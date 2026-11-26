package siga;

/** Estratégia de desconto: recebe o valor base e devolve o valor a pagar. */
public interface PoliticaDesconto {

    /**
     * @param valorBase mensalidade em reais, antes do desconto
     * @return o valor que o aluno <b>paga</b>, e não o valor descontado. Por
     *         exemplo, para 800,00: bolsista devolve 400,00; servidor,
     *         640,00; egresso, 720,00; sem desconto, 800,00
     */
    double aplicar(double valorBase);
}
