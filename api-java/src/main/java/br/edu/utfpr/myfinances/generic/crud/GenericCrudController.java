package br.edu.utfpr.myfinances.generic.crud;

import br.edu.utfpr.myfinances.utils.ModelMapperUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

public abstract class GenericCrudController<T, DTO, ID extends Serializable> {

    private final GenericCrudService<T, ID> genericCrudService;
    private final ModelMapperUtils<T, DTO> modelMapperUtils;

    public GenericCrudController(GenericCrudService<T, ID> genericCrudService,
                                 Class<T> entityClass,
                                 Class<DTO> dtoClass) {
        this.genericCrudService = genericCrudService;
        this.modelMapperUtils = new ModelMapperUtils<>(entityClass, dtoClass);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid DTO requestBody) throws Exception {
        return ResponseEntity.ok(modelMapperUtils.convertToDto(
                genericCrudService.save(
                        modelMapperUtils.convertToEntity(requestBody))
        ));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid DTO requestBody) throws Exception {
        return ResponseEntity.ok(modelMapperUtils.convertToDto(
                genericCrudService.update(
                        modelMapperUtils.convertToEntity(requestBody))
        ));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        return ResponseEntity.ok(genericCrudService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(modelMapperUtils.convertListToDtoList(genericCrudService.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable ID id) {
        return ResponseEntity.ok(modelMapperUtils.convertToDto(genericCrudService.getById(id)));
    }

}
