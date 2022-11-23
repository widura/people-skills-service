package com.company.people.server.api.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Skill")
public class Skill {
  @Id private String id;
  private String name;
  private String description;
}
