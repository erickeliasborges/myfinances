package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.registrations.account.enums.TypeAccountEnum;
import br.edu.utfpr.myfinances.registrations.bank.Bank;
import br.edu.utfpr.myfinances.registrations.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "account")
@Table(uniqueConstraints = { @UniqueConstraint(name = "unique_account", columnNames = {"agency", "number", "bank_id"}) })
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user"))
    @NotNull
    private User user;

    @Column
    @NotNull
    private String agency;

    @Column
    @NotNull
    private Long number;

    @OneToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_bank"))
    @NotNull
    private Bank bank;

    @Column(name = "type_account")
    @Comment("CC, CP, CASA, OUTRA")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeAccountEnum typeAccount;

}
