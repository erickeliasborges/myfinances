import { ICategory } from '../commons/interfaces';
import { api } from '../lib/axios'

const save = (category: ICategory) => {
    return api.post('/category', category);
}

const findAll = () => {
    return api.get('/category');
}

const remove = (id: number) => {
    return api.delete(`/category/${id}`);
}

const findById = (id: number) => {
    return api.get(`/category/${id}`);
}

const CategoryService = {
    save,
    findAll,
    remove,
    findById,
}

export default CategoryService;