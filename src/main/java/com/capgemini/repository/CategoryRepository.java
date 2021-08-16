package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.CategoryVO;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryVO, Integer> {

}
