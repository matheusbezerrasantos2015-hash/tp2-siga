package siga;

/** Numera cada linha do relatório decorado. */
public class DecoradorNumeracao extends DecoradorRelatorio {

    public DecoradorNumeracao(Relatorio relatorioDecorado) {
        super(relatorioDecorado);
    }

    @Override
    public String gerar() {
        String[] linhas = super.gerar().split("\n");
        StringBuilder numerado = new StringBuilder();
        for (int i = 0; i < linhas.length; i++) {
            numerado.append(i + 1).append(": ").append(linhas[i]).append("\n");
        }
        return numerado.toString().stripTrailing();
    }
}
