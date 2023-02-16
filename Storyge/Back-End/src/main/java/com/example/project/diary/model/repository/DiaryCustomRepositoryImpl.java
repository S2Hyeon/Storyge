package com.example.project.diary.model.repository;

import com.example.project.diary.model.dto.DailyEmotionStatistic;
import com.example.project.diary.model.dto.EmotionStatistic;
import com.example.project.diary.model.dto.QDailyEmotionStatistic;
import com.example.project.diary.model.dto.QEmotionStatistic;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.example.project.diary.model.entity.QDiary.diary;

@Repository
public class DiaryCustomRepositoryImpl implements DiaryCustomRepository {

    private final JPAQueryFactory query;

    public DiaryCustomRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public Optional<DailyEmotionStatistic> dailyEmotionStatistic(Long userId, LocalDate date) {


        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = LocalDateTime.of(date, LocalTime.MAX).withNano(0);

        return Optional.ofNullable(query
                .select(new QDailyEmotionStatistic(diary.emoticonName, diary.emoticonName.count(), diary.createdAt.max()))
                .from(diary)
                .where(diary.userId.eq(userId), diary.createdAt.between(from, to))
                .groupBy(diary.emoticonName)
                .orderBy(diary.emoticonName.count().desc(), diary.createdAt.max().desc())
                .limit(1)
                .fetchOne());
    }

    @Override
    public List<EmotionStatistic> emotionStatistic(String period, LocalDate date, Long userId) {

        LocalDateTime from;
        LocalDateTime to;

        if (period.equals("month")) {
            from = date.withDayOfMonth(1).atStartOfDay();
            to = LocalDateTime.of(date.withDayOfMonth(date.lengthOfMonth()), LocalTime.MAX);
        } else if (period.equals("year")) {
            from = date.withDayOfYear(1).atStartOfDay();
            to = LocalDateTime.of(date.withDayOfYear(date.lengthOfYear()), LocalTime.MAX);
        } else {
            return null;
        }

        return query
                .select(new QEmotionStatistic(diary.emoticonName, diary.emoticonName.count()))
                .from(diary)
                .where(diary.user.userId.eq(userId), diary.createdAt.between(from, to))
                .groupBy(diary.emoticonName)
                .fetch();
    }
}
