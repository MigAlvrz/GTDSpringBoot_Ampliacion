package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.repository.GroupRepository;
import com.capgemini.service.GroupService;
import com.capgemini.service.GroupVO;

public class GroupServiceImplementation implements GroupService {
	
	@Autowired
	private GroupRepository grRepo;

	@Override
	public GroupVO add(GroupVO group) {
		if(findById(group.getId()==null))
			return grRepo.save(group);
		return null;
	}

	@Override
	public GroupVO delete(GroupVO group) {
		if(findById(group.getId())!=null) {
			grRepo.delete(group);
			return group;
		}
		return null;
	}

	@Override
	public GroupVO deleteById(int id) {
		if(findById(id)!=null) {
			grRepo.deleteById(id);
			return findById(id);
		}
		return null;
	}

	@Override
	public List<GroupVO> listAll() {
		return grRepo.findAll();
	}

	@Override
	public GroupVO findById(int id) {
		Optional<GroupVO> optionalGr = grRepo.findById(id);
		if(optionalGr.isPresent())
			return optionalGr.get();
		return null;
	}

}
