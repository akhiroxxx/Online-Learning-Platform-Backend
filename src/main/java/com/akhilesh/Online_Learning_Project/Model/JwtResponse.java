package com.akhilesh.Online_Learning_Project.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
  private String jwtToken;
  private String username;
}
