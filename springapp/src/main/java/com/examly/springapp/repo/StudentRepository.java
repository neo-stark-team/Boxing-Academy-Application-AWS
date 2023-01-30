package com.examly.springapp.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Student;
import com.examly.springapp.model.User;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Student findByStudentEmail(String studentEmail);

	@Query(value = "select "
			+ "e.user_id as user_id, "
			+ "e.course_id as course_id, "
			+ "e.student_id as student_id, "
			+ "s.first_name as first_name, "
			+ "s.last_name as last_name, "
			+ "s.mobile_number as mobile_number, "
			+ "c.course_name as enrolled_course_name "
			+ "from "
			+ "students as s, "
			+ "course as c, "
			+ "enrolledcourse as e "
			+ "where "
			+ "e.student_id = s.student_id and "
			+ "e.course_id = c.course_id and ( "
			+ "s.student_id like %:keyword% or c.course_id like %:keyword% or s.first_name like %:keyword% or "
			+ "s.last_name like %:keyword% or "
			+ "s.mobile_number like %:keyword% or c.course_name like %:keyword%)", nativeQuery = true)
	public List<Object> findByKeyword(@Param("keyword") String keyword);

	public Set<Student> findByUser(User user);

}
