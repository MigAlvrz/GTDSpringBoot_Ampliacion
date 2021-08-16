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

import com.capgemini.model.GroupUserVO;
import com.capgemini.service.GroupUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/groupUser")
public class GroupUserController {
	
	@Autowired
	GroupUserService grUsService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody String body){
		GroupUserVO newGrUs = null;
		try {
			newGrUs = new ObjectMapper().readValue(body, GroupUserVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		GroupUserVO grUs = grUsService.add(newGrUs);
		return checkNull(grUs);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody GroupUserVO groupUser){
		GroupUserVO grUs = grUsService.delete(groupUser);
		return checkNull(grUs);
	}
	
	@DeleteMapping("/delete/{idgroupuser}")
	public ResponseEntity<?> deleteById(@PathVariable int idgroupuser){
		GroupUserVO grUs = grUsService.deleteById(idgroupuser);
		return checkNull(grUs);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(grUsService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping("/find/{idgroupuser}")
	public ResponseEntity<?> findById(@PathVariable int idgroupuser){
		GroupUserVO grUs = grUsService.findById(idgroupuser);
		return checkNull(grUs);
	}
	
	private ResponseEntity<?> checkNull(GroupUserVO grUs) {
		if(grUs!=null)
			return new ResponseEntity<>(grUs,HttpStatus.OK);
		return new ResponseEntity<>(grUs,HttpStatus.BAD_REQUEST);
	}

}
