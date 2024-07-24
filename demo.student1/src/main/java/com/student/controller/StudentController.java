package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.response.ResponseModel;
import com.student.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public ResponseEntity<ResponseModel<Student>> saveStudent(@RequestBody Student student) {

		ResponseModel<Student> savedStudent = studentService.saveStudent(student);

		log.info("Student saved successfully");
		return new ResponseEntity<>(savedStudent, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseModel<List<Student>>> getAllStudents() {
		ResponseModel<List<Student>> responseModel = studentService.getAllStudents();
		log.info("Fetched all students successfully");

		return ResponseEntity.ok(responseModel);
	}

	@GetMapping("/findById/{studentId}")
	public ResponseEntity<ResponseModel<Student>> findStudentById(@PathVariable("studentId") Integer studentId) {

		ResponseModel<Student> student = studentService.findStudentById(studentId);
		
		log.info("fetched student details by id successfully");
		return ResponseEntity.ok(student);
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<ResponseModel<Student>> updateStudent(@PathVariable Integer studentId,
			@RequestBody Student student) {

		ResponseModel<Student> strudent = studentService.updateStudent(studentId, student);
		
		log.info("updated student details successfully");
		return ResponseEntity.ok(strudent);
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<ResponseModel<Student>> deleteStudentById(@PathVariable Integer studentId) {
		ResponseModel<Student> deleteStudentById = studentService.deleteStudentById(studentId);

		log.info("deleted student details successfully");
		return ResponseEntity.ok(deleteStudentById);
	}

}
