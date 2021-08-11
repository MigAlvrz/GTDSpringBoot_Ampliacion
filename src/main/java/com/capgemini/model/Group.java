package com.capgemini.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Group {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long group_id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date creation_date;
	
	@OneToMany(mappedBy = "group")
	private List<Task> tasks;
	
	@OneToMany( mappedBy = "group")
	private List<GroupUser> groupUser;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User admin;

	public long getId() {
		return group_id;
	}

	public void setId(long id) {
		this.group_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public List<Task> getTask() {
		return tasks;
	}

	public void setTask(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<GroupUser> getGroupUser() {
		return groupUser;
	}

	public void setGroupUser(List<GroupUser> groupUser) {
		this.groupUser = groupUser;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creation_date, description, groupUser, group_id, name, tasks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(creation_date, other.creation_date) && Objects.equals(description, other.description)
				&& Objects.equals(groupUser, other.groupUser) && group_id == other.group_id && Objects.equals(name, other.name)
				&& Objects.equals(tasks, other.tasks);
	}
	
	

}
