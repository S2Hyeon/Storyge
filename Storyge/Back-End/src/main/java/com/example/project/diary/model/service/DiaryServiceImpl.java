package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.example.project.daily_emotion.model.service.DailyEmotionService;
import com.example.project.diary.model.dto.*;
import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.entity.DiaryCount;
import com.example.project.diary.model.repository.DiaryCountRepository;
import com.example.project.diary.model.repository.DiaryCustomRepository;
import com.example.project.diary.model.repository.DiaryRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryRepository;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryCustomRepository diaryCustomRepository;
    private final UserRepository userRepository;
    private final RecentDiaryRepository recentDiaryRepository;

    private final DiaryCountRepository diaryCountRepository;
    private final DailyEmotionService dailyEmotionService;
    private final RecentDiaryService recentDiaryService;


    @Override
    public Optional<Long> insertDiary(Long userId, DiaryRequestDto diaryRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        User user = optionalUser.get();
        // 오늘 작성한 일기 개수 확인
        Optional<DiaryCount> optionalDiaryCount = diaryCountRepository.findById(user.getUserId());
        if (optionalDiaryCount.isEmpty()) {
            return Optional.empty();
        }
        DiaryCount diaryCount = optionalDiaryCount.get();

        int currentCount = diaryCount.getDiaryCnt();
        if (currentCount >= 24) { // 제한개수(24개)를 넘어서면 false 리턴
            return Optional.empty();
        }

        // 일기 작성 개수 증가
        diaryCount.updateCount(currentCount + 1);

        Diary diary = toEntity(diaryRequestDto);
        diary.setUserId(userId);
        diary.setCreatedAt(LocalDateTime.now());
        diaryRepository.save(diary);

        recentDiaryService.insertRecentDiary(user.getUserId(), diary.getDiaryId()); // 최근 다이어리 저장

        LocalDate date = diary.getCreatedAt().toLocalDate();

        // 오늘 평균 감정 있는지 확인
        Optional<DailyEmotion> dailyEmotion = dailyEmotionService.selectOneDailyEmotion(userId, date);

        // 평균 감정 없다면 바로 등록
        if (dailyEmotion.isEmpty()) {
            // 일일 감정 평균에 등록 실패 시 empty를 리턴
            if(!dailyEmotionService.insertDailyEmotion(toDailyEmotionDto(diary)))
                return Optional.empty();
        } else {  // 평균 감정 있다면 오늘 일기 모두 가져온 뒤 평균 재계산 후 수정
            DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date).orElseThrow();
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
        }
        return Optional.of(diary.getDiaryId());
    }


    @Override
    public Optional<DiaryResponseDto> selectOneDiary(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        return diary.map(this::toResponseDto);
    }

    public List<DiaryResponseDto> selectAllDailyDiary(Long userId, String stringDate) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return null;
        }

        LocalDate date = LocalDate.parse(stringDate);
        LocalDateTime startTimeOfDay = date.atStartOfDay();
        LocalDateTime endTimeOfDay = LocalDateTime.of(date, LocalTime.MAX).withNano(0);
        List<Diary> diaryList = diaryRepository.findAllByUser_UserIdAndCreatedAtBetween(userId, startTimeOfDay, endTimeOfDay);
        return diaryList.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public int selectDiaryCount(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return -1;
        }

        User user = optionalUser.get();

        Optional<DiaryCount> optionalDiaryCount = diaryCountRepository.findById(user.getUserId());
        if (optionalDiaryCount.isEmpty()) {
            return -1;
        }

        return optionalDiaryCount.get().getDiaryCnt();

    }

    public List<EmotionStatistic> selectEmotionStatistic(String period, String stringDate, Long userId) {
        LocalDate date = LocalDate.parse(stringDate);
        return diaryCustomRepository.emotionStatistic(period, date, userId);
    }

    /*
    일기 수정 기회 남아있다면
    일기 수정하고 updateCnt를 1로 변경한다.
    이모티콘 바뀌었다면 일일 평균 감정에 반영한 뒤
    수정을 진행한다.
     */
    @Override
    public boolean updateDiary(Long userId, DiaryUpdateParam param) {
        Optional<Diary> optionalDiary = diaryRepository.findById(param.getDiaryId());
        if (optionalDiary.isEmpty() || userId != optionalDiary.get().getUserId()) {
            return false;
        }

        Diary diary = optionalDiary.get();

        if (diary.getUpdateCnt() == 0) {
            if (!param.getEmoticonName().equals(diary.getEmoticonName())) {
                LocalDate date = diary.getCreatedAt().toLocalDate();
                Optional<DailyEmotionStatistic> dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);

                if (dailyEmotionStatistic.isPresent())
                    dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.get().getEmoticonName());

            }

            diary.updateDiary(param.getEmoticonName(),
                    param.getDiaryContent(),
                    param.getScope(),
                    1,
                    param.getAnalizedResult());

            return true;
        }

        return false;
    }

    public boolean updateScope(Long userId, Long diaryId, int scope) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        if (optionalDiary.isEmpty() || userId != optionalDiary.get().getUserId()) {
            return false;
        }

        optionalDiary.get().updateScope(scope);

        if(recentDiaryRepository.findByDiaryId(optionalDiary.get().getDiaryId()).isPresent()){ // 만약 recentdiary에 있다면
            if(scope==0){ // 비공개로 바꿨으면
                recentDiaryRepository.deleteByDiaryId(optionalDiary.get().getDiaryId()); // 지우기
            }
        }
        else{ // 없고
            if(optionalDiary.get().getCreatedAt().plusHours(24).isAfter(LocalDateTime.now())){ // 공개 다이어리라면
                recentDiaryService.insertRecentDiary(userId, diaryId); // recentdiary에 추가
            }
        }

        return true;
    }


    @Override
    public boolean deleteDiary(Long userId, Long diaryId) {
        Optional<DiaryResponseDto> diaryResponseDto = selectOneDiary(diaryId);
        if (diaryResponseDto.isEmpty() || userId != diaryResponseDto.get().getUserId()) {
            return false;
        }
        diaryRepository.deleteById(diaryId);

        LocalDate date = diaryResponseDto.get().getCreatedAt().toLocalDate();
        Optional<DailyEmotionStatistic> dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);

        if (dailyEmotionStatistic.isPresent())
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.get().getEmoticonName());
        else {
            dailyEmotionService.deleteDailyEmotion(userId, date);
        }
        return true;
    }
}
