package com.company.people.rest.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.company.people.rest.api.PeopleApiDelegate;
import com.company.people.rest.mapper.PersonRestMapper;
import com.company.people.rest.mapper.PersonSkillRestMapper;
import com.company.people.rest.model.Person;
import com.company.people.rest.model.PersonSkill;
import com.company.people.server.api.facade.PersonFacade;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PeopleApiDelegateImpl implements PeopleApiDelegate {

  private final PersonRestMapper personRestMapper;
  private final PersonSkillRestMapper personSkillRestMapper;
  private final PersonFacade personFacade;

  @Override
  public ResponseEntity<Person> create(Person person) {
    return ResponseEntity.ok(
        personRestMapper.convertToRest(
            personFacade.create(personRestMapper.convertToServer(person))));
  }

  @Override
  public ResponseEntity<Void> deletePerson(String id) {
    personFacade.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Person> getById(String id, String skillId, String level) {
    return ResponseEntity.ok(personRestMapper.convertToRest(personFacade.getById(id)));
  }

  @Override
  public ResponseEntity<Person> update(String id, Person people) {
    return ResponseEntity.ok(
        personRestMapper.convertToRest(
            personFacade.update(personRestMapper.convertToServer(people))));
  }

  @Override
  public ResponseEntity<List<PersonSkill>> createPersonSkill(
      String id, List<PersonSkill> personSkill) {
    return ResponseEntity.ok(
        personSkillRestMapper.convertToRest(
            new ArrayList<>(
                personFacade.create(id, personSkillRestMapper.convertToServer(personSkill)))));
  }

  @Override
  public ResponseEntity<List<PersonSkill>> getPersonSkill(String id) {
    return ResponseEntity.ok(
        personSkillRestMapper.convertToRest(new ArrayList<>(personFacade.getSkills(id))));
  }

  @Override
  public ResponseEntity<List<Person>> getAllPeople() {
    return ResponseEntity.ok(personRestMapper.convertToRest(personFacade.getAllPeople()));
  }
}
