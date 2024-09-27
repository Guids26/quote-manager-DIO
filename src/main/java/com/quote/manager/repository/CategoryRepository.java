package com.quote.manager.repository;

import com.quote.manager.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Optional<Category> findFirstByNameIgnoringCase(String name);
}
