package com.example.personsrest.service;

import com.example.personsrest.domain.PersonImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    public List<PersonImpl> all() {
        return List.of(new PersonImpl("Arne Anka", 12, "Stad"));
    }
}
