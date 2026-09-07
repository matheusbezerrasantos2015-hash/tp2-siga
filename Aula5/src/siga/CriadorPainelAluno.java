package siga;

public class CriadorPainelAluno extends CriadorPainel {

    @Override
    public Painel criarPainel() {
        return new PainelAluno();
    }
}
