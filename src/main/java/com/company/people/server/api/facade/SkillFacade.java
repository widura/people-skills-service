package com.company.people.server.api.facade;

import java.util.List;

import com.company.people.server.api.model.mongo.Skill;

public interface SkillFacade {
  Skill create(Skill peopleEntity);

  Skill update(Skill peopleEntity);

  void delete(String id);

  Skill getById(String id);

  List<Skill> getAllSkills();
}
