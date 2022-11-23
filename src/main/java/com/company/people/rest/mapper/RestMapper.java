package com.company.people.rest.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface RestMapper<S, R> {
  default R convertToRest(final S serverObject) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  default S convertToServer(final R restObject) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  default List<R> convertToRest(final List<S> objects) {
    return objects != null
        ? (List) objects.stream().map(this::convertToRest).collect(Collectors.toList())
        : Collections.emptyList();
  }

  default Set<R> convertToRest(final Set<S> objects) {
    return objects != null
        ? (Set) objects.stream().map(this::convertToRest).collect(Collectors.toSet())
        : Collections.emptySet();
  }

  default List<S> convertToServer(final List<R> objects) {
    return objects != null
        ? (List) objects.stream().map(this::convertToServer).collect(Collectors.toList())
        : Collections.emptyList();
  }

  default Set<S> convertToServer(final Set<R> objects) {
    return objects != null
        ? (Set) objects.stream().map(this::convertToServer).collect(Collectors.toSet())
        : Collections.emptySet();
  }
}
