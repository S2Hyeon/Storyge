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
                .update_cnt(diaryDto.getUpdate_cnt())
                .analizedResult(diaryDto.getAnalizedResult())
                .createdAt(LocalDateTime.now())
                .build();
        diaryRepository.save(diary);

        long userId = diaryDto.getUserId();
        LocalDate date = diaryDto.getCreatedAt();
        DailyEmotion dailyEmotion = dailyEmotionService.selectDailyEmotion(userId, date);
        if(dailyEmotion == null) {
            if(!dailyEmotionService.insertDailyEmotion(toDailyEmotionDto(diaryDto))) {
                return false;
            };
        }
        else {
            DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
        }

        return true;
    }


    @Override
    public Optional<DiaryDto> selectOneDiary(Long diaryId) {
        return Optional.ofNullable(toDto(diaryRepository.findById(diaryId).orElseThrow()));
    }

    public List<DiaryDto> selectDailyDiaries(String nickname, String stringDate) {
        User user = userRepository.findByNickname(nickname).orElse(null);
        LocalDate date = LocalDate.parse(stringDate);
        LocalDateTime startTimeOfDay = date.atStartOfDay();
        LocalDateTime endTimeOfDay = LocalDateTime.of(date, LocalTime.MAX).withNano(0);
        List<Diary> diaryList = diaryRepository.findAllByUser_UserIdAndCreatedAtBetween(user.getUserId(), startTimeOfDay, endTimeOfDay);
        return diaryList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /*
    만약 일기의 내용이 변경되지 않았다면 이모티콘과 공개여부 설정만 변경하고
    일기의 내용이 변경되었다면 이모티콘과 일기내용, 공개여부, 감정분석 결과 모두를 변경한다.
        => 일기 수정 횟수의 경우 일기의 내용이 변경되었다면 1로 변경되어야하기 때문에 entity에서 값을 변경할 때 설정해준다.
     */
    @Override
    public boolean updateDiary(DiaryUpdateParam param) {
        Diary diary = diaryRepository.findById(param.getDiaryId()).orElse(null);
        System.out.println(diary.getEmoticonName().hashCode());
        System.out.println(param.getEmoticonName().hashCode());
        if(diary == null) {
            return false;
        }

        if(diary.getDiaryContent().equals(param.getDiaryContent())) {
            diary.updateDiary(param.getEmoticonName(),
                                param.getScope());
        }
        else {
            diary.updateDiaryContent(param.getEmoticonName(),
                                        param.getDiaryContent(),
                                        param.getScope(),
                                        param.getAnalizedResult());
        }

        System.out.println(diary.getEmoticonName().equals(param.getEmoticonName()));
        if(!diary.getEmoticonName().equals(param.getEmoticonName())) {
            long userId = diary.getUser().getUserId();
            LocalDate date = diary.getCreatedAt().toLocalDate();
            System.out.println("이모티콘 변경됨=======");
            System.out.println(userId);
            System.out.println(date);
            DailyEmotionStatistic dailyEmotionStatistic = diaryCustomRepository.dailyEmotionStatistic(userId, date);
            dailyEmotionService.updateDailyEmotion(userId, date, dailyEmotionStatistic.getEmoticonName());
        }

        return true;
    }

    @Override
    public boolean deleteDiary(Long diaryId) {
        DiaryDto diaryDto = selectOneDiary(diaryId).orElse(null);
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
