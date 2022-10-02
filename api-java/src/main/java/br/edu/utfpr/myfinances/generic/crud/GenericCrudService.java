package br.edu.utfpr.myfinances.generic.crud;

import br.edu.utfpr.myfinances.apierror.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

public abstract class GenericCrudService<T, ID> {

    private final GenericCrudRepository<T, ID> genericCrudRepository;

    public GenericCrudService(GenericCrudRepository<T, ID> genericCrudRepository) {
        this.genericCrudRepository = genericCrudRepository;
    }

    public T save(T requestBody) throws Exception {
        return genericCrudRepository.save(requestBody);
    }

    public String deleteById(ID id) {
        genericCrudRepository.deleteById(id);
        return "Registro deletado com sucesso.";
    }

    public List<T> getAll() {
        return genericCrudRepository.findAll();
    }

    public T getById(ID id) {
        Optional<T> optionalUser = genericCrudRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new DataNotFoundException("Registro não encontrado.");

        return optionalUser.get();
    }

}
