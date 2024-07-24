package com.student.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.student.exception.RecordNotFound;
import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.response.ResponseModel;
import com.student.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService 
{
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public ResponseModel<Student> saveStudent(Student student) 
	{
		ResponseModel<Student> model = new ResponseModel<Student>();
		try {
			
			Student save = studentRepository.save(student);
			model.setStatus(HttpStatus.OK.toString());
			model.setMessage("student saved sucefully");
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
	public ResponseModel<List<Student>> getAllStudents() {

		ResponseModel<List<Student>> model = new ResponseModel<List<Student>>();
		try {
			List<Student> all = studentRepository.findAll();

			model.setData(all);
			model.setMessage("fetched all students successfully..");
			model.setStatus(HttpStatus.OK.toString());

			return model;

		} catch (Exception e) {
			model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			model.setMessage("erroe while saving the data");
			model.setData(null);
			return model;
		}

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
	public ResponseModel<Student> updateStudent(Integer studentId, Student student) {
		ResponseModel<Student> model = new ResponseModel<Student>();
		try {
			Optional<Student> byId = studentRepository.findById(studentId);
			if (byId.isPresent()) {
				Student newObject = byId.get();

				newObject.setCourses(student.getCourses());
				newObject.setStudentEmail(student.getStudentEmail());
				newObject.setStudentFirstName(student.getStudentFirstName());
				newObject.setStudentLastName(student.getStudentLastName());
				newObject.setStudentId(student.getStudentId());
				newObject.setStudentPhoneNo(student.getStudentPhoneNo());

				Student save = studentRepository.save(newObject);

				model.setData(save);
				model.setMessage("student details upadted");
				model.setStatus(HttpStatus.OK.toString());

				return model;

			} else {
				throw new RecordNotFound("Id not found ");

			}

		} catch (RecordNotFound e) {
			model.setData(null);
			model.setMessage("Not found...");
			model.setStatus(HttpStatus.NOT_FOUND.toString());
			return model;

		} catch (Exception e) {
			model.setData(null);
			model.setMessage("failed to update");
			model.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return model;

		}

	}

	@Override
	public ResponseModel<Student> deleteStudentById(Integer studentId) {
		ResponseModel<Student> model = new ResponseModel<Student>();
		try {
			Optional<Student> byId = studentRepository.findById(studentId);
			if (byId.isPresent()) {
				studentRepository.deleteById(studentId);
				model.setData(byId.get());
				model.setMessage("deleted sucessfully");
				model.setStatus(HttpStatus.OK.toString());
				return model;

			} else {
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

  
}
