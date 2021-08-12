package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.CategoryVO;
import com.capgemini.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService catService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(CategoryVO category){
		CategoryVO cat = catService.add(category);
		return checkNull(cat);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody CategoryVO category){
		CategoryVO cat = catService.delete(category);
		return checkNull(cat);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<?> deleteById(@RequestBody int id){
		CategoryVO cat = catService.deleteById(id);
		return checkNull(cat);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(catService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping("/findId")
	public ResponseEntity<?> findById(@RequestBody int id){
		CategoryVO cat = catService.findById(id);
		return checkNull(cat);
	}
	
	private ResponseEntity<?> checkNull(CategoryVO cat) {
		if(cat!=null)
			return new ResponseEntity<>(cat,HttpStatus.OK);
		return new ResponseEntity<>(cat,HttpStatus.BAD_REQUEST);
	}
	

}