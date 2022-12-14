package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.account.dtos.AccountOverviewDTO;
import br.edu.utfpr.myfinances.registrations.movement.Movement;
import br.edu.utfpr.myfinances.registrations.movement.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService extends GenericCrudService<Account, Long> {

    private AccountRepository accountRepository;
    private MovementRepository movementRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, MovementRepository movementRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    public Boolean linkedRegister(Long id) {
        Optional<List<Movement>> optionalMovements = movementRepository.findByAccount_Id(id);
        return (optionalMovements.isPresent()) && (!optionalMovements.get().isEmpty());
    }

    @Override
    public String getMessageLinkedRegisterException() {
        return "Não é possível deletar pois a conta está em uso.";
    }

    public Optional<Account> findByAgencyAndNumberAndBank(Account account) {
        return accountRepository.findByAgencyAndNumberAndBank(account.getAgency(), account.getNumber(), account.getBank());
    }

    public List<Account> findByUser_Id(Long id) {
        return accountRepository.findByUser_Id(id).get();
    }

    public List<AccountOverviewDTO> findOverview() {
        List<Tuple> tupleList = accountRepository.findOverview();
        List<AccountOverviewDTO> accountOverviewDTOs = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            AccountOverviewDTO accountOverviewDTO = new AccountOverviewDTO();
//            TODO: melhoria futura: ajustar pois não é bom executar SQL dentro de loop, baixa muito o desempenho
            Optional<Account> optionalAccount = accountRepository.findById(((BigInteger) tuple.get("id")).longValue());
            if (optionalAccount.isPresent())
                accountOverviewDTO.setAccount(optionalAccount.get());
            accountOverviewDTO.setSaldo((BigDecimal) tuple.get("saldo"));
            accountOverviewDTO.setSaldoPrevisto((BigDecimal) tuple.get("saldo_previsto"));
            accountOverviewDTO.setReceitas((BigDecimal) tuple.get("receitas"));
            accountOverviewDTO.setReceitasPrevistas((BigDecimal) tuple.get("receitas_previstas"));
            accountOverviewDTO.setDespesas((BigDecimal) tuple.get("despesas"));
            accountOverviewDTO.setDespesasPrevistas((BigDecimal) tuple.get("despesas_previstas"));
            accountOverviewDTO.setTransferenciasRecebidas((BigDecimal) tuple.get("transferencias_recebidas"));
            accountOverviewDTO.setTransferenciasRecebidasPrevistas((BigDecimal) tuple.get("transferencias_recebidas_previstas"));
            accountOverviewDTO.setTransferenciasEnviadas((BigDecimal) tuple.get("transferencias_enviadas"));
            accountOverviewDTO.setTransferenciasEnviadasPrevistas((BigDecimal) tuple.get("transferencias_enviadas_previstas"));
            accountOverviewDTOs.add(accountOverviewDTO);
        }
        return accountOverviewDTOs;
    }

}
