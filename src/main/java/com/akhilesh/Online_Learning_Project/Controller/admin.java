package com.akhilesh.Online_Learning_Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhilesh.Online_Learning_Project.Model.Course;
import com.akhilesh.Online_Learning_Project.Model.Instructor;
import com.akhilesh.Online_Learning_Project.Service.courseService;


import com.akhilesh.Online_Learning_Project.Service.instructorService;

@RestController
@RequestMapping("/admin")
public class admin{
  @Autowired
  private courseService courseService;

  @Autowired
  private instructorService instructorService;

  @PostMapping("/addCourse")
  public boolean addCourse(@RequestBody Course course){
    courseService.addCourse(course);
    return true;
  }

  @PostMapping("/addInstructor")
  public boolean addInstructor(@RequestBody Instructor i){
    instructorService.addInstructor(i);
    return true;
  }
}