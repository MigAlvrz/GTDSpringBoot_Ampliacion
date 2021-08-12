package com.capgemini.service;

import java.util.List;

import com.capgemini.model.GroupVO;

public interface GroupService {
	
	public GroupVO add(GroupVO group);
	public GroupVO delete(GroupVO group);
	public GroupVO deleteById(int id);
	public List<GroupVO> listAll();
	public GroupVO findById(int id);
	public GroupVO modify(GroupVO group);

}
