package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.example.project.daily_emotion.model.service.DailyEmotionService;
import com.example.project.diary.model.dto.DailyEmotionStatistic;
import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.dto.EmotionStatistic;
import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.entity.DiaryCount;
import com.example.project.diary.model.repository.DiaryCountRepository;
import com.example.project.diary.model.repository.DiaryCustomRepository;
import com.example.project.diary.model.repository.DiaryRepository;
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

    private final DiaryCountRepository diaryCountRepository;
    private final DailyEmotionService dailyEmotionService;
    private final RecentDiaryService recentDiaryService;


    @Override
    public boolean insertDiary(DiaryDto diaryDto) {
        User user = userRepository.findById(diaryDto.getUserId()).orElse(null);
        if (user == null) {
            return false;
        }

        // 오늘 작성한 일기 개수 확인
        DiaryCount diaryCount = diaryCountRepository.findById(user.getUserId()).orElse(null);
        if (diaryCount == null) {
            return false;
        }
        int currentCount = diaryCount.getDiaryCnt();
        if (currentCount >= 24) { // 제한개수(24개)를 넘어서면 false 리턴
            return false;
        }

        // 일기 작성 개수 증가
        diaryCount.updateCount(currentCount + 1);

        // To DO : 문장 분석 결과 가져오기
        diaryDto.setAnalizedResult("문장 분석 결과 테스트");

        diaryDto.setCreatedAt(LocalDateTime.now());
        Diary diary = toEntity(diaryDto);
        diaryRepository.save(diary);

        recentDiaryService.insertRecentDiary(user.getUserId(), diary.getDiaryId()); // 최근 다이어리 저장


        long userId = diaryDto.getUserId();
        LocalDate date = diaryDto.getCreatedAt().toLocalDate();

        // 오늘 평균 감정 있는지 확인
        Optional<DailyEmotion> dailyEmotion = dailyEmotionService.selectOneDailyEmotion(userId, date);

        if (dailyEmotion.isPresent()) {
            return dailyEmotionService.insertDailyEmotion(toDailyEmotionDto(diaryDto));
        } else {  // 평균 감정 있다면 오늘 일기 모두 가져온 뒤 평균 재계산 후 수정
            DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date).orElseThrow();
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
        }
        return true;
    }


    @Override
    public DiaryDto selectOneDiary(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        return diary.map(this::toDto).orElse(null);
    }

    public List<DiaryDto> selectAllDailyDiary(Long userId, String stringDate) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        LocalDate date = LocalDate.parse(stringDate);
        LocalDateTime startTimeOfDay = date.atStartOfDay();
        LocalDateTime endTimeOfDay = LocalDateTime.of(date, LocalTime.MAX).withNano(0);
        List<Diary> diaryList = diaryRepository.findAllByUser_UserIdAndCreatedAtBetween(userId, startTimeOfDay, endTimeOfDay);
        return diaryList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public int selectDiaryCount(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return -1;
        }

        DiaryCount diaryCount = diaryCountRepository.findById(user.getUserId()).orElse(null);
        if (diaryCount == null) {
            return -1;
        }

        return diaryCount.getDiaryCnt();

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
        Diary diary = diaryRepository.findById(param.getDiaryId()).orElse(null);
        if (diary == null || userId != diary.getUserId()) {
            return false;
        }

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
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        if (diary == null || userId != diary.getUserId()) {
            return false;
        }

        diary.updateScope(scope);

        return true;
    }


    @Override
    public boolean deleteDiary(Long userId, Long diaryId) {
        DiaryDto diaryDto = selectOneDiary(diaryId);
        if (diaryDto == null || userId != diaryDto.getUserId()) {
            return false;
        }
        diaryRepository.deleteById(diaryId);

        LocalDate date = diaryDto.getCreatedAt().toLocalDate();
        Optional<DailyEmotionStatistic> dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);

        if (dailyEmotionStatistic.isPresent())
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.get().getEmoticonName());
        else {
            dailyEmotionService.deleteDailyEmotion(userId, date);
        }
        return true;
    }
}
