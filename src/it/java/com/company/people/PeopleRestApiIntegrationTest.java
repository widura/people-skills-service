package com.company.people;

import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.company.people.rest.model.Person;
import com.company.people.server.api.facade.PersonFacade;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PeopleRestApiIntegrationTest {
  @MockBean private PersonFacade personFacadeMock;

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void greetingShouldReturnDefaultMessage() {
    com.company.people.server.api.model.mongo.Person entity = new com.company.people.server.api.model.mongo.Person();
    entity.setId("1");
    entity.setName("name");
    entity.setSkills(Collections.emptySet());

    Mockito.when(personFacadeMock.create(Mockito.any(com.company.people.server.api.model.mongo.Person.class))).thenReturn(entity);

    Person personRequest = new Person();
    personRequest.setName("name");
    personRequest.setPersonalSkill(Collections.emptyList());

    Person person =
        this.restTemplate.postForObject(
            "http://localhost:" + port + "/people", personRequest, Person.class);

    Assertions.assertThat(person.getName()).isEqualTo("name");
    Assertions.assertThat(person.getId()).isEqualTo("1");
  }
}
