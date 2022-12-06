package br.edu.utfpr.myfinances.registrations.account.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DuplicatedAccount {

    public DuplicatedAccount(Boolean duplicated) {
        this.duplicated = duplicated;
    }

    private Boolean duplicated;
    private String message = "A conta já existe para a agência, número e banco informado. Por favor, informe outra.";

}
