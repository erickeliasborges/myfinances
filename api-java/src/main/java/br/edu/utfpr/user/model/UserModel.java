package br.edu.utfpr.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 4, max = 255)
    private String name;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    @Size(min = 6, max = 254)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

}
