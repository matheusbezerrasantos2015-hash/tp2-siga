package siga;

/** Política de desconto para servidores. */
public class DescontoServidor implements PoliticaDesconto {

    private static final double PERCENTUAL_DESCONTO = 0.20;

    @Override
    public double aplicar(double valorBase) {
        return valorBase * (1 - PERCENTUAL_DESCONTO);
    }
}
