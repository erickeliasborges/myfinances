package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.registrations.user.validations.UserUniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_username", columnNames = "username"))
@UserUniqueConstraint
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq")
    private Long id;

    //TODO: internacionalizar as mensagens de validação, criar um arquivo
    // ValidationMessages.properties na pasta resources, esse é o arquivo padrão (em inglês) que
    // irá cair caso não seja específicado o Accept-Language no header da request, para criar um
    // pt_br criar um arquivo na mesma pasta chamado ValidationMessages_pt_BR.properties,
    // e na request passar o Accept-Language como pt_BR

    @NotNull(message = "Parameter name is required.")
    @Size(min = 4, max = 255)
    private String name;

    @NotNull(message = "Parameter username is required.")
    @Size(min = 4, max = 255)
    @Column
    private String username;

    @NotNull(message = "Parameter password is required.")
    @Size(min = 6, max = 254)
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    @NotNull(message = "Parameter email is required.")
    private String email;

    @Override
    @Transient
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_USER");
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
