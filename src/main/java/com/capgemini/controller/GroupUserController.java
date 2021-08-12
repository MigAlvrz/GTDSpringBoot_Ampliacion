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

import com.capgemini.model.GroupUserVO;
import com.capgemini.service.GroupUserService;

@RestController
@RequestMapping("/groupUser")
public class GroupUserController {
	
	@Autowired
	GroupUserService grUsService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody GroupUserVO groupUser){
		GroupUserVO grUs = grUsService.add(groupUser);
		return checkNull(grUs);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody GroupUserVO groupUser){
		GroupUserVO grUs = grUsService.delete(groupUser);
		return checkNull(grUs);
	}
	
	@DeleteMapping("/delete/{idgroupuser}")
	public ResponseEntity<?> deleteById(@RequestBody int id){
		GroupUserVO grUs = grUsService.deleteById(id);
		return checkNull(grUs);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(grUsService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping("/find/{idgroupuser}")
	public ResponseEntity<?> findById(@RequestBody int id){
		GroupUserVO grUs = grUsService.findById(id);
		return checkNull(grUs);
	}
	
	private ResponseEntity<?> checkNull(GroupUserVO grUs) {
		if(grUs!=null)
			return new ResponseEntity<>(grUs,HttpStatus.OK);
		return new ResponseEntity<>(grUs,HttpStatus.BAD_REQUEST);
	}

}
