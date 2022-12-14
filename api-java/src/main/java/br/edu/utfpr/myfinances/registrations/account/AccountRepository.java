package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import br.edu.utfpr.myfinances.registrations.bank.Bank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends GenericCrudRepository<Account, Long> {

    Optional<List<Account>> findByBank_Id(Long bank_id);

    Optional<List<Account>> findByUser_Id(Long user_id);

    Optional<Account> findByAgencyAndNumberAndBank(String agency, Long number, Bank bank);

    @Query(value = "select\n" +
            "\taccount_data.id,\n" +
            "\t(((account_data.receitas + account_data.transferencias_recebidas) - (account_data.despesas + account_data.transferencias_enviadas))) as saldo,\n" +
            "\t(((account_data.receitas_previstas + account_data.transferencias_recebidas_previstas) - (account_data.despesas_previstas + account_data.transferencias_enviadas_previstas))) as saldo_previsto,\t\n" +
            "\taccount_data.receitas,\n" +
            "\taccount_data.receitas_previstas,\n" +
            "\taccount_data.despesas,\n" +
            "\taccount_data.despesas_previstas,\n" +
            "\taccount_data.transferencias_recebidas,\n" +
            "\taccount_data.transferencias_recebidas_previstas,\n" +
            "\taccount_data.transferencias_enviadas,\n" +
            "\taccount_data.transferencias_enviadas_previstas\n" +
            "from\n" +
            "\t(\n" +
            "\tselect account.id,\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is null or movement.due_date <= current_timestamp) and\n" +
            "\t\t\t(movement.account_id = account.id)\n" +
            "\t\t\tand\n" +
            "\t\t\t((movement.type_movement = 'RECEITA'))) as receitas,\n" +
            "\t\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is null or movement.due_date <= current_timestamp) and\n" +
            "\t\t\t\t(movement.account_id = account.id)\n" +
            "\t\t\t\tand\n" +
            "\t\t\t\t((movement.type_movement = 'DESPESA'))) as despesas,\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is null or movement.due_date <= current_timestamp) and\n" +
            "\t\t\t\t(movement.destination_account_id = account.id)\n" +
            "\t\t\t\tand\n" +
            "\t\t\t\t((movement.type_movement = 'TRANSFERENCIA_CONTAS'))) as transferencias_recebidas,\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is null or movement.due_date <= current_timestamp) and\n" +
            "\t\t\t\t(movement.account_id = account.id)\n" +
            "\t\t\t\tand\n" +
            "\t\t\t\t((movement.type_movement = 'TRANSFERENCIA_CONTAS'))) as transferencias_enviadas,\t\t\t\t\n" +
            "(\n" +
            "\t\tselect\n" +
            "\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is not null or movement.due_date > current_timestamp) and\n" +
            "\t\t\t(movement.account_id = account.id)\n" +
            "\t\t\tand\n" +
            "\t\t\t((movement.type_movement = 'RECEITA'))) as receitas_previstas,\n" +
            "\t\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is not null or movement.due_date > current_timestamp) and\n" +
            "\t\t\t\t(movement.account_id = account.id)\n" +
            "\t\t\t\tand\n" +
            "\t\t\t\t((movement.type_movement = 'DESPESA'))) as despesas_previstas,\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is not null or movement.due_date > current_timestamp) and\n" +
            "\t\t\t\t(movement.destination_account_id = account.id)\n" +
            "\t\t\t\tand\n" +
            "\t\t\t\t((movement.type_movement = 'TRANSFERENCIA_CONTAS'))) as transferencias_recebidas_previstas,\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tcoalesce(sum(value), 0)\n" +
            "\t\tfrom\n" +
            "\t\t\t\tmovement\n" +
            "\t\twhere (movement.due_date is not null or movement.due_date > current_timestamp) and\n" +
            "\t\t\t\t(movement.account_id = account.id)\n" +
            "\t\t\t\tand\n" +
            "\t\t\t\t((movement.type_movement = 'TRANSFERENCIA_CONTAS'))) as transferencias_enviadas_previstas\n" +
            "\tfrom\n" +
            "\t\t\t\taccount) as account_data", nativeQuery = true)
    List<Tuple> findOverview();

}
