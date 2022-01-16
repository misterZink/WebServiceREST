package com.example.personsrest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    List<Person> persons;

    public PersonRepositoryImpl() {
        persons = List.of(new PersonEntity("Arne Anka", 12, "Stad", List.of()));
    }

    @Override
    public Optional<Person> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return persons;
    }

    @Override
    public Page<Person> findAllByNameContainingOrCityContaining(String name, String city, Pageable pageable) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
