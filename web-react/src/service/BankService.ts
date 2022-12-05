import { IBank } from './../commons/interfaces';
import { api } from '../lib/axios'

const save = (bank: IBank) => {
    return api.post('/bank', bank);
}

const findAll = () => {
    return api.get('/bank');
}

const remove = (id: number) => {
    return api.delete(`/bank/${id}`);
}

const findById = (id: number) => {
    return api.get(`/bank/${id}`);
}

const BankService = {
    save,
    findAll,
    remove,
    findById,
}

export default BankService;