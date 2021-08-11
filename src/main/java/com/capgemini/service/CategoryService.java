package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Category;

public interface CategoryService {
	
	public CategoryVO add(CategoryVO category);
	public CategoryVO delete(CategoryVO category);
	public CategoryVO deleteById(int id);
	public List<CategoryVO> listAll();
	public CategoryVO findById(int id);

}
