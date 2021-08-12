package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.GroupUserVO;
import com.capgemini.repository.GroupUserRepository;
import com.capgemini.service.GroupUserService;

@Service
public class GroupUserServiceImplementation implements GroupUserService {

	@Autowired
	private GroupUserRepository GroupUserRepo;
	
	@Override
	public GroupUserVO add(GroupUserVO groupUser) {
		return GroupUserRepo.save(groupUser);
	}

	@Override
	public GroupUserVO delete(GroupUserVO groupUser) throws IllegalArgumentException{
		if(findById(groupUser.getIdgroupUser())!=null) {
			GroupUserRepo.delete(groupUser);
			return groupUser;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public GroupUserVO deleteById(int id) throws IllegalArgumentException{
		GroupUserVO GroupUser = findById(id);
		if(GroupUser!=null) {
			GroupUserRepo.deleteById(id);
			return GroupUser;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<GroupUserVO> listAll() {
		return GroupUserRepo.findAll();
	}

	@Override
	public GroupUserVO findById(int id) throws IllegalArgumentException{
		Optional<GroupUserVO> GroupUser = GroupUserRepo.findById(id);
		if(GroupUser.isPresent())
			return GroupUser.get();
		throw new IllegalArgumentException();
	}

	@Override
	public GroupUserVO modify(GroupUserVO groupUser) {
		return GroupUserRepo.save(groupUser);
	}

}
