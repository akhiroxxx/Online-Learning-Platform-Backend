package com.akhilesh.Online_Learning_Project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhilesh.Online_Learning_Project.Model.Course;
import com.akhilesh.Online_Learning_Project.Repository.CourseRepository;


@Service
public class courseService {
  @Autowired 
  private CourseRepository courseRepository;


  public List<Course> getAllCourses(){
    return courseRepository.findAll();
  }

  public boolean addCourse(Course c){
    courseRepository.save(c);
    return true;
  }

  public Course getCourse(String courseName){
      // return courseRepository.find({"courseName" : courseName});
      List<Course> allCourses=this.getAllCourses();
      for(int i=0;i<allCourses.size();i++)
      {
        if(courseName.equals(allCourses.get(i).getCourseName()))
        {
          return allCourses.get(i);
        }

      }
      Course x = new Course();
      return x;
  }

}
