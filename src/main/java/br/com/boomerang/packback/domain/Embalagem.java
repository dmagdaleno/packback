package br.com.boomerang.packback.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Embalagem {

    @Id
    @SequenceGenerator(name = "embalagem_seq", sequenceName = "embalagem_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "embalagem_seq")
    private Long id;

    @OneToOne
    private TipoEmbalagem tipo;

    private String descricao;

    private Double volume;

    private Double peso;

    public Embalagem() {
    }

    public Embalagem(Long id, TipoEmbalagem tipo, String descricao, Double volume, Double peso) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.volume = volume;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoEmbalagem getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmbalagem tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Embalagem embalagem = (Embalagem) o;
        return Objects.equals(id, embalagem.id) &&
                Objects.equals(tipo, embalagem.tipo) &&
                Objects.equals(descricao, embalagem.descricao) &&
                Objects.equals(volume, embalagem.volume) &&
                Objects.equals(peso, embalagem.peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, descricao, volume, peso);
    }

    @Override
    public String toString() {
        return "Embalagem{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", descricao='" + descricao + '\'' +
                ", volume=" + volume +
                ", peso=" + peso +
                '}';
    }
}
