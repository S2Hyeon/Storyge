package com.example.project.quote.model.repository;

import com.example.project.quote.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
