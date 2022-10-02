package br.edu.utfpr.myfinances.registrations.movement.entitys;

import br.edu.utfpr.myfinances.registrations.account.entitys.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movement")
public class Movement implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Account account;

    @Column
    private BigDecimal value;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime dueDate;

    @Column
    private BigDecimal amountPaid;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime payDate;

    @Column
    private String category;

    @Column
    private String description;

    @OneToOne
    private TypeMovement typeMovement;

}
