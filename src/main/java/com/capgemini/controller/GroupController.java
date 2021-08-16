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
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody GroupVO group){
		GroupVO gr = grService.delete(group);
		return checkNull(gr);
	}
	
	@DeleteMapping("/delete/{idcategory}")
	public ResponseEntity<?> deleteById(@RequestBody int id){
		GroupVO gr = grService.deleteById(id);
		return checkNull(gr);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(grService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping("/find/{idcategory}")
	public ResponseEntity<?> findById(@RequestBody int id){
		GroupVO gr = grService.findById(id);
		return checkNull(gr);
	}
	
	private ResponseEntity<?> checkNull(GroupVO gr) {
		if(gr!=null)
			return new ResponseEntity<>(gr,HttpStatus.OK);
		return new ResponseEntity<>(gr,HttpStatus.BAD_REQUEST);
	}

}
