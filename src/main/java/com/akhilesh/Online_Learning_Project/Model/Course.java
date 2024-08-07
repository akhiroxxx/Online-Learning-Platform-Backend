package com.akhilesh.Online_Learning_Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
  private String courseName;

 // username
 private String Instructor;


  private List<Message> courseMessages;
 

  // List<username>
  private List<String> studentsEnrolled;
}
