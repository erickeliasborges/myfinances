import { TypeAccountEnum, TypeMovementEnum } from "./enums";

export interface IUserSignUp {
    id?: number,
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

export interface IMovement {
    id?: number;
    account: IAccount,
    value: number,
    dueDate: Date,
    amountPaid: number,
    typeAccount: TypeAccountEnum,
    payDate: Date,
    category: ICategory,
    description: string,
    typeMovement: TypeMovementEnum 
}