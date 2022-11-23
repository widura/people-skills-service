package com.company.people.server.api.model.mongo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Person")
public class Person {
  @Id private String id;
  private String name;
  private Set<PersonSkill> skills = new HashSet<>();
}
