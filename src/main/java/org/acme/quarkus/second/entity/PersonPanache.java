package org.acme.quarkus.second.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.countByName", query = "select count(*) from PersonPanache p where p.name = :name")
})
public class PersonPanache extends PanacheEntity {

    public String name;
    public LocalDate birth;

    public PersonPanache() {
    }

    public PersonPanache(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public static List<PersonPanache> findAllByName(String name) {
        return list("name", name);
    }

    public static long countByName(String name) {
        return count("#Person.countByName", Parameters.with("name", name).map());
    }
}
