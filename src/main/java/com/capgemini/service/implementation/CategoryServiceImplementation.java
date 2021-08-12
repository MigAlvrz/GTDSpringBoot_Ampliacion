package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.CategoryVO;
import com.capgemini.repository.CategoryRepository;
import com.capgemini.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public CategoryVO add(CategoryVO category){
		return catRepo.save(category);	
	}

	@Override
	public CategoryVO delete(CategoryVO category) throws IllegalArgumentException{
		if(findById(category.getIdcategory())!=null) {
			catRepo.delete(category);
			return category;
		}
		throw new IllegalArgumentException();
			
	}

	@Override
	public CategoryVO deleteById(int id) throws IllegalArgumentException{
		CategoryVO cat = findById(id);
		if(cat!=null) {
			catRepo.deleteById(id);
			return cat;
		}
		throw new IllegalArgumentException();	
	}

	@Override
	public List<CategoryVO> listAll() {
		return catRepo.findAll();
	}

	@Override
	public CategoryVO findById(int id) throws IllegalArgumentException{
		Optional<CategoryVO> optionalCat = catRepo.findById(id);
		if(optionalCat.isPresent())
			return optionalCat.get();
		throw new IllegalArgumentException();
	}

	@Override
	public CategoryVO modify(CategoryVO category) {		
		return catRepo.save(category);
	}

}