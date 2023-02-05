package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecentDiaryCustomRepositoryImpl implements RecentDiaryCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public List<RecentDiary> selectAllRecentDiaryByFollowing(Long userId) {
//
//        List<RecentDiary> recentDiaryList = jpaQueryFactory
//                .selectFrom(recent_diary)
//                .
//
//        return null;
//    }
}
