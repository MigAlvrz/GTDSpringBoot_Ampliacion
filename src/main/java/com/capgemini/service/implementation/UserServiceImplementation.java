package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.repository.UserRepository;
import com.capgemini.service.UserService;
import com.capgemini.service.UserVO;

public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserVO add(UserVO user) {
		if(findById(user.getIduser())==null)
			return userRepo.save(user);
		return null;
	}

	@Override
	public UserVO delete(UserVO user) {
		if(findById(user.getIduser())!=null) {
			userRepo.delete(user);
			return user;
		}
		return null;
	}

	@Override
	public UserVO deleteById(int id) {
		UserVO user = findById(id);
		if(user!=null) {
			userRepo.deleteById(id);
			return user;
		}
		return null;
	}

	@Override
	public List<UserVO> listAll() {
		return userRepo.findAll();
	}

	@Override
	public UserVO findById(int id) {
		Optional<UserVO> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent())
			return optionalUser.get();
		return null;
	}

}
