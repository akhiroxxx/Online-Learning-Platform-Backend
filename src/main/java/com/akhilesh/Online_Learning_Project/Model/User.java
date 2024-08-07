package com.akhilesh.Online_Learning_Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
  public String name;
  public String username;
  public String password;
  public String roles;
}
