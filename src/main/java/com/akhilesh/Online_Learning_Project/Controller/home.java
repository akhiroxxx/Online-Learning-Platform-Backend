package com.akhilesh.Online_Learning_Project.Controller;
import java.util.List;
import java.util.UUID;


import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.akhilesh.Online_Learning_Project.Model.Instructor;
import com.akhilesh.Online_Learning_Project.Model.Message;
import com.akhilesh.Online_Learning_Project.Model.Student;
import com.akhilesh.Online_Learning_Project.Model.User;
import com.akhilesh.Online_Learning_Project.Repository.CourseRepository;
import com.akhilesh.Online_Learning_Project.Repository.InstructorRepository;
import com.akhilesh.Online_Learning_Project.Repository.MessageRepository;
import com.akhilesh.Online_Learning_Project.Repository.StudentRepository;
import com.akhilesh.Online_Learning_Project.Repository.UserRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/home")
public class home {

  @Autowired
  private courseService courseService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private customUserDetailService userDetailService;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private InstructorRepository instructorRepository;

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
  @PostMapping("/courses/instructor/{courseName}/{msg}")
  public Message sendNotif(@PathVariable String courseName, @PathVariable String msg){
    Message m=new Message();
    Course course_temp=this.getCourse(courseName);
    if(course_temp.getCourseName()==null)
      return m;
    m.setContent(msg);
    LocalDateTime now = LocalDateTime.now();
    m.setTime(now);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication!=null&authentication.isAuthenticated()){
      Object principal = authentication.getPrincipal();
      if(principal instanceof User){
        User user = (User) principal;
        Instructor i_temp=instructorRepository.findByUsername(user.getUsername());
        m.setSenderUserName(user.getUsername());
        i_temp.getMyNotifications().add(m);
        List<Message> x=course_temp.getCourseMessages();
        x.add(m);
        course_temp.setCourseMessages(x);
        courseRepository.deleteByCourseName(courseName);
        courseRepository.save(course_temp);
        //  deleting
        userRepository.deleteByUsername(user.getUsername());
        instructorRepository.deleteByUsername(user.getUsername());
        //  lines from signup
        i_temp.setPassword(passwordEncoder.encode(i_temp.getPassword()));
        instructorRepository.save(i_temp);
        User u=new User();
        u.setName(i_temp.getName());
        u.setUsername(i_temp.getUsername());
        u.setPassword(i_temp.getPassword());
        u.setRole("INSTRUCTOR");
        u.setUserId(UUID.randomUUID().toString());
        userRepository.save(u);   
      }
    }
    return m;
  }

  @SuppressWarnings("unchecked")
  @PostMapping("/courses/{courseName}/{msg}")
  public Message sendMessage(@PathVariable String courseName, @PathVariable String msg){
    // Message, Course and Student objects have to be modified.
    Message m=new Message();
    Course course_temp=this.getCourse(courseName);
    if(course_temp.getCourseName()==null)
      return m;
    m.setContent(msg);
    LocalDateTime now = LocalDateTime.now();
    m.setTime(now);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof User) {
        User user = (User) principal;
        Student s_temp=studentRepository.findByUsername(user.getUsername());
        m.setSenderUserName(user.getUsername());
        s_temp.getMyMessages().add(m);
        List<Message> x=course_temp.getCourseMessages();
        x.add(m);
        course_temp.setCourseMessages(x);
        courseRepository.deleteByCourseName(courseName);
        courseRepository.save(course_temp);


        //  deleting
        userRepository.deleteByUsername(user.getUsername());
        studentRepository.deleteByUsername(user.getUsername());
       //  lines from signup
        s_temp.setPassword(passwordEncoder.encode(s_temp.getPassword()));
        studentRepository.save(s_temp);
        User u=new User();
        u.setName(s_temp.getName());
        u.setUsername(s_temp.getUsername());
        u.setPassword(s_temp.getPassword());
        u.setRole("USER");
        u.setUserId(UUID.randomUUID().toString());
        userRepository.save(u);   
      }
  }
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
                 s_temp.getCoursesRegistered().add(course_temp);
                 course_temp.getStudentsEnrolled().add(user.getUsername());
                //  deleting
                 userRepository.deleteByUsername(user.getUsername());
                 studentRepository.deleteByUsername(user.getUsername());
                //  lines from signup
                 s_temp.setPassword(passwordEncoder.encode(s_temp.getPassword()));
                 studentRepository.save(s_temp);
                 User u=new User();
                 u.setName(s_temp.getName());
                 u.setUsername(s_temp.getUsername());
                 u.setPassword(s_temp.getPassword());
                 u.setRole("USER");
                 u.setUserId(UUID.randomUUID().toString());
                 userRepository.save(u);
                //  changes will be reflected in student but not course till here.. Afte deleting and adding to courseRepo change will
                //  be there too
                courseRepository.deleteByCourseName(courseName);
                courseRepository.save(course_temp);
            }
        }


    return true;
  }

  @PostMapping("/courses/{courseName}/deregister")
  public boolean deregistration(@PathVariable String courseName){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof User) {
                User user = (User) principal;
                //  System.out.println(user.getUsername());
                 Course course_temp=courseService.getCourse(courseName);
                 Student s_temp=studentRepository.findByUsername(user.getUsername());   
                 s_temp.getCoursesRegistered().remove(course_temp);
                 course_temp.getStudentsEnrolled().remove(user.getUsername());
                //  deleting
                 userRepository.deleteByUsername(user.getUsername());
                 studentRepository.deleteByUsername(user.getUsername());
                //  lines from signup
                 s_temp.setPassword(passwordEncoder.encode(s_temp.getPassword()));
                 studentRepository.save(s_temp);
                 User u=new User();
                 u.setName(s_temp.getName());
                 u.setUsername(s_temp.getUsername());
                 u.setPassword(s_temp.getPassword());
                 u.setRole("USER");
                 u.setUserId(UUID.randomUUID().toString());
                 userRepository.save(u);
                //  changes will be reflected in student but not course till here.. Afte deleting and adding to courseRepo change will
                //  be there too
                courseRepository.deleteByCourseName(courseName);
                courseRepository.save(course_temp);
            }
        }
    return true;
  }


  
}
