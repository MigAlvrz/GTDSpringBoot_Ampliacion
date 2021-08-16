package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.UserVO;
import com.capgemini.repository.UserRepository;
import com.capgemini.service.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	/**
	 * Muy bueno esto
	 */
	
	@Override
	public UserVO add(UserVO user) {
		return userRepo.save(user);
	}

	@Override
	public UserVO delete(UserVO user) throws IllegalArgumentException{
		if(findById(user.getIduser())!=null) {
			userRepo.delete(user);
			return user;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public UserVO deleteById(int id) throws IllegalArgumentException{
		UserVO user = findById(id);
		if(user!=null) {
			userRepo.deleteById(id);
			return user;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<UserVO> listAll() {
		return userRepo.findAll();
	}

	@Override
	public UserVO findById(int id) throws IllegalArgumentException{
		Optional<UserVO> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent())
			return optionalUser.get();
		throw new IllegalArgumentException();
	}

	@Override
	public UserVO modfiy(UserVO user) {
		return userRepo.save(user);
	}

}
