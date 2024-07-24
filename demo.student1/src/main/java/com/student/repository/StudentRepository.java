package com.student.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student.model.Student;


@Repository
public interface StudentRepository extends MongoRepository<Student,Integer>{

}
