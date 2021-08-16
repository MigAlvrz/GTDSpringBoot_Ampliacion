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

import com.capgemini.model.UserVO;
import com.capgemini.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody UserVO user) {
		UserVO newUser = userService.add(user);
		return checkNull(newUser);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody UserVO user) {
		UserVO exUser = userService.delete(user);
		return checkNull(exUser);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteById(@PathVariable int userId) {
		UserVO exUser = userService.deleteById(userId);
		return checkNull(exUser);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{userId}")
	public ResponseEntity<?> findById(@PathVariable int userId) {
		UserVO user = userService.findById(userId);
		return checkNull(user);
	}

	private ResponseEntity<?> checkNull(UserVO user) {
		if(user!=null) 
			return new ResponseEntity<>(user ,HttpStatus.OK);
		return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}

}
