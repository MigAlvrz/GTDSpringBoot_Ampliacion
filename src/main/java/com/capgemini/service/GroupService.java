package com.capgemini.service;

import java.util.List;

import com.capgemini.model.GroupVO;

public interface GroupService {
	
	public GroupVO add(GroupVO group);
	public GroupVO delete(GroupVO group) throws IllegalArgumentException;
	public GroupVO deleteById(int id) throws IllegalArgumentException;
	public List<GroupVO> listAll();
	public GroupVO findById(int id) throws IllegalArgumentException;
	public GroupVO modify(GroupVO group);

}
