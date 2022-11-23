package com.company.people.server.impl.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.company.people.server.api.exception.PersonNotFoundException;
import com.company.people.server.api.facade.PersonFacade;
import com.company.people.server.api.facade.SkillFacade;
import com.company.people.server.api.model.mongo.Person;
import com.company.people.server.api.model.mongo.PersonSkill;
import com.company.people.server.impl.repository.PersonRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonFacadeImpl implements PersonFacade {

  private final PersonRepository personRepository;
  private final SkillFacade skillFacade;

  @Override
  public Person create(Person person) {
    validatePersonSkill(new HashSet<>(person.getSkills()));
    return personRepository.save(person);
  }

  @Override
  public Set<PersonSkill> create(String id, List<PersonSkill> skills) {
    Person person = getById(id);
    HashSet<PersonSkill> skillSet = new HashSet<>(skills);

    validatePersonSkill(skillSet);
    person.setSkills(skillSet);
    Person savePersonEntity = personRepository.save(person);
    return savePersonEntity.getSkills();
  }

  private void validatePersonSkill(HashSet<PersonSkill> skillSet) {
    /** This validation can be improved with caching mechanism. */
    for (PersonSkill skill : skillSet) {
      skillFacade.getById(skill.getSkillId());
    }
  }

  @Override
  public Set<PersonSkill> getSkills(String id) {
    Person person = getById(id);
    return person.getSkills();
  }

  @Override
  public Person update(Person person) {
    return create(person);
  }

  @Override
  public void delete(String id) {
    Person person = getById(id);
    personRepository.delete(person);
  }

  @Override
  public Person getById(String id) {
    return personRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new PersonNotFoundException();
            });
  }

  @Override
  public List<Person> getAllPeople() {
    List<Person> allPeople = new ArrayList<>();
    personRepository.findAll().forEach(allPeople::add);
    return allPeople;
  }
}
