package com.capgemini.service;

import java.util.List;

import com.capgemini.model.UserVO;

public interface UserService {
	
	public UserVO add(UserVO user);
	public UserVO delete(UserVO user) throws IllegalArgumentException;
	public UserVO deleteById(int id) throws IllegalArgumentException;
	public List<UserVO> listAll();
	public UserVO findById(int id) throws IllegalArgumentException;
	public UserVO modfiy(UserVO user);

}
