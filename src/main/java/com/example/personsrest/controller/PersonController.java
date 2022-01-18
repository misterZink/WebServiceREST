package com.example.personsrest.controller;

import com.example.personsrest.domain.*;
import com.example.personsrest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/persons")
public class PersonController {

    PersonService personService;

    @GetMapping
    public List<PersonDTO> findAll(@RequestParam(required = false) Map<String, String> searchParams) {
        return searchParams.isEmpty()
                ? personService.findAll().stream().map(this::toDTO).collect(Collectors.toList())
                : personService.find(searchParams).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public PersonDTO createPerson(@RequestBody CreatePerson createPerson) {
        return toDTO(personService.save(createPerson));
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") String id) {
        return toDTO(personService.findById(id));
    }

    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable("id") String id, @RequestBody UpdatePerson updatePerson) {
        return toDTO(
                personService.update(
                        id,
                        updatePerson.getName(),
                        updatePerson.getCity(),
                        updatePerson.getAge()));
    }

    @DeleteMapping("/{id}")
    public PersonDTO deletePerson(@PathVariable("id") String id) {
        return toDTO(personService.delete(id));
    }

    @PutMapping("/{id}/addGroup/{groupName}")
    public PersonDTO addGroup(@PathVariable("id") String id, @PathVariable("groupName") String groupName) {
        return toDTO(personService.addGroup(id, groupName));
    }

    @DeleteMapping("/{id}/removeGroup/{groupName}")
    public PersonDTO removeGroup(@PathVariable("id") String id, @PathVariable("groupName") String groupName) {
        return toDTO(personService.removeGroup(id, groupName));
    }

    public PersonDTO toDTO(Person person) {

        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCity(),
                person.getAge(),
                person.getGroups().stream()
                        .map(groupId -> personService.getGroupNameById(groupId))
                        .collect(Collectors.toList())
        );
    }

}
