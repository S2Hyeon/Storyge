package com.example.project.diary.model.repository;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    List<DiaryDto> findByIdAndCreatedAtContaining(String nickname, Date createdAt);
}
