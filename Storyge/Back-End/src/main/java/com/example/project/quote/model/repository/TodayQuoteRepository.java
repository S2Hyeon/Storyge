package com.example.project.quote.model.repository;

import com.example.project.quote.model.entity.TodayQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayQuoteRepository extends JpaRepository<TodayQuote, Long> {
}
