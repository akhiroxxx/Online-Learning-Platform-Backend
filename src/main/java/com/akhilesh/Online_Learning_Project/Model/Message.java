package com.akhilesh.Online_Learning_Project.Model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  private String content;

  // username

  private String sender;

  private LocalDateTime time;
}