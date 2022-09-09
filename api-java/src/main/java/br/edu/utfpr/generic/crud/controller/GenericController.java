package br.edu.utfpr.generic.crud.controller;

import br.edu.utfpr.generic.crud.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public abstract class GenericController<T, ID extends Serializable> {

    private final GenericService<T> genericService;

    public GenericController(GenericService<T> genericService) {
        this.genericService = genericService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody T requestBody) {
        return genericService.save(requestBody);
    }

}
