package com.example.project.daily_emotion.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.example.project.daily_emotion.model.repository.DailyEmotionRepository;
import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyEmotionServiceImpl implements DailyEmotionService {

    private final DailyEmotionRepository dailyEmotionRepository;
    private final UserRepository userRepository;
//    private final ConvertDateType convertDateType;

    @Override
    public boolean insertDailyEmotion(DailyEmotionDto dailyEmotionDto) {
        User user = userRepository.findById(dailyEmotionDto.getUserId()).orElse(null);
        if(user == null) {
            return false;
        }
        DailyEmotion dailyEmotion = DailyEmotion.builder()
                .user(user)
                .dailyId(dailyEmotionDto.getDailyId())
                .emoticonName(dailyEmotionDto.getEmoticonName())
                .createdAt(dailyEmotionDto.getCreatedAt())
                .build();
        dailyEmotionRepository.save(dailyEmotion);

        return true;
    }

    /*
    해당 날짜 일기가 있는지 확인하기 위한 조회
     */
    @Override
    public DailyEmotion selectDailyEmotion(Long userId, LocalDate date) {

//        LocalDate date = LocalDate.parse(stringDate); // convertDateType.stringDateToDateTime(stringDate);
        return dailyEmotionRepository.findByUser_UserIdAndCreatedAt(userId, date).orElse(null);
    }

    /*
    캘린더 조회할 때 일별 감정통계 조회
     */
    @Override
    public Map<Integer, String> selectDailyEmotions(String nickname, String stringDate) {
        Map<Integer, String> map = new HashMap<>();
        long userId = 1;    // To Do : 닉네임으로 유저 아이디 찾아오기

        LocalDate date = LocalDate.parse(stringDate); //convertDateType.stringDateToDateTime(stringDate);
        LocalDate firstOfMonth = date.withDayOfMonth(1); // 주어진 날짜의 1번째 날로 날짜 값 변경
        LocalDate lastOfMonth = date.withDayOfMonth(date.lengthOfMonth()); // 주어진 날짜의 마지막 날로 날짜 값 변경
        // 조회결과 리스트로 받아오기
        List<DailyEmotion> dailyEmotions = dailyEmotionRepository.findAllByUser_UserIdAndCreatedAtBetween(userId, firstOfMonth, lastOfMonth);

        // DTO 변환
        List<DailyEmotionDto> dailyEmotionDtos = dailyEmotions.stream().map(this::toDto).collect(Collectors.toList());

        // Map<날짜, 이모티콘 이름> 형식으로 반환
        for(DailyEmotionDto dailyEmotionDto : dailyEmotionDtos) {
            int day = dailyEmotionDto.getCreatedAt().getDayOfMonth();
            String emoticonName = dailyEmotionDto.getEmoticonName();
            map.put(day, emoticonName);
        }
        return map;
    }

    @Override
    public void updateDailyEmotion(Long userId, LocalDate date, String emoticonName) {
        DailyEmotion dailyEmotion = selectDailyEmotion(userId, date);
        dailyEmotion.updateDailyEmotion(emoticonName);
    }

    @Override
    public void deleteDailyEmotion() {

    }
}
