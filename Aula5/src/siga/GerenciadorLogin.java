package siga;

public class GerenciadorLogin {

    public Painel montarPainel(CriadorPainel criador) {

        Painel painel = criador.criarPainel();

        painel.montar();

        return painel;
    }
}
