package com.example.personsrest.service;

import com.example.personsrest.domain.CreatePerson;
import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonEntity;
import com.example.personsrest.domain.PersonRepository;
import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
                        new ArrayList<>()));
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
            if (isUUID(groupName)) person.removeGroup(groupName);
            else person.getGroups().removeIf(group -> groupRemote.getNameById(group).equalsIgnoreCase(groupName));
            return personRepository.save(person);
        }).orElse(null);
    }

    private boolean isUUID(String groupName) {
        String uuidRegex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
        return Pattern.compile(uuidRegex).matcher(groupName).matches();
    }

}
