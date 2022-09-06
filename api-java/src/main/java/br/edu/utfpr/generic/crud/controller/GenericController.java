package br.edu.utfpr.generic.crud.controller;

import br.edu.utfpr.generic.crud.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

public abstract class GenericController<T> {

    @Autowired
    private GenericService<T> genericService;

    @PostMapping
    public ResponseEntity save(@RequestBody T requestBody) {
        return genericService.save(requestBody);
    }

}
