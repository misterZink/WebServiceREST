package com.example.personsrest.service;

import com.example.personsrest.domain.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    public List<PersonEntity> all() {
        return List.of(new PersonEntity("Arne Anka", 12, "Stad", List.of()));
    }
}
