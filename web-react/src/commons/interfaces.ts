
export interface IUserSignUp {
    name: string;
    username: string;
    password: string;
    email: string;
}

export interface IUserLogin {
    username: string;
    password: string;
}

export interface ICategory {
    id?: number;
    name: string;
}