package com.student.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student.model.Course;


@Repository
public interface CourseRepository extends MongoRepository<Course,Integer> 
{

}
