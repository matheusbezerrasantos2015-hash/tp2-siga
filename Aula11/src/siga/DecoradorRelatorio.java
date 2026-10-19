package siga;

/**
 * Base dos decoradores de relatório: é um Relatorio que envolve outro Relatorio.
 * Por padrão apenas repassa a geração; cada decorador concreto acrescenta o
 * seu comportamento em torno dessa chamada.
 */
public abstract class DecoradorRelatorio implements Relatorio {

    private final Relatorio relatorioDecorado;

    protected DecoradorRelatorio(Relatorio relatorioDecorado) {
        this.relatorioDecorado = relatorioDecorado;
    }

    @Override
    public String gerar() {
        return relatorioDecorado.gerar();
    }
}
