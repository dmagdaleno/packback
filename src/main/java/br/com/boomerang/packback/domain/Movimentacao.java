package br.com.boomerang.packback.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Movimentacao {

    @Id
    @SequenceGenerator(name = "movimentacao_seq", sequenceName = "pontuacao_usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontuacao_usuario_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario_origem", referencedColumnName = "id")
    private Usuario usuarioOrigem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario_destino", referencedColumnName = "id")
    private Usuario usuarioDestino;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Embalagem> embalagens;

    private LocalDateTime data;

    public Movimentacao() {
    }

    public Movimentacao(
            Long id, Usuario usuarioOrigem, Usuario usuarioDestino, List<Embalagem> embalagens, LocalDateTime data) {
        this.id = id;
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
        this.embalagens = embalagens;
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

    public List<Embalagem> getEmbalagens() {
        return embalagens;
    }

    public void setEmbalagens(List<Embalagem> embalagens) {
        this.embalagens = embalagens;
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
                Objects.equals(embalagens, that.embalagens) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioOrigem, usuarioDestino, embalagens, data);
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", usuarioOrigem=" + usuarioOrigem +
                ", usuarioDestino=" + usuarioDestino +
                ", embalagens=" + embalagens +
                ", data=" + data +
                '}';
    }

    public Pontuacao calculaPontuacao() {
        var pontos = embalagens.stream()
                .map(Embalagem::calculaPontos)
                .reduce(.0, Double::sum);

        var pontuacao = new Pontuacao(null, usuarioOrigem, pontos);

        usuarioOrigem.adicionaPontuacao(pontuacao);

        return pontuacao;
    }
}
