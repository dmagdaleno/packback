package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Endereco;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.domain.builder.UsuarioBuilderUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
    @Transactional
    public void deveCadastrarUsuarioComUmEndereco() {
        Usuario usuario = UsuarioBuilderUtils.criaUsuarioConsumidor();

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
    @Transactional
    public void deveCadastrarUsuarioComDoisEnderecos() {
        Usuario usuario = UsuarioBuilderUtils.criaUsuarioComDoisEnderecos();

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
        var usuario = UsuarioBuilderUtils.criaUsuarioConsumidor();
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
        var usuario = UsuarioBuilderUtils.criaUsuarioConsumidor();
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
}
