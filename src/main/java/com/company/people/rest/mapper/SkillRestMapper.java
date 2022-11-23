package com.company.people.rest.mapper;

import org.springframework.stereotype.Component;

import com.company.people.rest.model.Skill;
import com.company.people.server.api.model.SkillEntity;

@Component
public class SkillRestMapper implements RestMapper<SkillEntity, Skill> {

  @Override
  public Skill convertToRest(SkillEntity serverObject) {
    Skill skill = new Skill();

    skill.setId(serverObject.getId());
    skill.setName(serverObject.getName());
    skill.setDescription(serverObject.getDescription());

    return skill;
  }

  @Override
  public SkillEntity convertToServer(Skill restObject) {
    SkillEntity entity = new SkillEntity();

    entity.setId(restObject.getId());
    entity.setName(restObject.getName());
    entity.setDescription(restObject.getDescription());

    return entity;
  }
}
