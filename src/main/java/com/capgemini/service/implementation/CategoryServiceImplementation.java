package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.model.CategoryVO;
import com.capgemini.repository.CategoryRepository;
import com.capgemini.service.CategoryService;

public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public CategoryVO add(CategoryVO category) {
		return  catRepo.save(category);
	}

	@Override
	public CategoryVO delete(CategoryVO category) {
		if(findById(category.getIdcategory())!=null) {
			catRepo.delete(category);
			return category;
		}
		return null;
			
	}

	@Override
	public CategoryVO deleteById(int id) {
		CategoryVO cat = findById(id);
		if(cat!=null) {
			catRepo.deleteById(id);
			return cat;
		}
		return null;
			
	}

	@Override
	public List<CategoryVO> listAll() {
		return catRepo.findAll();
	}

	@Override
	public CategoryVO findById(int id) {
		Optional<CategoryVO> optionalCat = catRepo.findById(id);
		if(optionalCat.isPresent())
			return optionalCat.get();
		return null;
	}

}