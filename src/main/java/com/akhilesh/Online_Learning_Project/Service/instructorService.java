package com.akhilesh.Online_Learning_Project.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhilesh.Online_Learning_Project.Model.Instructor;
import com.akhilesh.Online_Learning_Project.Model.User;
import com.akhilesh.Online_Learning_Project.Repository.InstructorRepository;
import com.akhilesh.Online_Learning_Project.Repository.UserRepository;

@Service
public class instructorService {
    @Autowired
  private InstructorRepository instructorRepository;

    @Autowired
    private UserRepository userRepository;
    public boolean addInstructor(Instructor i){
    instructorRepository.save(i);
    User u=new User();
    u.setName(i.getName());
    u.setUsername(i.getUsername());
    u.setPassword(i.getPassword());
    u.setRoles("INSTRUCTOR");
    u.setUserId(UUID.randomUUID().toString());
    userRepository.save(u);
    return true;
  }
  
}
