package com.company.people.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.people.rest.model.PeopleDataServiceError;
import com.company.people.rest.model.PeopleDataServiceErrorType;
import com.company.people.server.api.exception.PersonNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class PeopleSkillServiceExceptionHandler {

  @ExceptionHandler(value = {PersonNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public PeopleDataServiceError handleResourceNotFoundException(final PersonNotFoundException ex) {
    log.error(ex.getMessage(), ex);

    return new PeopleDataServiceError()
        .errorCode(PeopleDataServiceErrorType.DATA_NOT_FOUND)
        .errorDescription(ex.getMessage());
  }
}
