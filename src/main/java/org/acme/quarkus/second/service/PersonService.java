package org.acme.quarkus.second.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.quarkus.second.entity.Person;
import org.acme.quarkus.second.repostiory.PersonRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        personRepository.persist(person);
        return person;
    }

    public List<Person> getAllByName(String name) {
        return personRepository.findAllByName(name);
    }
}
