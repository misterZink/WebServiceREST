package com.example.personsrest.service;

import com.example.personsrest.KeyCloakToken;
import com.example.personsrest.domain.CreatePerson;
import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonEntity;
import com.example.personsrest.domain.PersonRepository;
import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PersonService {

    PersonRepository personRepository;
    GroupRemote groupRemote;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(CreatePerson createPerson) {
        return personRepository.save(
                new PersonEntity(
                        createPerson.getName(),
                        createPerson.getAge(),
                        createPerson.getCity(),
                        List.of()));
    }

    public Person findById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person update(String id, String name, String city, int age) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setName(name);
            person.setCity(city);
            person.setAge(age);
            return personRepository.save(person);
        } else {
            return null;
        }
    }

    public Person delete(String id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            personRepository.delete(id);
            return person;
        } else {
            return null;
        }
    }

    public Page<Person> find(Map<String, String> searchParams) {
        PageRequest pageRequest = (searchParams.containsKey("pagesize") && searchParams.containsKey("pagenumber"))
                ? PageRequest.of(
                Integer.parseInt(searchParams.get("pagenumber")),
                Integer.parseInt(searchParams.get("pagesize")))
                : PageRequest.of(1, 20);

        return personRepository.findAllByNameContainingOrCityContaining(searchParams.get("search"), searchParams.get("search"), pageRequest);
    }

    public Person addGroup(String id, String groupName) {
        return personRepository.findById(id).map(person -> {
            person.addGroup(groupRemote.createGroup(groupName));
            return personRepository.save(person);
        }).orElse(null);
    }

    public String getGroupNameById(String groupId) {
        return groupRemote.getNameById(groupId);
    }

    public Person removeGroup(String id, String groupName) {
        return personRepository.findById(id).map(person -> {
            person.removeGroup(groupName);
            return personRepository.save(person);
        }).orElse(null);
    }
}
