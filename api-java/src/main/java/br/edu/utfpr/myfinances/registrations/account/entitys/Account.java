package br.edu.utfpr.myfinances.registrations.account.entitys;

import br.edu.utfpr.myfinances.registrations.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private Long number;

    @Column
    private Integer agency;
    
    @OneToOne
    private Bank bank;

    @OneToOne
    private TypeAccount type;

}
