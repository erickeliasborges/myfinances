package br.edu.utfpr.myfinances.registrations.account.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bank")
public class Bank implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

}
