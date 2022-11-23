package com.company.people.server.impl.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.company.people.server.api.exception.PersonNotFoundException;
import com.company.people.server.api.facade.SkillFacade;
import com.company.people.server.api.model.SkillEntity;
import com.company.people.server.impl.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SkillFacadeImpl implements SkillFacade {

  private final SkillRepository skillRepository;

  @Override
  public SkillEntity create(SkillEntity peopleEntity) {
    peopleEntity.setId(UUID.randomUUID().toString());
    return skillRepository.save(peopleEntity);
  }

  @Override
  public SkillEntity update(SkillEntity peopleEntity) {
    return skillRepository.save(peopleEntity);
  }

  @Override
  public void delete(String id) {
    SkillEntity skillEntity = getById(id);
    skillRepository.delete(skillEntity);
  }

  @Override
  public SkillEntity getById(String id) {
    return skillRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new PersonNotFoundException();
            });
  }

  @Override
  public List<SkillEntity> getAllSkills() {
    List<SkillEntity> allSkills = new ArrayList<>();
    skillRepository.findAll().forEach(allSkills::add);
    return allSkills;
  }
}
