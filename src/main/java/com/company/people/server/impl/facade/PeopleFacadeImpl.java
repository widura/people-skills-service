package com.company.people.server.impl.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.company.people.server.api.exception.PersonNotFoundException;
import com.company.people.server.api.facade.PersonFacade;
import com.company.people.server.api.model.PeopleSkillEmbeddable;
import com.company.people.server.api.model.PersonEntity;
import com.company.people.server.impl.repository.PeopleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PeopleFacadeImpl implements PersonFacade {

  private final PeopleRepository peopleRepository;

  @Override
  public PersonEntity create(PersonEntity peopleEntity) {
    return peopleRepository.save(peopleEntity);
  }

  @Override
  public Set<PeopleSkillEmbeddable> create(String id, List<PeopleSkillEmbeddable> skills) {
    PersonEntity personEntity = getById(id);
    personEntity.setSkills(new HashSet<>(skills));
    PersonEntity savePersonEntity = peopleRepository.save(personEntity);
    return savePersonEntity.getSkills();
  }

  @Override
  public Set<PeopleSkillEmbeddable> getSkills(String id) {
    PersonEntity personEntity = getById(id);
    return personEntity.getSkills();
  }

  @Override
  public PersonEntity update(PersonEntity peopleEntity) {
    return peopleRepository.save(peopleEntity);
  }

  @Override
  public void delete(String id) {
    PersonEntity personEntity = getById(id);
    peopleRepository.delete(personEntity);
  }

  @Override
  public PersonEntity getById(String id) {
    return peopleRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new PersonNotFoundException();
            });
  }

  @Override
  public List<PersonEntity> getAllPeople() {
    List<PersonEntity> allPeople = new ArrayList<>();
    peopleRepository.findAll().forEach(allPeople::add);
    return allPeople;
  }
}
