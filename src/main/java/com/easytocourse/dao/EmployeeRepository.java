package com.easytocourse.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.easytocourse.model.ActionItems;

@Repository
public interface EmployeeRepository extends JpaRepository<ActionItems, Integer>
{
	List<ActionItems> findByDuedateAfter(@Param("date") java.util.Date date);      //using method
}
