package com.akhilesh.Online_Learning_Project.Controller;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akhilesh.Online_Learning_Project.Service.courseService;
import com.akhilesh.Online_Learning_Project.Service.customUserDetailService;
import com.akhilesh.Online_Learning_Project.Service.studentService;
import com.akhilesh.Online_Learning_Project.Model.Course;
import com.akhilesh.Online_Learning_Project.Model.Message;
import com.akhilesh.Online_Learning_Project.Model.Student;
import com.akhilesh.Online_Learning_Project.Model.User;
import com.akhilesh.Online_Learning_Project.Repository.MessageRepository;
import com.akhilesh.Online_Learning_Project.Repository.StudentRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/home")
public class home {

  @Autowired
  private courseService courseService;



  @Autowired
  private customUserDetailService userDetailService;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private MessageRepository messageRepository;

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

  @SuppressWarnings("unchecked")
  @PostMapping("/courses/{courseName}/{msg}")
  public Message sendMessage(@PathVariable String courseName, @PathVariable String msg){
    Course c=this.getCourse(courseName);
    Message m = new Message();
    if(c.getCourseName()==null)
    return m;
    // to add in that course, against that sender and add messages to message-repo
    m.setContent(msg);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();
    System.out.println("Username is "+username);
    Student s=studentRepository.findByUsername(username);
    m.setSenderUserName(username);
    LocalDateTime now = LocalDateTime.now();
    m.setTime(now);
    
    // adding message to respective course
    try
    {
      c.getCourseMessages().add(m);
      System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
      for(int i=0;i<c.getCourseMessages().size();i++)
      {
        System.out.println(c.getCourseMessages().get(i)+" ");
      }
      System.out.println(" ");
      System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
    }
    catch(Exception e){
      System.out.println("not done"+ e);
    }


    // adding message to student object
    
    s.getMyMessages().add(m);

    messageRepository.save(m);
    return m;
  }


  @PostMapping("/courses/{courseName}/register")
  public boolean registration(@PathVariable String courseName){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof User) {
                User user = (User) principal;
                //  System.out.println(user.getUsername());
                 Course course_temp=courseService.getCourse(courseName);
                 Student s_temp=studentRepository.findByUsername(user.getUsername());   
                 System.out.println("Course is :"+course_temp);
                 System.out.println("Student is:"+s_temp);
                 s_temp.getCoursesRegistered().add(course_temp);
                 course_temp.getStudentsEnrolled().add(user.getUsername());
                 System.out.println("Course is :"+course_temp);
                 System.out.println("Student is:"+s_temp);
            }
        }


    return true;
  }











  
}
