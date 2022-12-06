import { TypeAccountEnum } from "./enums";

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

export interface IBank {
    id?: number;
    name: string;
}

export interface IAccount {
    id?: number;
    user: IUserSignUp,
    agency: string,
    number: number,
    bank: IBank,
    typeAccount: TypeAccountEnum,
}