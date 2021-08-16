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

import com.capgemini.model.GroupVO;
import com.capgemini.service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	GroupService grService;
	
	/**
	 * Creates a GroupVO, then adds it to the DB
	 * @param body
	 * @return
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody String body){
		GroupVO newGroup = null;
		try {
			newGroup = new ObjectMapper().readValue(body,GroupVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		GroupVO gr = grService.add(newGroup);
		return checkNull(gr);
	}
	
	/**
	 * Deletes an existing groupVO from the DB
	 * @param body
	 * @return
	 */
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String body){
		GroupVO group = null;
		try {
			group = new ObjectMapper().readValue(body,GroupVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		GroupVO gr = grService.delete(group);
		return checkNull(gr);
	}
	
	/**
	 * Finds a groupVO by it's ID, then deletes it
	 * @param id
	 * @return
	 */
	
	@DeleteMapping("/delete/{idcategory}")
	public ResponseEntity<?> deleteById(@RequestBody int id){
		GroupVO gr = grService.deleteById(id);
		return checkNull(gr);
	}
	
	/**
	 * returns all the existing categories from the DB
	 * @return
	 */
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(grService.listAll(),HttpStatus.OK);
	}
	
	/**
	 * Finds a category by it's ID
	 * @param id
	 * @return
	 */
	
	@GetMapping("/find/{idcategory}")
	public ResponseEntity<?> findById(@RequestBody int id){
		GroupVO gr = grService.findById(id);
		return checkNull(gr);
	}
	
	/**
	 * Checks if a GroupVO is correct, then returns an Ok httpStatus
	 * otherwise, returns a Bad_Request
	 * @param cat
	 * @return
	 */
	
	private ResponseEntity<?> checkNull(GroupVO gr) {
		if(gr!=null)
			return new ResponseEntity<>(gr,HttpStatus.OK);
		return new ResponseEntity<>(gr,HttpStatus.BAD_REQUEST);
	}

}
