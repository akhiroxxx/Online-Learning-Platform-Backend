package com.akhilesh.Online_Learning_Project.Repository;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.akhilesh.Online_Learning_Project.Model.Message;


@Repository
public interface MessageRepository extends MongoRepository<Message,ObjectId>{
  
}
