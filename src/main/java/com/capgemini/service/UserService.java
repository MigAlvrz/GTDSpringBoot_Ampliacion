package com.capgemini.service;

import java.util.List;

public interface UserService {
	
	public UserVO add(UserVO user);
	public UserVO delete(UserVO user);
	public UserVO deleteById(int id);
	public List<UserVO> listAll();
	public UserVO findById(int id);

}
