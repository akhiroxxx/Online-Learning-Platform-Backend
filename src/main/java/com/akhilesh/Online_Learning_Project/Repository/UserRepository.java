package com.akhilesh.Online_Learning_Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.akhilesh.Online_Learning_Project.Model.User;


@Repository
public interface UserRepository extends MongoRepository<User,String>{
  @Query
  public User findByUsername(String username);
}
