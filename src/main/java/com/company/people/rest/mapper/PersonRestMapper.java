package com.company.people.rest.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.company.people.rest.model.Person;
import com.company.people.rest.model.PersonSkill;
import com.company.people.server.api.model.PeopleSkillEmbeddable;
import com.company.people.server.api.model.PersonEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonRestMapper implements RestMapper<PersonEntity, Person> {

  private final PersonSkillRestMapper personSkillRestMapper;

  @Override
  public Person convertToRest(PersonEntity serverObject) {
    Person people = new Person();

    people.setId(serverObject.getId());
    people.setName(serverObject.getName());

    List<PersonSkill> personSkills =
        personSkillRestMapper.convertToRest(new ArrayList<>(serverObject.getSkills()));
    people.setPersonalSkill(personSkills);

    return people;
  }

  @Override
  public PersonEntity convertToServer(Person restObject) {
    PersonEntity entity = new PersonEntity();
    entity.setId(restObject.getId());
    entity.setName(restObject.getName());
    Set<PeopleSkillEmbeddable> skills =
        personSkillRestMapper.convertToServer(new HashSet<>(restObject.getPersonalSkill()));

    entity.setSkills(skills);
    return entity;
  }
}
