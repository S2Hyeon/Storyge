package com.example.project.quote.model.service;

import com.example.project.quote.model.dto.QuoteDto;
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
public class QuoteServiceImpl implements QuoteService {


    private final QuoteRepository quoteRepository;
    private final TodayQuoteRepository todayQuoteRepository;

    // 명언 1개 가져오기
    @Override
    public QuoteDto selectOneQuote() {

        Optional<TodayQuote> quote = todayQuoteRepository.findById((long) 1); // db에 저장되어 있는 오늘의 번호 가져오기
        if (quote.isEmpty()) { // 오늘의 번호가 없다면
            todayQuoteRepository.save(TodayQuote.builder() // 임의로 15번 넣어줌
                    .todayId(1L)
                    .todayQuoteId(15L).build());
            quote = todayQuoteRepository.findById((long) 1);
        }

        return toDto(quoteRepository.findById(quote.get().getTodayQuoteId()).orElseThrow());
    }

    @Override
    public void setQuoteId(Long quoteId) {
        //scheduler에서 난수 발생 후 quotId에 들어감
        todayQuoteRepository.save(TodayQuote.builder().todayId((long) 1).todayQuoteId(quoteId).build()); //오늘의 번호 입력
    }

}
