package com.akhilesh.Online_Learning_Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document(collection = "Students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
  private String address;
  private List<Course> coursesRegistered;
  private List<Message> myMessages;
}