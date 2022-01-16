package com.example.personsrest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

public class PersonRepositoryImpl implements PersonRepository {

    Map<String, Person> persons = new HashMap<>();

    public PersonRepositoryImpl() {
        Person person = new PersonEntity("Arne Anka", 12, "Stad", List.of());
        persons.put(person.getId(), person);
    }

    @Override
    public Optional<Person> findById(String id) {
        return persons.containsKey(id) ? Optional.of(persons.get(id)) : Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(persons.values());
    }

    @Override
    public Page<Person> findAllByNameContainingOrCityContaining(String name, String city, Pageable pageable) {
        List<Person> list = persons.values().stream()
                        .filter(person -> person.getName().equalsIgnoreCase(name))
                        .collect(Collectors.toList());
        return new PageImpl<>(list);
    }

    @Override
    public void deleteAll() {
        persons.clear();
    }

    @Override
    public Person save(Person person) {
        return persons.put(person.getId(), person);
    }

    @Override
    public void delete(String id) {
        persons.remove(id);
    }
}
