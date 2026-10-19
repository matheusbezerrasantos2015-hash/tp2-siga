package siga;

/** Acrescenta o cabeçalho institucional. É o único lugar onde o texto existe. */
public class DecoradorCabecalho extends DecoradorRelatorio {

    private static final String CABECALHO = "FATEC DE PORTO FERREIRA\n"
            + "Sistema de Gestao Academica\n"
            + "---\n";

    public DecoradorCabecalho(Relatorio relatorioDecorado) {
        super(relatorioDecorado);
    }

    @Override
    public String gerar() {
        return CABECALHO + super.gerar();
    }
}
