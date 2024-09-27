package com.quote.manager.service;

import com.quote.manager.exception.custom.QuoteNotFound;
import com.quote.manager.model.Author;
import com.quote.manager.model.Category;
import com.quote.manager.model.Quote;
import com.quote.manager.repository.AuthorRepository;
import com.quote.manager.repository.CategoryRepository;
import com.quote.manager.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    public List<Quote> listByFilters(String authorName, String categoryName, Integer year){
        return quoteRepository.findByAnyFilters(authorName, categoryName, year);
    }

    public Quote getRandomQuote(){
        long numberOfRecords = quoteRepository.count();
        if(numberOfRecords > 0){
            long randomLong = ThreadLocalRandom.current().nextLong(1, numberOfRecords);
            Page<Quote> page = quoteRepository.findAll(PageRequest.of((int) randomLong - 1, 1, Sort.by(Sort.Direction.ASC, "id")));
            if(!page.isEmpty()){
                return page.getContent().get(0);
            }
        }
        return null;
    }

    public Quote saveQuote(Quote quote){
        Optional<Quote> quoteFound = quoteRepository.findFirstByQuoteIgnoringCase(quote.getQuote().trim());
        if(quoteFound.isEmpty()){
            quote.setAuthor(saveOrGetAuthor(quote));
            quote.setCategory(saveOrGetCategory(quote));
            return quoteRepository.save(quote);
        }else{
            return quoteFound.get();
        }
    }

    public Boolean deleteQuote(int id){
        boolean quoteExists = quoteRepository.existsById(id);
        if(quoteExists){
            quoteRepository.deleteById(id);
            return true;
        }else {
            throw new QuoteNotFound("quote not found to delete");
        }
    }

    public Quote updateQuote(int id, Quote quote){
        boolean quoteExists = quoteRepository.existsById(id);
        if(quoteExists){
            quote.setId(id);
            quote.setAuthor(saveOrGetAuthor(quote));
            quote.setCategory(saveOrGetCategory(quote));
            return quoteRepository.save(quote);
        }else{
            throw new QuoteNotFound("quote not found to update");
        }
    }

    private Author saveOrGetAuthor(Quote quote) {
        return Optional.ofNullable(quote.getAuthor())
                .map(author -> authorRepository.findFirstByNameIgnoringCase(author.getName().trim())
                        .orElseGet(() -> authorRepository.save(author)))
                .orElse(null);
    }

    private Category saveOrGetCategory(Quote quote) {
        return Optional.ofNullable(quote.getCategory())
                .map(category -> categoryRepository.findFirstByNameIgnoringCase(category.getName().trim())
                        .orElseGet(() -> categoryRepository.save(category)))
                .orElse(null);
    }
}
