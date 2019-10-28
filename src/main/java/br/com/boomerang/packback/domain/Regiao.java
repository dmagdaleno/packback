package br.com.boomerang.packback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Regiao {

    @Id
    @SequenceGenerator(name = "regiao_seq", sequenceName = "regiao_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regiao_seq")
    private Long id;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Produto> produtos;

    private String loja;

    private String setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Collection<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regiao regiao = (Regiao) o;
        return Objects.equals(id, regiao.id) &&
                Objects.equals(produtos, regiao.produtos) &&
                Objects.equals(loja, regiao.loja) &&
                Objects.equals(setor, regiao.setor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtos, loja, setor);
    }

    @Override
    public String toString() {
        return "Regiao{" +
                "id=" + id +
                ", loja='" + loja + '\'' +
                ", setor='" + setor + '\'' +
                '}';
    }
}