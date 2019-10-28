package br.com.boomerang.packback.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Produto {

    @Id
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    private Long id;

    @OneToOne
    private Embalagem embalagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        if(embalagem == null) return 0.0;

        return embalagem.calculaPontos() * 0.01;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(embalagem, produto.embalagem) &&
                Objects.equals(regiao, produto.regiao) &&
                Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, embalagem, regiao, descricao);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", embalagem=" + embalagem +
                ", id_regiao=" + (regiao != null ? regiao.getId() : 0) +
                ", descricao='" + descricao + '\'' +
                ", valor=" + getValor() +
                '}';
    }
}
