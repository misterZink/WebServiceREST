package com.example.personsrest.controller;

import com.example.personsrest.domain.PersonDTO;
import com.example.personsrest.domain.PersonEntity;
import com.example.personsrest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/persons")
public class PersonController {

    PersonService personService;

    @GetMapping
    public List<PersonDTO> all() {
        return personService.all().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO toDTO(PersonEntity personEntity) {
        return new PersonDTO(
                personEntity.getId(),
                personEntity.getName(),
                personEntity.getCity(),
                personEntity.getAge(),
                personEntity.getGroups());
    }

}
