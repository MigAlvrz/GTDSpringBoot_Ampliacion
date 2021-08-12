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
	public GroupVO add(GroupVO group){
		return grRepo.save(group);
	}

	@Override
	public GroupVO delete(GroupVO group) throws IllegalArgumentException{
		if(findById(group.getIdgroup())!=null) {
			grRepo.delete(group);
			return group;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public GroupVO deleteById(int id) throws IllegalArgumentException{
		GroupVO gr = findById(id);
		if(gr!=null) {
			grRepo.deleteById(id);
			return gr;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<GroupVO> listAll(){
		return grRepo.findAll();
	}

	@Override
	public GroupVO findById(int id) throws IllegalArgumentException{
		Optional<GroupVO> optionalGr = grRepo.findById(id);
		if(optionalGr.isPresent())
			return optionalGr.get();
		throw new IllegalArgumentException();
	}

	@Override
	public GroupVO modify(GroupVO group) {		
		return grRepo.save(group);
	}

}
