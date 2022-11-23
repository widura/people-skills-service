package com.company.people.server.impl.facade;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.company.people.server.api.exception.PersonNotFoundException;
import com.company.people.server.api.model.PersonEntity;
import com.company.people.server.impl.repository.PeopleRepository;

class PeopleFacadeImplTest {

  @InjectMocks private PeopleFacadeImpl peopleFacadeImpl;

  @Mock private PeopleRepository peopleRepositoryMock;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void create_whenPersonEntitySuccessfullySave_thenReturnSavedPersonEntity() {
    final PersonEntity personEntityMock = Mockito.mock(PersonEntity.class);
    Mockito.when(peopleRepositoryMock.save(personEntityMock))
        .thenReturn(Mockito.mock(PersonEntity.class));
    final PersonEntity result = peopleFacadeImpl.create(personEntityMock);
    Assertions.assertNotNull(result);
  }

  @Test
  void update_whenPersonEntitySuccessfullySave_thenReturnSavedPersonEntity() {
    final PersonEntity personEntityMock = Mockito.mock(PersonEntity.class);
    Mockito.when(peopleRepositoryMock.save(personEntityMock))
        .thenReturn(Mockito.mock(PersonEntity.class));
    final PersonEntity result = peopleFacadeImpl.update(personEntityMock);
    Assertions.assertNotNull(result);
  }

  @Test
  void update_whenPersonEntityFoundForGivenId_thenReturnPersonEntity() {
    final String personId = "1";
    PersonEntity personEntity = Mockito.mock(PersonEntity.class);
    Optional<PersonEntity> personEntityOptional = Optional.of(personEntity);
    Mockito.when(peopleRepositoryMock.findById(personId)).thenReturn(personEntityOptional);

    final PersonEntity result = peopleFacadeImpl.getById(personId);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(personEntity, result);
  }

  @Test
  void update_whenPersonEntityNotFoundForGivenId_thenThrowPersonNotFoundException() {
    final String personId = "1";
    Mockito.when(peopleRepositoryMock.findById(personId)).thenReturn(Optional.empty());

    Assertions.assertThrows(
        PersonNotFoundException.class, () -> peopleFacadeImpl.getById(personId));
  }

  @Test
  void delete_whenPersonEntityFoundForGivenId_thenD() {
    final String personId = "1";
    PersonEntity personEntity = Mockito.mock(PersonEntity.class);
    Optional<PersonEntity> personEntityOptional = Optional.of(personEntity);
    Mockito.when(peopleRepositoryMock.findById(personId)).thenReturn(personEntityOptional);

    peopleFacadeImpl.delete(personId);

    Mockito.verify(peopleRepositoryMock, Mockito.times(1)).delete(personEntity);
  }
}
