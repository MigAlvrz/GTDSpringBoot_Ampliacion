package com.capgemini.service;

import java.util.List;

import com.capgemini.model.CategoryVO;

public interface CategoryService {
	
	public CategoryVO add(CategoryVO category);
	public CategoryVO delete(CategoryVO category) throws IllegalArgumentException;
	public CategoryVO deleteById(int id) throws IllegalArgumentException;
	public List<CategoryVO> listAll();
	public CategoryVO findById(int id) throws IllegalArgumentException;
	public CategoryVO modify(CategoryVO category);

}
