package com.akhilesh.Online_Learning_Project.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akhilesh.Online_Learning_Project.Model.Student;
import com.akhilesh.Online_Learning_Project.Model.User;
import com.akhilesh.Online_Learning_Project.Repository.StudentRepository;
import com.akhilesh.Online_Learning_Project.Repository.UserRepository;
;

@Service
public class studentService {
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private UserRepository userRepository;


  @Autowired
  private PasswordEncoder passwordEncoder;

  public boolean signup(Student s){
    studentRepository.save(s);
    User u=new User();
    u.setName(s.getName());
    u.setUsername(s.getUsername());
    u.setPassword(passwordEncoder.encode(s.getPassword()));
    u.setRole("USER");
    u.setUserId(UUID.randomUUID().toString());
    userRepository.save(u);
    return true;
  }

  public boolean login(User s){
    User x=userRepository.findByUsername(s.getUsername());
    if(x==null)
    {
      System.out.println("Not found user");
      return false;
    }

    if((x.getPassword()).equals(s.getPassword()))
    return true;
 
    System.out.println("pw doesnt match");
    return false;
  }
}
