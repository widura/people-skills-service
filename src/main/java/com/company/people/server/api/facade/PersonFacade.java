package com.company.people.server.api.facade;

import java.util.List;
import java.util.Set;

import com.company.people.server.api.model.mongo.Person;
import com.company.people.server.api.model.mongo.PersonSkill;

public interface PersonFacade {
  Person create(Person peopleEntity);

  Set<PersonSkill> create(String id, List<PersonSkill> skills);

  Set<PersonSkill> getSkills(String id);

  Person update(Person peopleEntity);

  void delete(String id);

  Person getById(String id);

  List<Person> getAllPeople();
}
