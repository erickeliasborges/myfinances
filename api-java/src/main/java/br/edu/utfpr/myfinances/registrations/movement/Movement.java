package br.edu.utfpr.myfinances.registrations.movement;

import br.edu.utfpr.myfinances.registrations.account.Account;
import br.edu.utfpr.myfinances.registrations.category.Category;
import br.edu.utfpr.myfinances.registrations.movement.enums.TypeMovementEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movement_seq")
    @SequenceGenerator(name = "movement_seq", sequenceName = "movement_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_account"))
    @NotNull
    private Account account;

    @Column
    @NotNull
    @DecimalMin(message = "O valor deve ser maior que zero.", value = "0.1")
    private BigDecimal value;

    @Column(name = "due_date", columnDefinition = "timestamp")
    private LocalDateTime dueDate;

    @Column(name = "amount_paid")
    @DecimalMin(message = "O valor pago deve ser maior que zero.", value = "0.1")
    private BigDecimal amountPaid;

    @Column(name = "pay_date", columnDefinition = "timestamp")
    private LocalDateTime payDate;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_category"))
    @NotNull
    private Category category;

    @Column
    @NotNull
    @NotBlank
    private String description;

    @Column(name = "type_movement")
    @Comment(value = "RECEITA, DESPESA, TRANSFERENCIA_CONTAS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeMovementEnum typeMovement;

}
