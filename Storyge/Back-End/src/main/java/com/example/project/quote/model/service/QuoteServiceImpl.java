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
    public Optional<QuoteDto> selectOneQuote() {
        TodayQuote quote = todayQuoteRepository.findById((long)1).orElse(null);
        if(quote==null){
            todayQuoteRepository.save(TodayQuote.builder()
                    .todayId(1L)
                    .todayQuoteId(15L).build());
            quote = todayQuoteRepository.findById((long)1).orElse(null);
        }
        return Optional.ofNullable(toDto(quoteRepository.findById(quote.getTodayQuoteId()).orElseThrow()));
    }

    @Override
    public void setQuoteId(Long quoteId) {
        todayQuoteRepository.save(TodayQuote.builder().todayId((long)1).todayQuoteId(quoteId).build());
    }

}
