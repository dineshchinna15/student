package com.student.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "courses")
public class Course 
{
	@Id
	private Integer courseId;
	
	private Long courseFee;
	private String courseName;
	private String courseFaculty;
	private Integer courseDuration;
	


}
