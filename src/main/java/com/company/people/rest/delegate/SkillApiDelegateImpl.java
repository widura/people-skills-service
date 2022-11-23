package com.company.people.rest.delegate;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.company.people.rest.mapper.SkillRestMapper;
import com.company.people.rest.api.SkillsApiDelegate;
import com.company.people.rest.model.Skill;
import com.company.people.server.api.facade.SkillFacade;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SkillApiDelegateImpl implements SkillsApiDelegate {

  private final SkillFacade skillFacade;
  private final SkillRestMapper skillRestMapper;

  @Override
  public ResponseEntity<Skill> create(Skill skill) {
    return ResponseEntity.ok(
        skillRestMapper.convertToRest(skillFacade.create(skillRestMapper.convertToServer(skill))));
  }

  @Override
  public ResponseEntity<Void> delete(String id) {
    skillFacade.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Skill> getById(String id) {
    return ResponseEntity.ok(skillRestMapper.convertToRest(skillFacade.getById(id)));
  }

  @Override
  public ResponseEntity<Skill> update(String id, Skill skill) {
    return ResponseEntity.ok(
        skillRestMapper.convertToRest(skillFacade.update(skillRestMapper.convertToServer(skill))));
  }

  @Override
  public ResponseEntity<List<Skill>> getAllSkills() {
    return ResponseEntity.ok(skillRestMapper.convertToRest(skillFacade.getAllSkills()));
  }
}
