package br.edu.utfpr.myfinances.generic.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public abstract class GenericController<T, ID extends Serializable> {

    private final GenericService<T> genericService;

    public GenericController(GenericService<T> genericService) {
        this.genericService = genericService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid T requestBody) throws Exception {
        return saveOrUpdate(requestBody);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid T requestBody) throws Exception {
        return saveOrUpdate(requestBody);
    }

    private ResponseEntity saveOrUpdate(@RequestBody T requestBody) throws Exception {
        return ResponseEntity.ok(genericService.save(requestBody));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(genericService.deleteById(id));
    }

    @GetMapping
    public List<T> getAll() {
        return genericService.getAll();
    }

}
