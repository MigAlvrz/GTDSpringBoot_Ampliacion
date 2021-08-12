package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.model.GroupUserVO;
import com.capgemini.repository.GroupUserRepository;
import com.capgemini.service.GroupUserService;

public class GroupUserServiceImplementation implements GroupUserService {

	@Autowired
	private GroupUserRepository GroupUserRepo;
	
	@Override
	public GroupUserVO add(GroupUserVO groupUser) {
		return GroupUserRepo.save(groupUser);
	}

	@Override
	public GroupUserVO delete(GroupUserVO groupUser) {
		GroupUserRepo.delete(groupUser);
		return groupUser;
	}

	@Override
	public GroupUserVO deletebyId(int id) {
		GroupUserVO GroupUser = findById(id);
		if(GroupUser!=null) {
			GroupUserRepo.deleteById(id);
			return GroupUser;
		}
			
		return null;
	}

	@Override
	public List<GroupUserVO> listAll() {
		return GroupUserRepo.findAll();
	}

	@Override
	public GroupUserVO findById(int id) {
		Optional<GroupUserVO> GroupUser = GroupUserRepo.findById(id);
		if(GroupUser.isPresent())
			return GroupUser.get();
		return null;
	}

}
