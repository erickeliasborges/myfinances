package br.edu.utfpr.generic.crud.service;

import br.edu.utfpr.generic.crud.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public abstract class GenericService<T> {

    private GenericRepository<T> genericRepository;

    public GenericService(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public ResponseEntity save(T requestBody) {
        ResponseEntity responseEntity;

        try {
            responseEntity = ResponseEntity.ok(genericRepository.save(requestBody));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }

        return responseEntity;
    }

}
