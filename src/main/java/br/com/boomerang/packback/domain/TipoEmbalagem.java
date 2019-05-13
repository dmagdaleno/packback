package br.com.boomerang.packback.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TipoEmbalagem {

    @Id
    @SequenceGenerator(name = "tp_embalagem_seq", sequenceName = "tp_embalagem_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tp_embalagem_seq")
    private Long id;

    private String descricao;

    private String material;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoEmbalagem that = (TipoEmbalagem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, material);
    }

    @Override
    public String toString() {
        return "TipoEmbalagem{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
