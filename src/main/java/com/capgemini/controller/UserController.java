package com.capgemini.controller;

import java.net.http.HttpHeaders;  
import java.util.Date;
import java.util.List;

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
import com.capgemini.repository.UserRepository;
import com.capgemini.service.UserService;
import com.capgemini.service.security.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Creates an UserVO, then adds it to the DB
	 * @param body
	 * @return
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody String body) {
		UserVO newUser = null;
		try {
			newUser = new ObjectMapper().readValue(body, UserVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(newUser.getLogin()+" "+newUser.getEmail());
		userService.add(newUser);
		return checkNull(newUser);
	}
	
	/**
	 * Deletes an UserVO from the DB
	 * @param body
	 * @return
	 */
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String body) {
		UserVO exUser = null;
		try {
			exUser = new ObjectMapper().readValue(body, UserVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		userService.delete(exUser);
		return checkNull(exUser);
	}
	
	/**
	 * Finds an user by Id, then deletes it
	 * @param userId
	 * @return
	 */
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteById(@PathVariable int userId) {
		UserVO exUser = userService.deleteById(userId);
		return checkNull(exUser);
	}
	
	
	/**
	 * Returns all the existing users rfrom the DB
	 * @return
	 */
	
	@GetMapping("/listAll")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
	}
	
	/**
	 * Finds an user by it's ID, then returns it
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/find/{userId}")
	public ResponseEntity<?> findById(@PathVariable int userId) {
		UserVO user = userService.findById(userId);
		return checkNull(user);
	}
	
	/**
	 * Attemps to log with an username or email and a password, then returns a JWT
	 * @param body
	 * @return JWT token
	 */
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody String body){
		System.out.println(body);
		UserVO user = null;
		try {
			user = checkIfUserExists(body);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(user== null) {
			System.out.println("no se encontró ese usuario");
			return null;
		}
		
		String JWT = Jwts.builder()
				.claim("activeUser", user)
				.setExpiration(new Date(System.currentTimeMillis()+ Constants.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, Constants.SUPER_SECRET_KEY)
				.compact();
		

		System.out.println(JWT);
		return new ResponseEntity<>(JWT ,HttpStatus.OK); 
		
	}

	/**
	 * Checks if a UserVO is correct, then returns an Ok httpStatus
	 * otherwise, returns a Bad_Request
	 * @param cat
	 * @return
	 */
	
	private ResponseEntity<?> checkNull(UserVO user) {
		if(user!=null) 
			return new ResponseEntity<>(user ,HttpStatus.OK);
		return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Checks if a username or email exists within the DB and if the password given checks out
	 * then, returns the UserVO
	 * @param body
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	
	private UserVO checkIfUserExists(String body) throws JsonMappingException, JsonProcessingException {
		JsonNode jsonBody = new ObjectMapper().readTree(body);
		System.out.println(jsonBody);
		UserVO user = null;

		if (jsonBody.has("login")) {
			user = userService.listAll().stream().filter(userVO -> jsonBody.path("login").asText().equals(userVO.getLogin())).findFirst().get();
			if(user==null)
				user = userService.listAll().stream().filter(userVO -> jsonBody.path("login").asText().equals(userVO.getEmail())).findFirst().get();
			else 
				return null;
		} else {
			return null;
		}
		
		if (jsonBody.has("password")&& jsonBody.path("password").asText().equals(user.getPassword()))
			return user;
		return null;
		
	}

}
