package br.com.boomerang.packback.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Movimentacao {

    @Id
    @SequenceGenerator(name = "movimentacao_seq", sequenceName = "pontuacao_usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontuacao_usuario_seq")
    private Long id;

    private TipoMovimentacao tipo;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_embalagem")
    private Embalagem embalagem;

    private LocalDateTime data;

    public Movimentacao() {
    }

    public Movimentacao(Long id, TipoMovimentacao tipo, Usuario usuario, Embalagem embalagem, LocalDateTime data) {
        this.id = id;
        this.tipo = tipo;
        this.usuario = usuario;
        this.embalagem = embalagem;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimentacao that = (Movimentacao) o;
        return Objects.equals(id, that.id) &&
                tipo == that.tipo &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(embalagem, that.embalagem) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, usuario, embalagem, data);
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", usuario=" + usuario +
                ", embalagem=" + embalagem +
                ", data=" + data +
                '}';
    }
}
