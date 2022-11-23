package com.company.people.rest.mapper;

import org.springframework.stereotype.Component;

import com.company.people.rest.model.PersonSkill;
import com.company.people.rest.model.SkillLevel;
import com.company.people.server.api.facade.SkillFacade;
import com.company.people.server.api.model.mongo.PersonSkillLevel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonSkillRestMapper
    implements RestMapper<com.company.people.server.api.model.mongo.PersonSkill, PersonSkill> {

  private final SkillFacade skillFacade;

  @Override
  public PersonSkill convertToRest(
      com.company.people.server.api.model.mongo.PersonSkill serverObject) {
    PersonSkill peopleSkill = new PersonSkill();
    peopleSkill.setSkillId(serverObject.getSkillId());
    peopleSkill.setLevel(SkillLevel.fromValue(serverObject.getLevel()));
    return peopleSkill;
  }

  @Override
  public com.company.people.server.api.model.mongo.PersonSkill convertToServer(
      PersonSkill restObject) {
    com.company.people.server.api.model.mongo.PersonSkill skillEmbeddable =
        new com.company.people.server.api.model.mongo.PersonSkill();
    skillEmbeddable.setSkillId(restObject.getSkillId());
    skillEmbeddable.setLevel(PersonSkillLevel.valueOf(restObject.getLevel().toString()).toString());
    return skillEmbeddable;
  }
}
