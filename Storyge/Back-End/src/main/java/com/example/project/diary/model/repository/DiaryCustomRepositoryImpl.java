package com.example.project.diary.model.repository;

import com.example.project.diary.model.dto.DailyEmotionStatistic;
import com.example.project.diary.model.dto.QDailyEmotionStatistic;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.project.diary.model.entity.QDiary.diary;

public class DiaryCustomRepositoryImpl implements DiaryCustomRepository {

    private EntityManager em;
    @Override
    public DailyEmotionStatistic dailyEmotionStatistic(Long user_id, LocalDateTime date) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = LocalDateTime.of(date, LocalTime.MAX).withNano(0);

        long userId = 1;
        List<DailyEmotionStatistic> dailyEmotionStatisticDto = jpaQueryFactory
                .select(new QDailyEmotionStatistic(diary.emoticonName, diary.emoticonName.count(), diary.createdAt.max()))
                .from(diary)
                .where(diary.user.userId.eq(userId), diary.createdAt.between(from, to))
                .groupBy(diary.emoticonName)
                .orderBy(diary.emoticonName.count().desc(), diary.createdAt.max().desc())
                .fetch();
        return null;
    }
}
