//package com.example.project.daily_emotion.model.service;
//
//import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
//import com.example.project.daily_emotion.model.entity.DailyEmotion;
//import com.example.project.daily_emotion.model.repository.DailyEmotionRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class DailyEmotionServiceImpl implements DailyEmotionService {
//
//    private final DailyEmotionRepository dailyEmotionRepository;
//
//    @Override
//    public void insertDailyEmotion(DailyEmotionDto dailyEmotionDto) {
//        dailyEmotionRepository.save(toEntity(dailyEmotionDto));
//    }
//
//    /*
//    해당 날짜 일기가 있는지 확인하기 위한 조회
//     */
//    @Override
//    public Long selectOneDailyEmotion(String nickname, String stringDate) {
//        return dailyEmotionRepository.countByUser_UserIdAndCreatedAt(userId, date);
//    }
//
//    /*
//    캘린더 조회할 때 일별 감정통계 조회
//     */
//    @Override
//    public Map<Integer, String> selectDailyEmotions(String nickname, String stringDate) {
//        Map<Integer, String> map = new HashMap<>();
//        List<DailyEmotionDto> dailyEmotions = dailyEmotionRepository.findByUser_UserIdAndCreatedAt(userId, date);
//        for(DailyEmotionDto dailyEmotionDto : dailyEmotions) {
//            int day = dailyEmotionDto.getCreatedAt().getDayOfMonth();
//            String emoticonName = dailyEmotionDto.getEmoticonName();
//            map.put(day, emoticonName);
//        }
//        return map;
//    }
//
//    @Override
//    public void updateDailyEmotion(Long userId, String emoticonName) {
//        DailyEmotion dailyEmotion = dailyEmotionRepository.findById(userId).orElseThrow();
//        dailyEmotion.updateDailyEmotion(emoticonName);
//    }
//
//    @Override
//    public void deleteDailyEmotion() {
//
//    }
//}
