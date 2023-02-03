package com.example.project.quote.model.service;

import com.example.project.quote.model.dto.QuoteDto;
import com.example.project.quote.model.entity.Quote;
import com.example.project.quote.model.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService{


    private final QuoteRepository quoteRepository;

    @Override
    public Optional<QuoteDto> selectOneQuote(Long quoteId) {
        return Optional.ofNullable(toDto(quoteRepository.findById(quoteId).orElseThrow()));
    }
}
