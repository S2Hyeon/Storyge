package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.example.project.daily_emotion.model.service.DailyEmotionService;
import com.example.project.diary.model.dto.DailyEmotionStatistic;
import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.repository.DiaryCustomRepository;
import com.example.project.diary.model.repository.DiaryRepository;
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
public class DiaryServiceImpl implements DiaryService{

    private final DiaryRepository diaryRepository;
    private final DiaryCustomRepository diaryCustomRepository;
    private final DailyEmotionService dailyEmotionService;
    private final UserRepository userRepository;

    @Override
    public boolean insertDiary(DiaryDto diaryDto) {
        User user = userRepository.findById(diaryDto.getUserId()).orElse(null);
        if(user == null) {
            return false;
        }
        // To DO : 문장 분석 결과 가져오기
        diaryDto.setAnalizedResult("문장 분석 결과 테스트");

        Diary diary = Diary.builder()
                .user(user)
                .emoticonName(diaryDto.getEmoticonName())
                .diaryContent(diaryDto.getDiaryContent())
                .scope(diaryDto.getScope())
                .updateCnt(diaryDto.getUpdate_cnt())
                .analizedResult(diaryDto.getAnalizedResult())
                .createdAt(LocalDateTime.now())
                .build();
        diaryRepository.save(diary);

        long userId = diaryDto.getUserId();
        LocalDate date = diaryDto.getCreatedAt();

        // 오늘 평균 감정 있는지 확인
        DailyEmotion dailyEmotion = dailyEmotionService.selectDailyEmotion(userId, date);

        if(dailyEmotion == null) {
            return dailyEmotionService.insertDailyEmotion(toDailyEmotionDto(diaryDto));
        }
        else {  // 평균 감정 있다면 오늘 일기 모두 가져온 뒤 평균 재계산 후 수정
            DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
        }

        return true;
    }


    @Override
    public DiaryDto selectOneDiary(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        return diary.map(this::toDto).orElse(null);
    }

    public List<DiaryDto> selectDailyDiaries(String nickname, String stringDate) {
        User user = userRepository.findByNickname(nickname).orElse(null);
        if(user == null) {
            return null;
        }
        LocalDate date = LocalDate.parse(stringDate);
        LocalDateTime startTimeOfDay = date.atStartOfDay();
        LocalDateTime endTimeOfDay = LocalDateTime.of(date, LocalTime.MAX).withNano(0);
        List<Diary> diaryList = diaryRepository.findAllByUser_UserIdAndCreatedAtBetween(user.getUserId(), startTimeOfDay, endTimeOfDay);
        return diaryList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /*
    일기 수정 기회 남아있다면
    일기 수정하고 updateCnt를 1로 변경한다.
    이모티콘 바뀌었다면 일일 평균 감정에 반영한 뒤
    수정을 진행한다.
     */
    @Override
    public boolean updateDiary(DiaryUpdateParam param) {
        Diary diary = diaryRepository.findById(param.getDiaryId()).orElse(null);
        if(diary == null) {
            return false;
        }

        if(diary.getUpdateCnt() == 0) {
            if(!param.getEmoticonName().equals(diary.getEmoticonName())) {
                long userId = diary.getUser().getUserId();
                LocalDate date = diary.getCreatedAt().toLocalDate();
                DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);
                dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
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

    public boolean updateScope(long diaryId, int scope) {
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        if(diary == null) {
            return false;
        }

        diary.updateScope(scope);

        return true;
    }



    @Override
    public boolean deleteDiary(Long diaryId) {
        DiaryDto diaryDto = selectOneDiary(diaryId);
        if(diaryDto == null) {
            return false;
        }
        diaryRepository.deleteById(diaryId);
        long userId = diaryDto.getUserId();
        LocalDate date = diaryDto.getCreatedAt();
        DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);
        dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
        return true;
    }
}
