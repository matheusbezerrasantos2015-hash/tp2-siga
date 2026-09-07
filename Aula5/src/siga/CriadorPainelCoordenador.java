package siga;

public class CriadorPainelCoordenador extends CriadorPainel {

    @Override
    public Painel criarPainel() {
        return new PainelCoordenador();
    }
}
