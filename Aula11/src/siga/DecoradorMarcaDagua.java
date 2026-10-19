package siga;

/** Acrescenta a marca d'água de rascunho ao final do relatório decorado. */
public class DecoradorMarcaDagua extends DecoradorRelatorio {

    private static final String MARCA_DAGUA = "\n*** RASCUNHO - NAO E DOCUMENTO OFICIAL ***";

    public DecoradorMarcaDagua(Relatorio relatorioDecorado) {
        super(relatorioDecorado);
    }

    @Override
    public String gerar() {
        return super.gerar() + MARCA_DAGUA;
    }
}
