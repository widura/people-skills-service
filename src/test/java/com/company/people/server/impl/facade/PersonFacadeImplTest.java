package com.company.people.server.impl.facade;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.company.people.server.api.exception.PersonNotFoundException;
import com.company.people.server.api.model.mongo.Person;
import com.company.people.server.impl.repository.PersonRepository;

class PersonFacadeImplTest {

  @InjectMocks private PersonFacadeImpl personFacadeImpl;

  @Mock private PersonRepository peopleRepositoryMock;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void create_whenPersonEntitySuccessfullySave_thenReturnSavedPersonEntity() {
    final Person personEntityMock = Mockito.mock(Person.class);
    Mockito.when(peopleRepositoryMock.save(personEntityMock))
        .thenReturn(Mockito.mock(Person.class));
    final Person result = personFacadeImpl.create(personEntityMock);
    Assertions.assertNotNull(result);
  }

  @Test
  void update_whenPersonEntitySuccessfullySave_thenReturnSavedPersonEntity() {
    final Person personEntityMock = Mockito.mock(Person.class);
    Mockito.when(peopleRepositoryMock.save(personEntityMock))
        .thenReturn(Mockito.mock(Person.class));
    final Person result = personFacadeImpl.update(personEntityMock);
    Assertions.assertNotNull(result);
  }

  @Test
  void update_whenPersonEntityFoundForGivenId_thenReturnPersonEntity() {
    final String personId = "1";
    Person personEntity = Mockito.mock(Person.class);
    Optional<Person> personEntityOptional = Optional.of(personEntity);
    Mockito.when(peopleRepositoryMock.findById(personId)).thenReturn(personEntityOptional);

    final Person result = personFacadeImpl.getById(personId);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(personEntity, result);
  }

  @Test
  void update_whenPersonEntityNotFoundForGivenId_thenThrowPersonNotFoundException() {
    final String personId = "1";
    Mockito.when(peopleRepositoryMock.findById(personId)).thenReturn(Optional.empty());

    Assertions.assertThrows(
        PersonNotFoundException.class, () -> personFacadeImpl.getById(personId));
  }

  @Test
  void delete_whenPersonEntityFoundForGivenId_thenD() {
    final String personId = "1";
    Person personEntity = Mockito.mock(Person.class);
    Optional<Person> personEntityOptional = Optional.of(personEntity);
    Mockito.when(peopleRepositoryMock.findById(personId)).thenReturn(personEntityOptional);

    personFacadeImpl.delete(personId);

    Mockito.verify(peopleRepositoryMock, Mockito.times(1)).delete(personEntity);
  }
}
