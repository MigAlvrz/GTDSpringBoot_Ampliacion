package com.capgemini.model;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GroupUserVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idgroupUser;
	
	@Column
	private boolean isAdmin;
	
	@ManyToOne
	@JoinColumn(name="iduser")
	private UserVO userGroupUser;
	
	@ManyToOne
	@JoinColumn(name="idgroup")
	private GroupVO groupGroupUser;

	public GroupUserVO() {
		super();
	}
	
	

	public GroupUserVO(boolean isAdmin, UserVO userGroupUser, GroupVO groupGroupUser) {
		super();
		this.isAdmin = isAdmin;
		this.userGroupUser = userGroupUser;
		this.groupGroupUser = groupGroupUser;
	}



	public GroupUserVO(int idgroupUser, boolean isAdmin, UserVO userGroupUser, GroupVO groupGroupUser) {
		super();
		this.idgroupUser = idgroupUser;
		this.isAdmin = isAdmin;
		this.userGroupUser = userGroupUser;
		this.groupGroupUser = groupGroupUser;
	}



	public int getIdgroupUser() {
		return idgroupUser;
	}



	public void setIdgroupUser(int idgroupUser) {
		this.idgroupUser = idgroupUser;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public UserVO getUserGroupUser() {
		return userGroupUser;
	}



	public void setUserGroupUser(UserVO userGroupUser) {
		this.userGroupUser = userGroupUser;
	}



	public GroupVO getGroupGroupUser() {
		return groupGroupUser;
	}



	public void setGroupGroupUser(GroupVO groupGroupUser) {
		this.groupGroupUser = groupGroupUser;
	}



	@Override
	public int hashCode() {
		return Objects.hash(groupGroupUser, idgroupUser, isAdmin, userGroupUser);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupUserVO other = (GroupUserVO) obj;
		return Objects.equals(groupGroupUser, other.groupGroupUser) && idgroupUser == other.idgroupUser
				&& isAdmin == other.isAdmin && Objects.equals(userGroupUser, other.userGroupUser);
	}



	@Override
	public String toString() {
		return "GroupUserVO [idgroupUser=" + idgroupUser + ", isAdmin=" + isAdmin + ", userGroupUser=" + userGroupUser
				+ ", groupGroupUser=" + groupGroupUser + "]";
	}

}
