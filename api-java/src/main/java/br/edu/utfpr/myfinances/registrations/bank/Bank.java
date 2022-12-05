package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.registrations.bank.validations.BankUniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bank")
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_name_bank", columnNames = "name"))
@BankUniqueConstraint
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
    @SequenceGenerator(name = "bank_seq", sequenceName = "bank_seq")
    private Long id;

    @Column
    @NotNull
    private String name;

}
