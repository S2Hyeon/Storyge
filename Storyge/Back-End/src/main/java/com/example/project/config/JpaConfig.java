package com.example.project.config;

import com.example.project.daily_emotion.model.repository.DailyEmotionRepository;
import com.example.project.daily_emotion.model.service.DailyEmotionService;
import com.example.project.daily_emotion.model.service.DailyEmotionServiceImpl;
import com.example.project.diary.model.repository.DiaryCountRepository;
import com.example.project.diary.model.repository.DiaryCustomRepository;
import com.example.project.diary.model.repository.DiaryRepository;
import com.example.project.diary.model.service.DiaryService;
import com.example.project.diary.model.service.DiaryServiceImpl;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.notification.model.repository.NotificationRepository;
import com.example.project.notification.model.service.NotificationService;
import com.example.project.notification.model.service.NotificationServiceImpl;
import com.example.project.quote.model.repository.QuoteRepository;
import com.example.project.quote.model.repository.TodayQuoteRepository;
import com.example.project.quote.model.service.QuoteService;
import com.example.project.quote.model.service.QuoteServiceImpl;
import com.example.project.recentdiary.model.repository.ReadDiaryRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryCustomRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryRepository;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import com.example.project.recentdiary.model.service.RecentDiaryServiceImpl;
import com.example.project.review.model.repository.ReviewRepository;
import com.example.project.review.model.service.ReviewService;
import com.example.project.review.model.service.ReviewServiceImpl;
import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.Service.UserServiceImpl;
import com.example.project.user.model.jwt.JwtUtil;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final DailyEmotionRepository dailyEmotionRepository;

    private final DiaryRepository diaryRepository;
    private final DiaryCustomRepository diaryCustomRepository;
    private final DiaryCountRepository diaryCountRepository;
    private final NotificationRepository notificationRepository;
    private final QuoteRepository quoteRepository;
    private final TodayQuoteRepository todayQuoteRepository;
    private final RecentDiaryRepository recentDiaryRepository;
    private final ReadDiaryRepository readDiaryRepository;
    private final RecentDiaryCustomRepository recentDiaryCustomRepository;
    private final ReviewRepository reviewRepository;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, followRepository);
    }

    @Bean
    public DailyEmotionService dailyEmotionService() {
        return new DailyEmotionServiceImpl(dailyEmotionRepository, userRepository);
    }

    @Bean
    public DiaryService diaryService() {
        return new DiaryServiceImpl(diaryRepository, diaryCustomRepository, userRepository, recentDiaryRepository, diaryCountRepository, dailyEmotionService(), recentDiaryService());
    }

    @Bean
    public NotificationService notificationService() {
        return new NotificationServiceImpl(notificationRepository);
    }

    @Bean
    public QuoteService quoteService() {
        return new QuoteServiceImpl(quoteRepository, todayQuoteRepository);
    }

    @Bean
    public RecentDiaryService recentDiaryService() {
        return new RecentDiaryServiceImpl(recentDiaryRepository, readDiaryRepository, followRepository, recentDiaryCustomRepository, diaryRepository);
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewServiceImpl(reviewRepository, userRepository, diaryRepository, notificationService());
    }

}
