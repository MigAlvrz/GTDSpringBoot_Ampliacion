package com.capgemini.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserVO;

@Repository
public interface TaskRepository extends JpaRepository<TaskVO, Integer> {
	
	List<TaskVO> findByUserTaskAndCategoryTaskAndFinishedOrderByPlannedAsc(UserVO user,CategoryVO catInbox,LocalDate finished);

}
