package siga;

/** Política de desconto para bolsistas. */
public class DescontoBolsista implements PoliticaDesconto {

    private static final double PERCENTUAL_DESCONTO = 0.50;

    @Override
    public double aplicar(double valorBase) {
        return valorBase * (1 - PERCENTUAL_DESCONTO);
    }
}
