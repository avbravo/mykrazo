package com.avbravo.mykrazo.persistence;

import com.avbravo.mykrazo.model.Person;
import com.avbravo.mykrazo.model.Rol;
import org.jnosql.artemis.Repository;



import java.util.List;

public interface RolRepository extends Repository<Rol ,String> {

    List<Rol> findAll();
}
