package com.example.project.quote.model.service;

import com.example.project.quote.model.dto.QuoteDto;
import com.example.project.quote.model.entity.Quote;
import com.example.project.quote.model.entity.TodayQuote;
import com.example.project.quote.model.repository.QuoteRepository;
import com.example.project.quote.model.repository.TodayQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService{


    private final QuoteRepository quoteRepository;
    private final TodayQuoteRepository todayQuoteRepository;

    @Override
    public Optional<QuoteDto> selectOneQuote(Long quoteId) {
        return Optional.ofNullable(toDto(quoteRepository.findById(quoteId).orElseThrow()));
    }

    @Override
    public void setQuoteId(Long quoteId) {
        TodayQuote quote = todayQuoteRepository.findById((long)1).orElse(null);
        todayQuoteRepository.save(TodayQuote.builder().quoteId(quoteId).build());
    }

    @Override
    public Long getQuoteId() {
        return todayQuoteRepository.findById((long)1).get().getQuoteId();
    }
}
