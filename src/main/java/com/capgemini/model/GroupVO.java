package com.capgemini.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GroupVO {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idgroup;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date creation_date;
	
	@OneToMany(mappedBy = "groupTask",cascade = CascadeType.ALL)
	private List<TaskVO> tasks;
	
	@OneToMany( mappedBy = "groupGroupUser",cascade = CascadeType.ALL)
	private List<GroupUserVO> users;

	public GroupVO() {
		super();
	}

	public GroupVO(String name, String description, Date creation_date, List<TaskVO> tasks, List<GroupUserVO> users) {
		super();
		this.name = name;
		this.description = description;
		this.creation_date = creation_date;
		this.tasks = tasks;
		this.users = users;
	}

	public GroupVO(int idgroup, String name, String description, Date creation_date, List<TaskVO> tasks,
			List<GroupUserVO> users) {
		super();
		this.idgroup = idgroup;
		this.name = name;
		this.description = description;
		this.creation_date = creation_date;
		this.tasks = tasks;
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creation_date, description, idgroup, name, tasks, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupVO other = (GroupVO) obj;
		return Objects.equals(creation_date, other.creation_date) && Objects.equals(description, other.description)
				&& idgroup == other.idgroup && Objects.equals(name, other.name) && Objects.equals(tasks, other.tasks)
				&& Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		return "GroupVO [idgroup=" + idgroup + ", name=" + name + ", description=" + description + ", creation_date="
				+ creation_date + ", tasks=" + tasks + ", users=" + users + "]";
	}
	
	

	
}
