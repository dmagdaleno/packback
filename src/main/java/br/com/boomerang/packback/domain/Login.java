package br.com.boomerang.packback.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Login {

    @Id
    @SequenceGenerator(name = "login_seq", sequenceName = "login_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    private Long id;

    private String senha;

    public Login() {
    }

    public Login(String senha) {
        this(null, senha);
    }

    public Login(Long id, String senha) {
        this.id = id;
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(id, login.id) &&
                Objects.equals(senha, login.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senha);
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                '}';
    }
}
