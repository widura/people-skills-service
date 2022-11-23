package com.company.people.server.api.facade;

import java.util.List;
import java.util.Set;

import com.company.people.server.api.model.PeopleSkillEmbeddable;
import com.company.people.server.api.model.PersonEntity;

public interface PersonFacade {
  PersonEntity create(PersonEntity peopleEntity);

  Set<PeopleSkillEmbeddable> create(String id, List<PeopleSkillEmbeddable> skills);

  Set<PeopleSkillEmbeddable> getSkills(String id);

  PersonEntity update(PersonEntity peopleEntity);

  void delete(String id);

  PersonEntity getById(String id);

  List<PersonEntity> getAllPeople();
}
