package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.ReadDiary;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadDiaryRepository extends JpaRepository<ReadDiary, Long> {
    ReadDiary findByRecentId(Long recentId);
    Optional<ReadDiary> findByUserIdAndAndRecentId(Long userId, Long recentId);
}
