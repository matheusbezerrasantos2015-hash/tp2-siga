package siga;

/** Política de desconto para egressos. */
public class DescontoEgresso implements PoliticaDesconto {

    private static final double PERCENTUAL_DESCONTO = 0.10;

    @Override
    public double aplicar(double valorBase) {
        return valorBase * (1 - PERCENTUAL_DESCONTO);
    }
}
