package com.example.project.quote.model.scheduler;

import com.example.project.quote.model.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteScheduler {

    private final QuoteService quoteService;

    //매일 0시 0분에 새로운 명언 번호 설정
    @Scheduled(cron="0 0 0 * * *")
    public void selectQuoteId(){
        Long quoteId = (long)(Math.random()*48);
        quoteService.setQuoteId(quoteId);
    }
}
