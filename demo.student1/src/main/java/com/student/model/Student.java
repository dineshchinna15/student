package com.student.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class Student 
{
	@Id
	private Integer studentId;
	
	private String studentFirstName;
	private String studentLastName;
	private String studentEmail;
	private Long studentPhoneNo;
	
	
	private Set<Course> courses = new HashSet<>();
	
}
