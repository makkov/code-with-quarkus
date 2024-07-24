package org.acme.quarkus.second.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.quarkus.second.entity.PersonPanache;

import java.util.List;

@Path("/person-panache")
public class PersonPanacheResource {

    @POST
    @Transactional
    public void create(PersonPanache personPanache) {
        personPanache.persist();
    }

    @GET
    @Path("/all")
    public List<PersonPanache> findAll(@QueryParam("name") String name) {
        return PersonPanache.findAllByName(name);
    }

    @GET
    @Path("/count-by-name")
    public List<PersonPanache> countByName(@QueryParam("name") String name) {
        return PersonPanache.findAllByName(name);
    }
}
