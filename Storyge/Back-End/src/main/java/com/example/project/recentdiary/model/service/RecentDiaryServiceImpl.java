package com.example.project.recentdiary.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.recentdiary.model.repository.ReadDiaryRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryRepository;
import com.example.project.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecentDiaryServiceImpl implements RecentDiaryService {


    private final RecentDiaryRepository recentDiaryRepository;
    private final ReadDiaryRepository readDiaryRepository;

    @Override
    public void insertRecentDiary(User user, Diary diary) {
        if(diary.getScope()==1){ // 공개일때
            recentDiaryRepository.save(RecentDiary.builder()
                    .userId(user)
                    .diaryId(diary)
                    .build());
        }
    }
}
