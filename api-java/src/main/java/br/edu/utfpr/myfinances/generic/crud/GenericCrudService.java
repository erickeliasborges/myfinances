package br.edu.utfpr.myfinances.generic.crud;

import br.edu.utfpr.myfinances.apierror.exception.DataNotFoundException;
import br.edu.utfpr.myfinances.apierror.exception.LinkedRegisterException;
import br.edu.utfpr.myfinances.generic.response.GenericResponse;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public abstract class GenericCrudService<T, ID> {

    private final GenericCrudRepository<T, ID> genericCrudRepository;
    private String defaultOrderBy = "id";

    public GenericCrudService(GenericCrudRepository<T, ID> genericCrudRepository) {
        this.genericCrudRepository = genericCrudRepository;
    }

    public GenericCrudService(GenericCrudRepository<T, ID> genericCrudRepository, String defaultOrderBy) {
        this.genericCrudRepository = genericCrudRepository;
        this.defaultOrderBy = defaultOrderBy;
    }

    public T update(T requestBody) throws Exception {
        return genericCrudRepository.save(requestBody);
    }

    public T save(T requestBody) throws Exception {
        return genericCrudRepository.save(requestBody);
    }

    public GenericResponse deleteById(ID id) {
        if (!genericCrudRepository.existsById(id))
            throw new DataNotFoundException("Registro não encontrado.");

        if (linkedRegister(id))
            throw new LinkedRegisterException(getMessageLinkedRegisterException());

        genericCrudRepository.deleteById(id);
        return new GenericResponse("Registro deletado com sucesso.");
    }

    public Boolean linkedRegister(ID id) {
        return false;
    }

    public String getMessageLinkedRegisterException() {
        return "";
    }

    public List<T> getAll() {
        return genericCrudRepository.findAll(Sort.by(Sort.Direction.ASC, defaultOrderBy));
    }

    public T getById(ID id) {
        Optional<T> optionalUser = genericCrudRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new DataNotFoundException("Registro não encontrado.");

        return optionalUser.get();
    }

}
