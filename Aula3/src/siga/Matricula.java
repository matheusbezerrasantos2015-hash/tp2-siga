package siga;

public class Matricula {

    private final Aluno aluno;
    private final double valorBase;
    private final Desconto desconto;
    private final MatriculaRepositorio gravador;

    /**
     * @param aluno     aluno matriculado; não pode ser {@code null}, pois
     *                  {@link #salvar()} usa o nome dele
     * @param valorBase mensalidade em reais, antes de qualquer desconto
     * @param desconto  regra aplicada em {@link #calcularMensalidade()};
     *                  use {@link SemDesconto} para não dar desconto, nunca
     *                  {@code null}
     * @param gravador  destino usado por {@link #salvar()}
     */
    public Matricula(Aluno aluno, double valorBase, Desconto desconto, MatriculaRepositorio gravador) {
        this.aluno = aluno;
        this.valorBase = valorBase;
        this.desconto = desconto;
        this.gravador = gravador;
    }

    /**
     * Calcula a mensalidade já com o desconto.
     *
     * @return valor a pagar em reais (resultado de
     *         {@link Desconto#aplicar(double)} sobre o valor base)
     */
    public double calcularMensalidade() {
        return desconto.aplicar(valorBase);
    }

    /**
     * Grava a matrícula no repositório recebido. O texto gravado leva o valor
     * da mensalidade <b>com</b> desconto, não o valor base.
     */
    public void salvar() {
        gravador.gravar("Matrícula de " + aluno.getNome()
                + " - mensalidade: " + calcularMensalidade());
    }
}

