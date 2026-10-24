package siga;

/** Estratégia de desconto: recebe o valor base e devolve o valor a pagar. */
public interface PoliticaDesconto {

    double aplicar(double valorBase);
}
