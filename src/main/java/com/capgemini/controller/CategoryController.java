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
	
	/**
	 * Creates a new CategoryVO and adds it to the DB
	 * @param body
	 * @return
	 */
	
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
	
	/**
	 * Deletes a CategoryVO from the DB
	 * @param category
	 * @return
	 */
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String body){
		CategoryVO CategoryToBeDeleted = null;
		try {
			CategoryToBeDeleted = new ObjectMapper().readValue(body, CategoryVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		CategoryVO cat = catService.delete(CategoryToBeDeleted);
		return checkNull(CategoryToBeDeleted);
	}
	
	/**
	 * Finds a CategoryVO by it's ID and deletes it
	 * @param idcategory
	 * @return
	 */
	
	@DeleteMapping("/delete/{idcategory}")
	public ResponseEntity<?> deleteById(@PathVariable int idcategory){
		CategoryVO cat = catService.deleteById(idcategory);
		return checkNull(cat);
	}
	
	/**
	 * returns all the categoryVOs available in the DB
	 * @return
	 */
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(catService.listAll(),HttpStatus.OK);
	}
	
	/**
	 * Finds a categoryVO by id
	 * @param idcategory
	 * @return
	 */
	
	@GetMapping("/find/{idcategory}")
	public ResponseEntity<?> findById(@PathVariable int idcategory){
		CategoryVO cat = catService.findById(idcategory);
		return checkNull(cat);
	}
	
	/**
	 * Checks if a categoryVO is correct, then returns an Ok httpStatus
	 * otherwise, returns a Bad_Request
	 * @param cat
	 * @return
	 */
	
	private ResponseEntity<?> checkNull(CategoryVO cat) {
		if(cat!=null)
			return new ResponseEntity<>(cat,HttpStatus.OK);
		return new ResponseEntity<>(cat,HttpStatus.BAD_REQUEST);
	}
	

}
