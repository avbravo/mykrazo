package com.avbravo.mykrazo.controller;

import com.avbravo.mykrazo.model.Errors;
import com.avbravo.mykrazo.model.Messages;
import com.avbravo.mykrazo.model.Rol;
import com.avbravo.mykrazo.persistence.RolRepository;
import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

import org.eclipse.krazo.engine.Viewable;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;



@Controller
@Path("rol")
public class RolController {

    private static final Supplier<WebApplicationException> NOT_FOUND_EXCEPTION = () -> new WebApplicationException(NOT_FOUND);
    
    @Inject
    private Models models;
    
    @Inject
    private Messages message;
    
    @Inject 
    private Errors erros;
    
    @Inject
    private BindingResult bindingResult;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private RolRepository repository;

    @GET
    @Path("new")
    public Viewable newElement() {
    	this.models.put("countries", getCountries());
        return new Viewable("insert");
    }

    @GET
    @Path("show")
    @View("list")
    public void list() {
        this.models.put("list", repository.findAll());
    }

    @POST
    @Path("add")
    public String add(@Valid @BeanParam Rol rol) {
    	if (bindingResult.isFailed()) {

    		this.getErros();
    		this.models.put("countries", getCountries());
    		this.models.put("rol", rol);
    		return "insert";

    	}
        repository.save(rol);
        message.setMessageRedirect("The " + rol.getName() + " was successfully registered ! ");
        return "redirect:rol/show";
    }

    @POST
    @Path("update")
    public String update(@Valid @BeanParam Rol rol) {
    	if (bindingResult.isFailed()) { 

    		this.getErros();
    		this.models.put("countries", getCountries());
    		this.models.put("rol", rol);
    		return "change";
    		
    	}
        repository.save(rol);
        message.setMessageRedirect("The " + rol.getName() + " was changed successfully ! ");
        return "redirect:rol/show";
    }

    @GET
    @Path("update/{id}")
    public Viewable update(@PathParam("id") String id) {

        Optional<Rol> rol = repository.findById(id);
        this.models.put("rol", rol.orElseThrow(NOT_FOUND_EXCEPTION));
        this.models.put("countries", getCountries());
        return new Viewable("change", models);
    }

    @GET
    @Path("remove/{id}")
    public String delete(@PathParam("id") String id) {
        repository.deleteById(id);
        message.setMessageRedirect("The register was successfully Excluded ! ");
        return "redirect:rol/show";
    }

    private String getCountryName(String country) {
    	return new Locale(country,country).getDisplayCountry(Locale.ENGLISH);
    }

    private List<String> getCountries() {
		return Arrays.stream(Locale.getISOCountries())
					 .map(country -> getCountryName(country))
					 .sorted((a, b) -> a.compareTo(b))
					 .collect(Collectors.toList());
    }
    
    private void getErros() {
    	erros.setErrors(
                bindingResult.getAllErrors().stream()
                .collect(toList()));
    }
}