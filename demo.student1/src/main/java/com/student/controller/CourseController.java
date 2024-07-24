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

import com.student.model.Course;
import com.student.model.Student;
import com.student.response.ResponseModel;
import com.student.service.CourseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/courses")
public class CourseController 
{
	
	@Autowired
    private CourseService courseService;
	
	
	@PostMapping("/saveCourses")
	public ResponseEntity<ResponseModel<Course>> saveCourse(@RequestBody Course course) {
	    ResponseModel<Course> saveCourse = courseService.saveCourse(course);
	    
	    log.info("Course saved successfully");
	    return new ResponseEntity<>(saveCourse, HttpStatus.OK);
	}

	
    
    @GetMapping("/getAllCourses")
    public ResponseEntity<ResponseModel<List<Course>>> getAllCourses()
    {
    	ResponseModel<List<Course>> model = courseService.getAllCourses();
        log.info("courses fetched successfully");
        return ResponseEntity.ok(model);
    }
    
    @GetMapping("/findById/{courseId}")
    public ResponseEntity<ResponseModel<Course>> findCourseById(Integer courseId)
    {
    	ResponseModel<Course> courseById = courseService.findCourseById(courseId);
    	
    	log.info("fetched course deatils by id successfully");
		return ResponseEntity.ok(courseById);
    }
    
    
   
    
    @PutMapping("/{courseId}")
    public ResponseEntity<ResponseModel<Course>> updateCourse(@PathVariable Integer courseId, @RequestBody Course course) {
        ResponseModel<Course> updateCourse = courseService.updateCourse(courseId, course);
        log.info("Updated course details successfully");
        return ResponseEntity.ok(updateCourse);
    }
    
    


    
  
    
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ResponseModel<Course>> deleteCourse(@PathVariable Integer courseId) {
       ResponseModel<Course> deleteCourseById = courseService.deleteCourseById(courseId);

        
        log.info("course deleted by id successfully");
        return ResponseEntity.ok(deleteCourseById);
    }
}