package siga;

/** Matrícula de um aluno em uma turma. O desconto é delegado à política recebida. */
public class Matricula {

    private final Aluno aluno;
    private final String codigoTurma;
    private final PoliticaDesconto politicaDesconto;

    public Matricula(Aluno aluno, String codigoTurma, PoliticaDesconto politicaDesconto) {
        this.aluno = aluno;
        this.codigoTurma = codigoTurma;
        this.politicaDesconto = politicaDesconto;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public double calcularValor(double valorBase) {
        return politicaDesconto.aplicar(valorBase);
    }

    @Override
    public String toString() {
        return aluno.getNome() + " em " + codigoTurma;
    }
}
