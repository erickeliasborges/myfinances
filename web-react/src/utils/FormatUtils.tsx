export function formatDatePtBr(date: Date) {
    if ((date == null) || (date == undefined))
        return;
    return new Date(date).toLocaleString('pt-BR');
}

export function formatMoneyPtBr(value: number) {
    if ((value == null) || (value == undefined))
        return;
    return value.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
}
