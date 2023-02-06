package com.example.project.quote.model.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuoteScheduler {
    @Scheduled(cron="0 0 0 * * *")
    public long selectQuoteId(){
        return (long)(Math.random()*48);

    }
}
