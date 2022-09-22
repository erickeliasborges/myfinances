package br.edu.utfpr.myfinances.registrations.movement.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "typemovement")
public class TypeMovement implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String type;

}
