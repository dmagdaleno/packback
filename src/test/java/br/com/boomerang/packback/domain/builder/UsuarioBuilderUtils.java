package br.com.boomerang.packback.domain.builder;

import br.com.boomerang.packback.domain.Endereco;
import br.com.boomerang.packback.domain.Usuario;

public class UsuarioBuilderUtils {

    public static Usuario criaUsuarioConsumidor() {
        return criaUsuarioConsumidor("Nome do Consumidor");
    }

    public static Usuario criaUsuarioConsumidor(String nome) {
        var endereco = new EnderecoBuilder()
                .naRua("Rua do Consumidor")
                .numero(2)
                .constroi();

        return new UsuarioBuilder()
                .comNome(nome)
                .comCpf("00100200304")
                .comEmail("consumidor@gmail.com")
                .adicionaEndereco(endereco)
                .constroi();
    }

    public static Usuario criaUsuarioProdutor() {
        return criaUsuarioProdutor("Empresa Parceira");
    }

    public static Usuario criaUsuarioProdutor(String razaoSocial) {
        var endereco = new EnderecoBuilder()
                .naRua("Rua da Empresa")
                .numero(33)
                .constroi();

        return new UsuarioBuilder()
                .comNome("Dono da Empresa")
                .comRazaoSocial(razaoSocial)
                .comCpf("00100200345")
                .comCnpj("001002003000145")
                .comEmail("produtor@empresa.com.br")
                .adicionaEndereco(endereco)
                .constroi();
    }

    public static Usuario criaUsuarioComDoisEnderecos() {
        Usuario usuario = criaUsuarioConsumidor();
        Endereco novoEndereco = new EnderecoBuilder().naRua("Nova Rua").numero(100).constroi();
        usuario.adicionaEndereco(novoEndereco);
        return usuario;
    }
}
