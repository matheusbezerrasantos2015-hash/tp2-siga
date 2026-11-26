package siga;

/**
 * Contrato de uma regra de desconto sobre a mensalidade.
 *
 * Implementações: {@link DescontoBolsista}, {@link DescontoConvenio},
 * {@link DescontoFuncionario} e {@link SemDesconto}. Para criar um tipo novo
 * de desconto basta implementar esta interface; {@link Matricula} não muda.
 */
public interface Desconto {

    /**
     * Aplica a regra de desconto ao valor base.
     *
     * @param valor valor base da mensalidade, em reais, antes do desconto;
     *              espera-se um número maior ou igual a zero (nenhuma
     *              implementação valida isso)
     * @return o valor que o aluno <b>paga</b> depois do desconto, e não o
     *         valor descontado. Por exemplo, para 1000,00: bolsista devolve
     *         500,00; convênio, 800,00; funcionário, 700,00; sem desconto,
     *         1000,00
     */
    double aplicar(double valor);
}
