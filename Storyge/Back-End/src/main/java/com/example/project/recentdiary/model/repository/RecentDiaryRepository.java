package com.example.project.recentdiary.model.repository;

import com.example.project.diary.model.entity.Diary;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecentDiaryRepository extends JpaRepository<RecentDiary, Long> {

    Optional<RecentDiary> findByUserId(User userId);
    Optional<RecentDiary> findByDiaryId(Diary diary);
    void deleteByUserId(User user);
    void deleteByDiaryId(Diary diaryId);

}
