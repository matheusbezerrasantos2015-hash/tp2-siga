package siga;

public class CriadorPainelProfessor extends CriadorPainel {

    @Override
    public Painel criarPainel() {
        return new PainelProfessor();
    }
}

