package siga;

/** Política para quem não tem direito a desconto. */
public class SemDesconto implements PoliticaDesconto {

    @Override
    public double aplicar(double valorBase) {
        return valorBase;
    }
}
