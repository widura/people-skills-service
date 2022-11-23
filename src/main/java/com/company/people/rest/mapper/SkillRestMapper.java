package com.company.people.rest.mapper;

import org.springframework.stereotype.Component;

import com.company.people.rest.model.Skill;

@Component
public class SkillRestMapper
    implements RestMapper<com.company.people.server.api.model.mongo.Skill, Skill> {

  @Override
  public Skill convertToRest(com.company.people.server.api.model.mongo.Skill serverObject) {
    Skill skill = new Skill();

    skill.setId(serverObject.getId());
    skill.setName(serverObject.getName());
    skill.setDescription(serverObject.getDescription());

    return skill;
  }

  @Override
  public com.company.people.server.api.model.mongo.Skill convertToServer(Skill restObject) {
    com.company.people.server.api.model.mongo.Skill entity =
        new com.company.people.server.api.model.mongo.Skill();

    entity.setId(restObject.getId());
    entity.setName(restObject.getName());
    entity.setDescription(restObject.getDescription());

    return entity;
  }
}
