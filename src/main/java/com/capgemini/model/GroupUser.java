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
public class GroupUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupUser_id;
	
	@Column
	private boolean isAdmin;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
	
	public GroupUser() {}

	public long getId() {
		return groupUser_id;
	}

	public void setId(long id) {
		this.groupUser_id = id;
	}

	public boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isAdmin, group, groupUser_id, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupUser other = (GroupUser) obj;
		return Objects.equals(isAdmin, other.isAdmin) && Objects.equals(group, other.group) && groupUser_id == other.groupUser_id
				&& Objects.equals(user, other.user);
	}
	
	

}
