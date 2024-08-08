package com.akhilesh.Online_Learning_Project.Model;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
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
  public String roles;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
    }

    @Override
    public String getUsername(){
      return this.username;
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
