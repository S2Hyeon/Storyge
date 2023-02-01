package com.example.project.daily_emotion.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.example.project.daily_emotion.model.repository.DailyEmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyEmotionServiceImpl implements DailyEmotionService {

    /*
    일기를 작성, 수정, 삭제하면
유저의 해당 날짜의 일기들이 있다면
	diary에서 해당 날짜 일기 불러와서 통계를 내고(가장 많은거, 동률이면 최근거)
	해당 날짜의 통계결과를 statistic 테이블에 갱신한다.
일기 작성 시 유저의 해당 날짜의 일기들이 없다면
	현재 일기 정보를 그대로 statistic 테이블에 insert한다.

statistic 테이블은 어떤 유저의 각 날짜별 감정통계가 들어있다.

달력을 조회 할 때는 statistic 테이블의 날짜별 감정통계를 이용해 표시한다

통계 페이지에서는 어떤 유저의 년, 월별로 일기의 감정을 모두 가져와서 수치를 제공한다.
     */
    private final DailyEmotionRepository dailyEmotionRepository;

    @Override
    public void insertDailyEmotion(DailyEmotionDto dailyEmotionDto) {
        dailyEmotionRepository.save(toEntity(dailyEmotionDto));
    }

    @Override
    public Optional<DailyEmotion> selectOneDailyEmotion(Long userId, Date date) {
        return Optional.ofNullable(toDto(dailyEmotionRepository.findByIdAndCreatedAt(userId, date).orElseThrow()));
        return dailyEmotionRepository.findByIdAndCreatedAt(userId, date);
    }

    @Override
    public Map<Integer, File> selectDailyEmotions(String nickname, Date date) {

        return null;
    }

    @Override
    public void updateDailyEmotion(String nickname, DailyEmotionDto dailyEmotionDto) {
        DailyEmotion dailyEmotion;
    }

    @Override
    public void deleteDailyEmotion() {

    }
}
