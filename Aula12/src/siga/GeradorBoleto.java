package siga;

/** Emite boletos usando a mesma política de desconto aplicada na matrícula. */
public class GeradorBoleto {

    public void emitir(Aluno aluno, double valorBase, PoliticaDesconto politicaDesconto) {
        double valor = politicaDesconto.aplicar(valorBase);
        System.out.printf("   [boleto] %s: R$ %.2f%n", aluno.getNome(), valor);
    }
}
