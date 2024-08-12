package com.akhilesh.Online_Learning_Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.akhilesh.Online_Learning_Project.Model.Student;


@Repository
public interface StudentRepository extends MongoRepository<Student,String>{
  // @Query
  public Student findByUsername(String username);
  public void deleteByUsername(String username);
}
