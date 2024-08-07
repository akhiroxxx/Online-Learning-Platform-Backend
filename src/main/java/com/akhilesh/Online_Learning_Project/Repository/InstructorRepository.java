package com.akhilesh.Online_Learning_Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.akhilesh.Online_Learning_Project.Model.Instructor;


public interface InstructorRepository extends MongoRepository<Instructor,Integer>{
  
}
