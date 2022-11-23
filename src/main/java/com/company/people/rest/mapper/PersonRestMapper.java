package com.company.people.rest.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.company.people.rest.model.Person;
import com.company.people.rest.model.PersonSkill;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonRestMapper
    implements RestMapper<com.company.people.server.api.model.mongo.Person, Person> {

  private final PersonSkillRestMapper personSkillRestMapper;

  @Override
  public Person convertToRest(com.company.people.server.api.model.mongo.Person serverObject) {
    Person people = new Person();

    people.setId(serverObject.getId());
    people.setName(serverObject.getName());

    List<PersonSkill> personSkills =
        personSkillRestMapper.convertToRest(new ArrayList<>(serverObject.getSkills()));
    people.setPersonalSkill(personSkills);

    return people;
  }

  @Override
  public com.company.people.server.api.model.mongo.Person convertToServer(Person restObject) {
    com.company.people.server.api.model.mongo.Person entity =
        new com.company.people.server.api.model.mongo.Person();
    entity.setId(restObject.getId());
    entity.setName(restObject.getName());
    Set<com.company.people.server.api.model.mongo.PersonSkill> skills =
        personSkillRestMapper.convertToServer(new HashSet<>(restObject.getPersonalSkill()));

    entity.setSkills(skills);
    return entity;
  }
}
