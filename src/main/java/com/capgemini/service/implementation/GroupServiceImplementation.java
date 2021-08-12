package com.capgemini.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.GroupVO;
import com.capgemini.repository.GroupRepository;
import com.capgemini.service.GroupService;

@Service
public class GroupServiceImplementation implements GroupService {
	
	@Autowired
	private GroupRepository grRepo;

	@Override
	public GroupVO add(GroupVO group) {
		if(findById(group.getIdgroup())==null)
			return grRepo.save(group);
		return null;
	}

	@Override
	public GroupVO delete(GroupVO group) {
		if(findById(group.getIdgroup())!=null) {
			grRepo.delete(group);
			return group;
		}
		return null;
	}

	@Override
	public GroupVO deleteById(int id) {
		GroupVO gr = findById(id);
		if(gr!=null) {
			grRepo.deleteById(id);
			return gr;
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
