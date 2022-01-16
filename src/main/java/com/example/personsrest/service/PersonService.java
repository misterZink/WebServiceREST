package com.example.personsrest.service;

import com.example.personsrest.domain.CreatePerson;
import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonEntity;
import com.example.personsrest.domain.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PersonService {

    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(CreatePerson createPerson) {
        return personRepository.save(new PersonEntity(createPerson.getName(),createPerson.getAge(),createPerson.getCity(),List.of()));
    }

    public Person findById(String id) {
        return personRepository.findById(id).orElse(null);
    }
}
