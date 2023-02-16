package com.example.project.quote.controller;

import com.example.project.quote.model.dto.QuoteDto;
import com.example.project.quote.model.service.QuoteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {"하루 글귀 API"})
public class QuoteController {
    private final QuoteService quoteService;

    //글귀 1개 가져오기
    @GetMapping("/quote")
    public ResponseEntity<QuoteDto> selectOneQuote() {

        QuoteDto quoteDto = quoteService.selectOneQuote();
        return new ResponseEntity<>(quoteDto, HttpStatus.OK);
    }

}
