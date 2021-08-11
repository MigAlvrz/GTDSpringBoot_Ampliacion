package com.capgemini.model;


import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TaskVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtask;

	@Column(nullable = false)
	private String title;

	@Column
	private String comments;

	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate created;

	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate planned;

	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate finished;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private UserVO userTask;

	@ManyToOne
	@JoinColumn(name = "idcategory")
	private CategoryVO categoryTask;

	@ManyToOne
	@JoinColumn(name = "idgroup")
	private GroupVO groupTask;

	public TaskVO() {
		super();
	}

	public TaskVO(String title, String comments, LocalDate created, LocalDate planned, LocalDate finished,
			UserVO userTask, CategoryVO categoryTask, GroupVO groupTask) {
		super();
		this.title = title;
		this.comments = comments;
		this.created = created;
		this.planned = planned;
		this.finished = finished;
		this.userTask = userTask;
		this.categoryTask = categoryTask;
		this.groupTask = groupTask;
	}

	public TaskVO(int idtask, String title, String comments, LocalDate created, LocalDate planned, LocalDate finished,
			UserVO userTask, CategoryVO categoryTask, GroupVO groupTask) {
		super();
		this.idtask = idtask;
		this.title = title;
		this.comments = comments;
		this.created = created;
		this.planned = planned;
		this.finished = finished;
		this.userTask = userTask;
		this.categoryTask = categoryTask;
		this.groupTask = groupTask;
	}

	public int getIdtask() {
		return idtask;
	}

	public void setIdtask(int idtask) {
		this.idtask = idtask;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getPlanned() {
		return planned;
	}

	public void setPlanned(LocalDate planned) {
		this.planned = planned;
	}

	public LocalDate getFinished() {
		return finished;
	}

	public void setFinished(LocalDate finished) {
		this.finished = finished;
	}

	public UserVO getUserTask() {
		return userTask;
	}

	public void setUserTask(UserVO userTask) {
		this.userTask = userTask;
	}

	public CategoryVO getCategoryTask() {
		return categoryTask;
	}

	public void setCategoryTask(CategoryVO categoryTask) {
		this.categoryTask = categoryTask;
	}

	public GroupVO getGroupTask() {
		return groupTask;
	}

	public void setGroupTask(GroupVO groupTask) {
		this.groupTask = groupTask;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryTask, comments, created, finished, groupTask, idtask, planned, title, userTask);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskVO other = (TaskVO) obj;
		return Objects.equals(categoryTask, other.categoryTask) && Objects.equals(comments, other.comments)
				&& Objects.equals(created, other.created) && Objects.equals(finished, other.finished)
				&& Objects.equals(groupTask, other.groupTask) && idtask == other.idtask
				&& Objects.equals(planned, other.planned) && Objects.equals(title, other.title)
				&& Objects.equals(userTask, other.userTask);
	}

	@Override
	public String toString() {
		return "TaskVO [idtask=" + idtask + ", title=" + title + ", comments=" + comments + ", created=" + created
				+ ", planned=" + planned + ", finished=" + finished + ", userTask=" + userTask + ", categoryTask="
				+ categoryTask + ", groupTask=" + groupTask + "]";
	}
	
}
