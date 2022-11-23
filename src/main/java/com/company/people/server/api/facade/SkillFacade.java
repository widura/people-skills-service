package com.company.people.server.api.facade;

import java.util.List;

import com.company.people.server.api.model.SkillEntity;

public interface SkillFacade {
  SkillEntity create(SkillEntity peopleEntity);

  SkillEntity update(SkillEntity peopleEntity);

  void delete(String id);

  SkillEntity getById(String id);

  List<SkillEntity> getAllSkills();
}
