package com.akhilesh.Online_Learning_Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhilesh.Online_Learning_Project.Model.Instructor;
import com.akhilesh.Online_Learning_Project.Repository.InstructorRepository;

@Service
public class instructorService {
    @Autowired
  private InstructorRepository instructorRepository;


    public boolean addInstructor(Instructor i){
    instructorRepository.save(i);
    return true;
  }
  
}
