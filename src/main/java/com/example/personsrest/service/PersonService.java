package com.example.personsrest.service;

import com.example.personsrest.domain.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonService {

    List<PersonEntity> persons;

    public PersonService() {
        persons = List.of(new PersonEntity("Arne Anka", 12, "Stad", List.of()));
    }

    public List<PersonEntity> all() {
        return persons;
    }
}
