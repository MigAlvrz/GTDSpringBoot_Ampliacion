package com.capgemini.service;

import java.util.List;

import com.capgemini.model.GroupUserVO;

public interface GroupUserService {

	public GroupUserVO add(GroupUserVO groupUser) ;
	public GroupUserVO delete(GroupUserVO groupUser) throws IllegalArgumentException;
	public GroupUserVO deleteById(int id) throws IllegalArgumentException;
	public List<GroupUserVO> listAll();
	public GroupUserVO findById(int id) throws IllegalArgumentException;
}
