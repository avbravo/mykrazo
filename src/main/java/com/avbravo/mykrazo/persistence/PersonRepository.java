package com.avbravo.mykrazo.persistence;

import com.avbravo.mykrazo.model.Person;
import org.jnosql.artemis.Repository;



import java.util.List;

public interface PersonRepository extends Repository<Person, String> {

    List<Person> findAll();
}
