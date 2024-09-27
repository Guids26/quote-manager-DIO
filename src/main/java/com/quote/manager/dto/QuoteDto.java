package com.quote.manager.dto;

import com.quote.manager.model.Author;
import com.quote.manager.model.Quote;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public class QuoteDto {
    private int id;
    @NotNull(message = "the field quote is mandatory")
    @NotBlank(message = "the field quote is not blank")
    private String quote;
    private String author;
    private Year year;
    private String category;

    public QuoteDto(int id, String quote, String author, Year year, String category) {
        this.id = id;
        this.quote = quote;
        this.author = author;
        this.year = year;
        this.category = category;
    }

    public QuoteDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
