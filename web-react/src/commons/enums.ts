export enum TypeAccountEnum {
    CC = "CC",
    CP = "CP",
    CASA = "CASA",
    OUTRA = "OUTRA"
}

export enum TypeMovementEnum {
    RECEITA = "Receita",
    DESPESA = "Despesa",
    TRANSFERENCIA_CONTAS = "Transferência entre contas"
}

export function getEnumByKey(_enum: any, key: any): string {
    return Object.values(_enum)[Object.keys(_enum).indexOf(key)] as string;
}

export function getEnumByValue(_enum: any, value: any): string {
    return Object.keys(_enum)[Object.values(_enum).indexOf(value)] as string;
}