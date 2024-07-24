package com.student.service;

import java.util.List;

import com.student.model.Course;
import com.student.model.Student;
import com.student.response.ResponseModel;

public interface CourseService 
{
	ResponseModel<Course> saveCourse(Course course);
	
	ResponseModel<List<Course>> getAllCourses();
	
	ResponseModel<Course> findCourseById(Integer courseId);
	
	ResponseModel<Course> deleteCourseById(Integer courseId);
	
	ResponseModel<Course> updateCourse(Integer courseId, Course course);
	 
	
	

	
	
	

}
