package com.company.people.server.impl.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.company.people.server.api.exception.SkillNotFoundException;
import com.company.people.server.api.exception.SkillOperationException;
import com.company.people.server.api.facade.SkillFacade;
import com.company.people.server.api.model.mongo.Skill;
import com.company.people.server.impl.repository.PersonRepository;
import com.company.people.server.impl.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SkillFacadeImpl implements SkillFacade {

  private final SkillRepository skillRepository;
  private final PersonRepository personRepository;

  @Override
  public Skill create(Skill peopleEntity) {
    peopleEntity.setId(UUID.randomUUID().toString());
    return skillRepository.save(peopleEntity);
  }

  @Override
  public Skill update(Skill peopleEntity) {
    return skillRepository.save(peopleEntity);
  }

  @Override
  public void delete(String id) {

    Skill skillEntity = getById(id);

    long skillUsageCount = personRepository.getPersonCountOfSkill(id);

    if (skillUsageCount > 0) {
      throw new SkillOperationException();
    }

    skillRepository.delete(skillEntity);
  }

  @Override
  public Skill getById(String id) {
    return skillRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new SkillNotFoundException();
            });
  }

  @Override
  public List<Skill> getAllSkills() {
    List<Skill> allSkills = new ArrayList<>();
    skillRepository.findAll().forEach(allSkills::add);
    return allSkills;
  }
}
