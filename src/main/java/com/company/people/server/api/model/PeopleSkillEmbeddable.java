package com.company.people.server.api.model;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class PeopleSkillEmbeddable {
  @OneToOne private SkillEntity skill;
  private PeopleSkillLevel level;
}
