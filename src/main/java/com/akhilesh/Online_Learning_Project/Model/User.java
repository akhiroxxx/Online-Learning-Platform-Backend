package com.akhilesh.Online_Learning_Project.Model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Document(collection = "users")
public class User implements UserDetails{
  @Id
  public String userId;
  public String name;
  public String username;
  public String password;
  public String role;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(this.getRole());
    
    return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getUsername(){
      return this.username;
    }

    @Override
    public String getPassword(){
      return this.password; 
    }

    @Override
    public boolean isAccountNonExpired(){
      return true;
    }

    @Override
    public boolean isAccountNonLocked(){
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired (){
      return true; 
    }

    @Override
    public boolean isEnabled(){
      return true;
    }

}
