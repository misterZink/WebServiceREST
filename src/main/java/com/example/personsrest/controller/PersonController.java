package com.example.personsrest.controller;

import com.example.personsrest.domain.PersonImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @GetMapping
    public List<PersonImpl> all() {
        return List.of(new PersonImpl("Arne Anka", 12, "Stad"));
    }

}
