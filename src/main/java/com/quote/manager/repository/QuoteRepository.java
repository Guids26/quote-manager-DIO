package com.quote.manager.repository;

import com.quote.manager.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    Optional<Quote> findFirstByQuoteIgnoringCase(String quote);

    Page<Quote> findAll(Pageable pageable);

    @Query(value = "SELECT Q.* \n" +
            "FROM QUOTE Q \n" +
            "LEFT JOIN CATEGORY C ON C.ID = Q.CATEGORY_ID \n" +
            "LEFT JOIN AUTHOR A ON A.ID = Q.AUTHOR_ID \n" +
            "WHERE \n" +
            "    (:authorName IS NULL OR LOWER(A.NAME) LIKE LOWER(CONCAT('%', TRIM(:authorName), '%'))) AND  \n" +
            "    (:year IS NULL OR Q.QUOTE_YEAR = :year) AND \n" +
            "    (:category IS NULL OR LOWER(C.NAME) LIKE LOWER(CONCAT('%', TRIM(:category), '%')))", nativeQuery = true)
    List<Quote> findByAnyFilters(String authorName, String category, Integer year);
}
