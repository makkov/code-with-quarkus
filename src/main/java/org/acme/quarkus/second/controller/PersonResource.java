package org.acme.quarkus.second.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.quarkus.second.entity.Person;
import org.acme.quarkus.second.entity.PersonPanache;
import org.acme.quarkus.second.service.PersonService;

import java.util.List;

@Path("/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @POST
    @Transactional
    public Person createPerson(Person personPanache) {
        return personService.createPerson(personPanache);
    }

    @GET
    @Path("/all")
    public List<Person> getAll(@QueryParam("name") String name) {
        return personService.getAllByName(name);
    }
}
