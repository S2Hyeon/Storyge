package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.RecentDiary;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.project.follow.model.entity.QFollow.follow;
import static com.example.project.recentdiary.model.entity.QRecentDiary.recentDiary;

@Repository
public class RecentDiaryCustomRepositoryImpl implements RecentDiaryCustomRepository {

    private final JPAQueryFactory query;

    public RecentDiaryCustomRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<RecentDiary> selectAllRecentDiaryByFollowing(Long userId) {


        List<RecentDiary> recentDiaryList = query
                .selectFrom(recentDiary)
                .where(
                        (recentDiary.userId.in(
                                JPAExpressions
                                        .select(follow.following)
                                        .from(follow)
                                        .where(follow.follower.eq(userId))
                        )).and(recentDiary.endsAt.after(LocalDateTime.now()))

                )
                .orderBy(recentDiary.endsAt.desc())
                .fetch();


        return recentDiaryList;
    }
}
