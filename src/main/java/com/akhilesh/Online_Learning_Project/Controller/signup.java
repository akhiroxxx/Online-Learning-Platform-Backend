package com.akhilesh.Online_Learning_Project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akhilesh.Online_Learning_Project.Model.Student;
import com.akhilesh.Online_Learning_Project.Service.studentService;


@RestController
public class signup {

  @Autowired
  private studentService studentService;


  @GetMapping("/signup")
  public String SignupPage(){
      return "signup page";
  }
  

  @PostMapping("/signup")
  public boolean SignUp(@RequestBody Student s){
    studentService.signup(s);
    return true;
  }
}
