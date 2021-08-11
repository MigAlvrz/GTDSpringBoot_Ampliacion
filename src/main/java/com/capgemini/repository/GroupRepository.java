package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.GroupVO;

@Repository
public interface GroupRepository extends JpaRepository<GroupVO, Integer> {

}
