package com.student.service;

import java.util.List;

import com.student.model.Student;
import com.student.response.ResponseModel;

public interface StudentService 
{
	ResponseModel<Student> saveStudent(Student student);

	ResponseModel<List<Student>> getAllStudents();

	ResponseModel<Student> findStudentById(Integer studentId);

	ResponseModel<Student> deleteStudentById(Integer studentId);

	ResponseModel<Student> updateStudent(Integer studentId, Student student);
}
