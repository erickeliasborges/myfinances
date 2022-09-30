package br.edu.utfpr.myfinances.generic.crud;

import java.util.List;

public abstract class GenericService<T, ID> {

    private final GenericRepository<T, ID> genericRepository;

    public GenericService(GenericRepository<T, ID> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public T save(T requestBody) throws Exception {
        return genericRepository.save(requestBody);
    }

    public String deleteById(ID id) {
        genericRepository.deleteById(id);
        return "Registro deletado com sucesso.";
    }

    public List<T> getAll() {
        return genericRepository.findAll();
    }

}
