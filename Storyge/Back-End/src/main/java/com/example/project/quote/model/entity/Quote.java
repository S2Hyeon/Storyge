package com.example.project.quote.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long quteId;

    @Column(name = "quote_content")
    private String quoteContent;

    @Column(name = "quote_source")
    private String quoteSource;

}
