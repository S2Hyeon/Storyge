package com.example.project.daily_emotion.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.example.project.daily_emotion.model.repository.DailyEmotionRepository;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyEmotionServiceImpl implements DailyEmotionService {

    private final DailyEmotionRepository dailyEmotionRepository;
    private final UserRepository userRepository;

    @Override
    public boolean insertDailyEmotion(DailyEmotionDto dailyEmotionDto) {
        User user = userRepository.findById(dailyEmotionDto.getUserId()).orElse(null);
        if (user == null) {
            return false;
        }
        dailyEmotionRepository.save(toEntity(dailyEmotionDto));
        return true;
    }

    /*
    해당 날짜 일기가 있는지 확인하기 위한 조회
     */
    @Override
    public Optional<DailyEmotion> selectOneDailyEmotion(Long userId, LocalDate date) {
        return dailyEmotionRepository.findByUser_UserIdAndCreatedAt(userId, date);
    }

    /*
    캘린더 조회할 때 일별 감정통계 조회
     */
    @Override
    public List<DailyEmotionDto> selectAllDailyEmotion(Long userId, String stringDate) {
        List<DailyEmotionDto> dailyEmotionDtoList = null;
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        LocalDate date = LocalDate.parse(stringDate); //convertDateType.stringDateToDateTime(stringDate);
        LocalDate firstOfMonth = date.withDayOfMonth(1); // 주어진 날짜의 1번째 날로 날짜 값 변경
        LocalDate lastOfMonth = date.withDayOfMonth(date.lengthOfMonth()); // 주어진 날짜의 마지막 날로 날짜 값 변경
        // 조회결과 리스트로 받아오기
        List<DailyEmotion> dailyEmotions = dailyEmotionRepository.findAllByUser_UserIdAndCreatedAtBetween(userId, firstOfMonth, lastOfMonth);

        // DTO 변환
        if (!dailyEmotions.isEmpty())
            dailyEmotionDtoList = dailyEmotions.stream().map(this::toDto).collect(Collectors.toList());


        return dailyEmotionDtoList;
    }

    @Override
    public void updateDailyEmotion(Long userId, LocalDate date, String emoticonName) {
        Optional<DailyEmotion> dailyEmotion = selectOneDailyEmotion(userId, date);
        if(dailyEmotion.isPresent()) {
            dailyEmotion.get().updateDailyEmotion(emoticonName);
        }

    }

    @Override
    public void deleteDailyEmotion(Long userId, LocalDate date) {
        Optional<DailyEmotion> dailyEmotion = selectOneDailyEmotion(userId, date);
        if(dailyEmotion.isPresent()) {
            Long dailyId = dailyEmotion.get().getDailyId();
            dailyEmotionRepository.deleteById(dailyId);
        }
    }
}
