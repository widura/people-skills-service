package com.company.people.server.impl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.people.server.api.model.PersonEntity;

@Repository
public interface PeopleRepository extends CrudRepository<PersonEntity, String> {}
