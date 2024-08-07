package com.akhilesh.Online_Learning_Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhilesh.Online_Learning_Project.Model.Student;
import com.akhilesh.Online_Learning_Project.Model.User;
import com.akhilesh.Online_Learning_Project.Repository.StudentRepository;
import com.akhilesh.Online_Learning_Project.Repository.UserRepository;


@Service
public class studentService {
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private UserRepository userRepository;

  public boolean signup(Student s){
    Student x=new Student();
    x.setName(s.getName());
    x.setUsername(s.getUsername());
    x.setPassword(s.getPassword());
    x.setAddress(s.getAddress());
    studentRepository.save(x);
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
