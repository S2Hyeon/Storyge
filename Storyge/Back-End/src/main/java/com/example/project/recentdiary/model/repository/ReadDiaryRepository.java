package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.ReadDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadDiaryRepository extends JpaRepository<ReadDiary, Long> {

}
