package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.CategoryVO;
import com.capgemini.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService catService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody String body){
		CategoryVO newCat = null;
		try {
			newCat = new ObjectMapper().readValue(body, CategoryVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		CategoryVO cat = catService.add(newCat);
		return checkNull(cat);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody CategoryVO category){
		CategoryVO cat = catService.delete(category);
		return checkNull(cat);
	}
	
	@DeleteMapping("/delete/{idcategory}")
	public ResponseEntity<?> deleteById(@PathVariable int idcategory){
		CategoryVO cat = catService.deleteById(idcategory);
		return checkNull(cat);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(catService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping("/find/{idcategory}")
	public ResponseEntity<?> findById(@PathVariable int idcategory){
		CategoryVO cat = catService.findById(idcategory);
		return checkNull(cat);
	}
	
	private ResponseEntity<?> checkNull(CategoryVO cat) {
		if(cat!=null)
			return new ResponseEntity<>(cat,HttpStatus.OK);
		return new ResponseEntity<>(cat,HttpStatus.BAD_REQUEST);
	}
	

}
