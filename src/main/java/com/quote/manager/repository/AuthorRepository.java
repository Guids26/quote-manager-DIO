package com.quote.manager.repository;

import com.quote.manager.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findFirstByNameIgnoringCase(String name);
}
