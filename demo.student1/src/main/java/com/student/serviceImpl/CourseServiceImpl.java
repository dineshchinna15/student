package com.student.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.student.exception.RecordNotFound;
import com.student.model.Course;
import com.student.model.Student;
import com.student.repository.CourseRepository;
import com.student.response.ResponseModel;
import com.student.service.CourseService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
    private CourseRepository courseRepository;

	@Override
	public ResponseModel<Course> saveCourse(Course course) {
		ResponseModel<Course> model = new ResponseModel<Course>();
		try {
			Course save = courseRepository.save(course);
			model.setStatus(HttpStatus.OK.toString());
			model.setMessage("course saved sucefully");
			model.setData(save);
			return model;

		} catch (Exception e) {
			model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			model.setMessage("erroe while saving the data");
			model.setData(null);
			return model;
		}

	}
	
	

	
	
	
	
	
	
	

	@Override
	public ResponseModel<List<Course>> getAllCourses() {

		ResponseModel<List<Course>> model = new ResponseModel<List<Course>>();
		try {
			List<Course> all = courseRepository.findAll();

			model.setData(all);
			model.setMessage("fetched all courses successfully..");
			model.setStatus(HttpStatus.OK.toString());

			return model;

		} catch (Exception e) {
			model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			model.setMessage("error while saving the data");
			model.setData(null);
			return model;
		}
	}










	@Override
	public ResponseModel<Course> findCourseById(Integer courseId) {
	    ResponseModel<Course> responseModel = new ResponseModel<Course>();

	    try {
	        Optional<Course> course = courseRepository.findById(courseId);
	        if (course.isPresent()) {
	            responseModel.setData(course.get());
	            responseModel.setStatus(HttpStatus.OK.toString());
	            responseModel.setMessage("Course found successfully.");
	        } else {
	            throw new RecordNotFound("Record not found");
	        }
	    } catch (RecordNotFound e) {
	        responseModel.setData(null);
	        responseModel.setStatus(HttpStatus.NOT_FOUND.toString());
	        responseModel.setMessage(e.getMessage());
	    } catch (Exception e) {
	        responseModel.setData(null);
	        responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
	        responseModel.setMessage("Failed to fetch course details.");
	    }

	    return responseModel;
	}

		
		
		@Override
		public ResponseModel<Student> findStudentById(Integer studentId) {
			ResponseModel<Student> responseModel = new ResponseModel<Student>();

			try {
				Optional<Student> student = studentRepository.findById(studentId);

				if (student.isPresent()) {
					responseModel.setData(student.get());
					responseModel.setStatus(HttpStatus.OK.toString());
					responseModel.setMessage("find by id successfully fetched");
					return responseModel;
				} else {
					throw new RecordNotFound("record not found");
				}

			} catch (RecordNotFound e) {
				responseModel.setData(null);
				responseModel.setStatus(HttpStatus.NOT_FOUND.toString());
				responseModel.setMessage(e.getMessage());
				return responseModel;
			} catch (Exception e) {
				responseModel.setData(null);
				responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				responseModel.setMessage("failed to fetch");
				return responseModel;
			}
		}
		
		
		
		
		
		
		
		
		
		

	@Override
	public ResponseModel<Course> deleteCourseById(Integer courseId) 
	{
		ResponseModel<Course> model = new ResponseModel<Course>();
		try
		{
			Optional<Course> byId = courseRepository.findById(courseId);
			if(byId.isPresent())
			{
				courseRepository.deleteById(courseId);
				model.setData(byId.get());
				model.setMessage("deleted sucessfully");
				model.setStatus(HttpStatus.OK.toString());
				return model;
			}
			else {
				throw new RecordNotFound("record not found");
			}
			
		} catch (RecordNotFound e) {
			model.setData(null);
			model.setMessage("not found");
			model.setStatus(HttpStatus.NOT_FOUND.toString());
			return model;
		} catch (Exception e) {
			model.setData(null);
			model.setMessage("failed to delete");
			model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return model;
		}
	}
		











	@Override
	public ResponseModel<Course> updateCourse(Integer courseId, Course course)
	{
		ResponseModel<Course> model = new ResponseModel<Course>();
		try
		{
			Optional<Course> byId = courseRepository.findById(courseId);
			if(byId.isPresent())
			{
				Course newCourse = byId.get();
				newCourse.setCourseName(course.getCourseName());
				newCourse.setCourseDuration(course.getCourseDuration());
				newCourse.setCourseFaculty(course.getCourseFaculty());
				newCourse.setCourseFee(course.getCourseFee());
				
				Course save = courseRepository.save(newCourse);
				model.setData(save);
				model.setMessage("course details upadted");
				model.setStatus(HttpStatus.OK.toString());

				return model;
				
			}
			else
			{
				throw new RecordNotFound("Id not found");
			}
			
		} catch (RecordNotFound e) {
			model.setData(null);
			model.setMessage("Not found...");
			model.setStatus(HttpStatus.NOT_FOUND.toString());
			return model;
		}
	 catch (Exception e) {
		model.setData(null);
		model.setMessage("failed to update");
		model.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return model;
	 }
	}

	
}

