package com.avbravo.mykrazo.model;

import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mvc.binding.MvcBinding;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Convert;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

@Entity
public class Rol {

    @FormParam("id")
    @Id
    @Convert(ObjectIdConverter.class)
    private String id;

    @FormParam("rol")
    @Column
    @NotEmpty
    @Size(min = 1, max = 20)
    @MvcBinding
    private String name;

   
    @FormParam("activo")
    @Column
    @NotEmpty
    @MvcBinding
    private String activo;

  
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rol person = (Rol) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", rol=").append(name);
        sb.append(", activo=").append(activo);
       
        sb.append('}');
        return sb.toString();
    }
}
