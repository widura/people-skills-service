package com.company.people.server.impl.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.people.server.api.model.mongo.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

  @Query(value = "{ 'skills.skillId' : ?0 }", count = true)
  long getPersonCountOfSkill(String skillId);
}
