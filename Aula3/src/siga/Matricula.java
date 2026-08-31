package siga;

public class Matricula {

    private final Aluno aluno;
    private final double valorBase;
    private final Desconto desconto;
    private final MatriculaRepositorio gravador;

    public Matricula(Aluno aluno, double valorBase, Desconto desconto, MatriculaRepositorio gravador) {
        this.aluno = aluno;
        this.valorBase = valorBase;
        this.desconto = desconto;
        this.gravador = gravador;
    }

    public double calcularMensalidade() {
        return desconto.aplicar(valorBase);
    }

    public void salvar() {
        gravador.gravar("Matrícula de " + aluno.getNome()
                + " - mensalidade: " + calcularMensalidade());
    }
}

