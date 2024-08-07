package com.akhilesh.Online_Learning_Project.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akhilesh.Online_Learning_Project.Service.courseService;
import com.akhilesh.Online_Learning_Project.Model.Course;
import com.akhilesh.Online_Learning_Project.Repository.CourseRepository;


@RestController
@RequestMapping("/home")
public class home {

  @Autowired
  private courseService courseService;

  @GetMapping
  public String HomePage(){
    return "home_page";
  }

  @GetMapping("/myProfile")
  public String myProfilePage(){
    return "This is my customized profile based on whether, I am a Student, Instructor or Admin";
  }

  @GetMapping("/courses")
  public List<Course> allCourses(){
    return courseService.getAllCourses();
  }

  @GetMapping("/courses/{courseName}")
  public Course getCourse(@PathVariable String courseName){
    return courseService.getCourse(courseName);
  }
  
}
