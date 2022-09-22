package br.edu.utfpr.myfinances.registrations.account.entitys;

import br.edu.utfpr.myfinances.registrations.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
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
