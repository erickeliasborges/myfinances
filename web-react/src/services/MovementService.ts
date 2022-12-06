import { IMovement } from '../commons/interfaces';
import { api } from '../lib/axios'

const save = (movement: IMovement) => {
    return api.post('/movement', movement);
}

const findAll = () => {
    return api.get('/movement');
}

const remove = (id: number) => {
    return api.delete(`/movement/${id}`);
}

const findById = (id: number) => {
    return api.get(`/movement/${id}`);
}

const MovementService = {
    save,
    findAll,
    remove,
    findById,
}

export default MovementService;