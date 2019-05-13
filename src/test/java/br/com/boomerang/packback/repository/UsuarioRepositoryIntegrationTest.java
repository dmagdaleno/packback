package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Endereco;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.domain.builder.EnderecoBuilder;
import br.com.boomerang.packback.domain.builder.UsuarioBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository repositorio;

    @Before
    public void setup() {
        repositorio.deleteAll();
    }

    @Test
    public void deveCadastrarUmUsuarioComUmEndereco() {
        Usuario usuario = criaUmUsuarioComUmEndereco();

        repositorio.save(usuario);

        List<Usuario> usuarios = getUsuariosDoBanco();
        assertThat(usuarios.size()).isEqualTo(1);

        Usuario usuarioDoBanco = usuarios.get(0);
        assertThat(usuarioDoBanco.getNome()).isEqualTo("João da Silva");

        List<Endereco> enderecos = usuarioDoBanco.getEnderecos();
        assertThat(enderecos.size()).isEqualTo(1);
        assertThat(enderecos.get(0).getRua()).isEqualTo("Rua Muito Bacana");
    }

    public List<Usuario> getUsuariosDoBanco() {
        return List.copyOf(repositorio.findAll());
    }

    @Test
    public void deveCadastrarUmUsuarioComDoisEnderecos() {
        Usuario usuario = criaUsuarioComDoisEnderecos();

        repositorio.save(usuario);

        List<Usuario> usuarios = getUsuariosDoBanco();
        assertThat(usuarios.size()).isEqualTo(1);

        Usuario usuarioDoBanco = usuarios.get(0);
        assertThat(usuarioDoBanco.getNome()).isEqualTo("João da Silva");

        List<Endereco> enderecos = usuarioDoBanco.getEnderecos();
        assertThat(enderecos.size()).isEqualTo(2);
        assertThat(enderecos.get(0).getRua()).isEqualTo("Rua Muito Bacana");
        assertThat(enderecos.get(1).getRua()).isEqualTo("Nova Rua");
    }

    @Test
    public void deveRetornarOptionalEmptyCasoOUsuarioNaoExista() {
        Optional<Usuario> usuario = repositorio.findById(100L);
        assertThat(usuario.isEmpty()).isTrue();
    }

    public Usuario criaUsuarioComDoisEnderecos() {
        Usuario usuario = criaUmUsuarioComUmEndereco();
        Endereco novoEndereco = new EnderecoBuilder().naRua("Nova Rua").numero(100).constroi();
        usuario.adicionaEndereco(novoEndereco);
        return usuario;
    }

    public Usuario criaUmUsuarioComUmEndereco() {
        var endereco = new EnderecoBuilder()
                .naRua("Rua Muito Bacana")
                .numero(2)
                .constroi();

        return new UsuarioBuilder()
                .comNome("João da Silva")
                .comCpf("00100200304")
                .adicionaEndereco(endereco)
                .constroi();
    }
}
