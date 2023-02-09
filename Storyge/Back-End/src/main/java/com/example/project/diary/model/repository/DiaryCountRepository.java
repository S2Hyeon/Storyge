package com.example.project.diary.model.repository;

import com.example.project.diary.model.entity.DiaryCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryCountRepository extends JpaRepository<DiaryCount, Long> {
}
