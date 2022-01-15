package com.example.personsrest.controller;

import com.example.personsrest.domain.PersonImpl;
import com.example.personsrest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/persons")
public class PersonController {

    PersonService personService;

    @GetMapping
    public List<PersonImpl> all() {
        return personService.all();
    }

}
