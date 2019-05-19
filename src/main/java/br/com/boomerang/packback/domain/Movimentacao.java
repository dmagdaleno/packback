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

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario_origem", referencedColumnName = "id")
    private Usuario usuarioOrigem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario_destino", referencedColumnName = "id")
    private Usuario usuarioDestino;

    @ManyToOne
    @JoinColumn(name = "id_embalagem")
    private Embalagem embalagem;

    private LocalDateTime data;

    public Movimentacao() {
    }

    public Movimentacao(Long id, Usuario usuarioOrigem, Usuario usuarioDestino, Embalagem embalagem, LocalDateTime data) {
        this.id = id;
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
        this.embalagem = embalagem;
        this.data = data;
    }

    public boolean deveSerPontuada() {
        return usuarioOrigem.isTipoConsumidor() && usuarioDestino.isNotTipoConsumidor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public void setUsuarioOrigem(Usuario usuarioOrigem) {
        this.usuarioOrigem = usuarioOrigem;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
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
                Objects.equals(usuarioOrigem, that.usuarioOrigem) &&
                Objects.equals(usuarioDestino, that.usuarioDestino) &&
                Objects.equals(embalagem, that.embalagem) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioOrigem, usuarioDestino, embalagem, data);
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", usuarioOrigem=" + usuarioOrigem +
                ", usuarioDestino=" + usuarioDestino +
                ", embalagem=" + embalagem +
                ", data=" + data +
                '}';
    }
}
