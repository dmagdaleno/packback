package br.com.boomerang.packback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pontuacao {

    @Id
    @SequenceGenerator(name = "pontuacao_seq", sequenceName = "pontuacao_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontuacao_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    private Double pontos;

    public Pontuacao() {
    }

    public Pontuacao(Long id, Usuario usuario, Double pontos) {
        this.id = id;
        this.usuario = usuario;
        this.pontos = pontos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pontuacao that = (Pontuacao) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(pontos, that.pontos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, pontos);
    }

    @Override
    public String toString() {
        return "Pontuacao{" +
                "id=" + id +
                ", usuario.id=" + usuario.getId() +
                ", pontos=" + pontos +
                '}';
    }
}
