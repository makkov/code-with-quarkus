package org.acme.quarkus.second.repostiory;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.quarkus.second.entity.Person;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findAllByName(String name) {
        return list("name", name);
    }
}
