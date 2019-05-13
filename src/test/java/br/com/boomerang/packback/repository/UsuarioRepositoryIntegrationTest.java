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
        Usuario usuario = criaUsuarioComUmEndereco();

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

    @Test
    public void deveEditarUsuarioCadastrado() {
        var usuario = criaUsuarioComUmEndereco();
        var idUsuarioSalvo = repositorio.save(usuario).getId();

        Usuario usuarioSalvo = encontraUsuarioPorId(idUsuarioSalvo);

        assertThat(usuarioSalvo.getNome()).isEqualTo("João da Silva");

        usuario.setId(idUsuarioSalvo);
        usuario.setNome("Outro João");
        repositorio.save(usuario);

        var usuarioEditado = encontraUsuarioPorId(idUsuarioSalvo);

        assertThat(usuarioEditado.getNome()).isEqualTo("Outro João");
    }

    @Test
    public void deveRemoverUsuarioPorIdCadastrado() {
        var usuario = criaUsuarioComUmEndereco();
        var idUsuarioSalvo = repositorio.save(usuario).getId();

        Usuario usuarioSalvo = encontraUsuarioPorId(idUsuarioSalvo);

        assertThat(usuarioSalvo).isNotNull();

        repositorio.deleteById(idUsuarioSalvo);

        Usuario usuarioRemovido = encontraUsuarioPorId(idUsuarioSalvo);

        assertThat(usuarioRemovido).isNull();

    }

    public Usuario encontraUsuarioPorId(Long idUsuarioSalvo) {
        var optionalUsuario = repositorio.findById(idUsuarioSalvo);
        return optionalUsuario.orElse(null);
    }

    public Usuario criaUsuarioComDoisEnderecos() {
        Usuario usuario = criaUsuarioComUmEndereco();
        Endereco novoEndereco = new EnderecoBuilder().naRua("Nova Rua").numero(100).constroi();
        usuario.adicionaEndereco(novoEndereco);
        return usuario;
    }

    public Usuario criaUsuarioComUmEndereco() {
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
