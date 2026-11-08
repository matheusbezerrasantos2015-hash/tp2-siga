package siga;

/** Matrícula de um aluno em uma turma. As regras do ciclo de vida ficam nos estados. */
public class Matricula {

    private final Aluno aluno;
    private final String codigoTurma;
    private EstadoMatricula estado = new PreMatriculada();

    public Matricula(Aluno aluno, String codigoTurma) {
        this.aluno = aluno;
        this.codigoTurma = codigoTurma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public String getSituacao() {
        return estado.getNome();
    }

    public void confirmar() {
        estado.confirmar(this);
    }

    public void trancar() {
        estado.trancar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    void mudarPara(EstadoMatricula novoEstado) {
        this.estado = novoEstado;
    }
}
