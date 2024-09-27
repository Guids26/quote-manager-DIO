package com.quote.manager.controller;

import com.quote.manager.dto.QuoteDto;
import com.quote.manager.mapper.QuoteMapper;
import com.quote.manager.model.Quote;
import com.quote.manager.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @RouterOperation(operation = @Operation(description = "Say hello", operationId = "hello", tags = "persons",
            responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = QuoteDto.class)))))
    @PostMapping
    public QuoteDto saveNewQuote(@Valid @RequestBody QuoteDto dto){
        Quote quote = QuoteMapper.toEntity(dto);
        quote = quoteService.saveQuote(quote);
        return QuoteMapper.toDto(quote);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable("id") int id){
        quoteService.deleteQuote(id);
    }

    @PutMapping("/{id}")
    public QuoteDto updateQuote(@PathVariable("id") int id, @Valid @RequestBody QuoteDto dto){
        Quote quote = QuoteMapper.toEntity(dto);
        quote = quoteService.updateQuote(id, quote);
        return QuoteMapper.toDto(quote);
    }

    @GetMapping("/random")
    public QuoteDto getRandomQuote(){
        Quote quote = quoteService.getRandomQuote();
        return quote != null ? QuoteMapper.toDto(quote) : null;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<QuoteDto> listByFilters(@RequestParam(value = "author", required = false) String author, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "year", required = false) Integer year){
        return quoteService.listByFilters(author, category, year).stream().map(QuoteMapper::toDto).collect(Collectors.toList());
    }
}
