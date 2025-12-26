package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticalVO {
    private Long id;
    private String author;
    private String tittle;
    private String content;
    private List<Reader> reader;
}
