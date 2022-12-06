import { IUserLogin, IUserSignUp } from '../commons/interfaces';
import { api } from '../lib/axios'

const signup = (user: IUserSignUp) => {
    return api.post('/v1/users', user);
}

const loggedUser = () => {
    return api.get('/v1/users/bytoken');
}

const login = (user: IUserLogin) => {
    return api.post('/login', user);
}

const isAuthenticated = () => {
    const token = localStorage.getItem('token');
    if (token) {
        api.defaults.headers.common['Authorization'] = `Bearer ${JSON.parse(token)}`;
    }
    return token ? true : false;
}

const logout = () => {
    localStorage.removeItem('token');
}

const AuthService = {
    signup,
    login,
    isAuthenticated,
    logout,
    loggedUser,
}
export default AuthService;