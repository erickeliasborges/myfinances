import { IAccount } from '../commons/interfaces';
import { api } from '../lib/axios'

const save = (account: IAccount) => {
    return api.post('/account', account);
}

const findAll = () => {
    return api.get('/account');
}

const remove = (id: number) => {
    return api.delete(`/account/${id}`);
}

const findById = (id: number) => {
    return api.get(`/account/${id}`);
}

const findByUserId = (id: number) => {
    return api.get(`/account/user/${id}`);
}

const duplicatedAccount = (account: IAccount) => {
    return api.post(`/account/duplicated`, account);
}

const AccountService = {
    save,
    findAll,
    remove,
    findById,
    duplicatedAccount,
    findByUserId,
}

export default AccountService;