package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecentDiaryCustomRepositoryImpl implements RecentDiaryCustomRepository {


    private final EntityManager em;

    @Override
    public List<RecentDiary> selectAllRecentDiaryByFollowing(User userId) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        //여기 수정해야함 ////////////////////////////////////////////////////////////////////////////////////////

//        List<RecentDiary> recentDiaryList = jpaQueryFactory
//                .selectFrom(recentDiary)
////                .leftJoin(readDiary)
//                .where(
//                        (recentDiary.userId.in(
//                        JPAExpressions
//                                .select(follow.following)
//                                .from(follow)
//                                .where(follow.follower.eq(userId))
//                        )).and(recentDiary.endsAt.after(LocalDateTime.now()))
////                                .and(recentDiary.recentId.eq(readDiary.recentId))
//
//                )
//                .orderBy(recentDiary.endsAt.desc())
////                .limit(20)
//                .fetch();


//        return recentDiaryList;
        return null;
    }
}
