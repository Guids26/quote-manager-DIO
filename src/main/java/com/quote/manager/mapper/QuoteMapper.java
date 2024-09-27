package com.quote.manager.mapper;

import com.quote.manager.dto.QuoteDto;
import com.quote.manager.model.Author;
import com.quote.manager.model.Category;
import com.quote.manager.model.Quote;

public class QuoteMapper {

    public static Quote toEntity(QuoteDto dto){
        Quote quote = new Quote();
        quote.setQuote(dto.getQuote());
        quote.setQuoteYear(dto.getYear());
        if(dto.getAuthor() != null){
            Author author = new Author();
            author.setName(dto.getAuthor());
            quote.setAuthor(author);
        }
        if(dto.getCategory() != null){
            Category category = new Category();
            category.setName(dto.getCategory());
            quote.setCategory(category);
        }
        return quote;
    }

    public static QuoteDto toDto(Quote quote){
        QuoteDto dto =  new QuoteDto();
        dto.setId(quote.getId());
        dto.setQuote(quote.getQuote());
        dto.setYear(quote.getQuoteYear());
        if(quote.getAuthor() != null){
            dto.setAuthor(quote.getAuthor().getName());
        }
        if(quote.getCategory() != null){
            dto.setCategory(quote.getCategory().getName());
        }
        return dto;
    }

}
