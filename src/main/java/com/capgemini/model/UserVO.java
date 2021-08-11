package com.capgemini.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class UserVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;

	@Column(unique = true)
	private String login;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Column
	@Type(type="yes_no")
	private boolean admin;
	
	@OneToMany(mappedBy = "userTask", cascade = CascadeType.ALL)
	private List<TaskVO> tasks;
	
	@OneToMany( mappedBy = "userCategory", cascade = CascadeType.ALL)
	private List<CategoryVO> categories;
	
	@OneToMany( mappedBy = "userGroupUser", cascade = CascadeType.ALL)
	private List<GroupUserVO> groups;

	public UserVO() {
		super();
	}

	public UserVO(String login, String email, String password, UserStatus status, boolean admin, List<TaskVO> tasks,
			List<CategoryVO> categories, List<GroupUserVO> groups) {
		super();
		this.login = login;
		this.email = email;
		this.password = password;
		this.status = status;
		this.admin = admin;
		this.tasks = tasks;
		this.categories = categories;
		this.groups = groups;
	}

	public UserVO(int iduser, String login, String email, String password, UserStatus status, boolean admin,
			List<TaskVO> tasks, List<CategoryVO> categories, List<GroupUserVO> groups) {
		super();
		this.iduser = iduser;
		this.login = login;
		this.email = email;
		this.password = password;
		this.status = status;
		this.admin = admin;
		this.tasks = tasks;
		this.categories = categories;
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		return Objects.hash(admin, categories, email, groups, iduser, login, password, status, tasks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		return admin == other.admin && Objects.equals(categories, other.categories)
				&& Objects.equals(email, other.email) && Objects.equals(groups, other.groups) && iduser == other.iduser
				&& Objects.equals(login, other.login) && Objects.equals(password, other.password)
				&& status == other.status && Objects.equals(tasks, other.tasks);
	}

	@Override
	public String toString() {
		return "UserVO [iduser=" + iduser + ", login=" + login + ", email=" + email + ", password=" + password
				+ ", status=" + status + ", admin=" + admin + ", tasks=" + tasks + ", categories=" + categories
				+ ", groups=" + groups + "]";
	}
	
	
	
	

}
