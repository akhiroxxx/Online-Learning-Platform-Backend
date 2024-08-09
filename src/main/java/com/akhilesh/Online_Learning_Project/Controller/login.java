package com.akhilesh.Online_Learning_Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akhilesh.Online_Learning_Project.Model.User;
import com.akhilesh.Online_Learning_Project.Service.studentService;


@RestController
public class login {
  @Autowired
  private studentService studentService;
  @GetMapping("/login")
  public String LoginPage(){
    return "Login Page";
  }


  // Logging in is for Student, Instructor & Admin
  // @PostMapping("/login")
  // public boolean logging(@RequestBody User s){
  //   return studentService.login(s);
  // }
  
}
