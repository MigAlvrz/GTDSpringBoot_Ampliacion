package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
