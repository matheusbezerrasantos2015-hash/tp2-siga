package siga;

public class CriadorPainelSecretaria extends CriadorPainel {

    @Override
    public Painel criarPainel() {
        return new PainelSecretaria();
    }
}
