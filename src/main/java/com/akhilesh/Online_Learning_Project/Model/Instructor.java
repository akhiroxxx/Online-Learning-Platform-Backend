package com.akhilesh.Online_Learning_Project.Model;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Instructors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User {
  private Integer age;

  private List<Course> coursesTaught=new ArrayList<>();
  
  private List<Message> myNotifications=new ArrayList<>();
}
