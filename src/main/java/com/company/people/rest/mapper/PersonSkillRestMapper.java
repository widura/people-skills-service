package com.company.people.rest.mapper;

import org.springframework.stereotype.Component;

import com.company.people.rest.model.PersonSkill;
import com.company.people.rest.model.SkillLevel;
import com.company.people.server.api.facade.SkillFacade;
import com.company.people.server.api.model.PeopleSkillEmbeddable;
import com.company.people.server.api.model.PeopleSkillLevel;
import com.company.people.server.api.model.SkillEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonSkillRestMapper implements RestMapper<PeopleSkillEmbeddable, PersonSkill> {

  private final SkillFacade skillFacade;

  @Override
  public PersonSkill convertToRest(PeopleSkillEmbeddable serverObject) {
    PersonSkill peopleSkill = new PersonSkill();
    peopleSkill.setSkillId(serverObject.getSkill().getId());
    peopleSkill.setLevel(SkillLevel.fromValue(serverObject.getLevel().toString()));
    return peopleSkill;
  }

  @Override
  public PeopleSkillEmbeddable convertToServer(PersonSkill restObject) {
    PeopleSkillEmbeddable skillEmbeddable = new PeopleSkillEmbeddable();
    SkillEntity skillEntity = skillFacade.getById(restObject.getSkillId());
    skillEmbeddable.setSkill(skillEntity);
    skillEmbeddable.setLevel(PeopleSkillLevel.valueOf(restObject.getLevel().toString()));
    return skillEmbeddable;
  }
}
