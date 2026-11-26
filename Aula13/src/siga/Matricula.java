package siga;

/** Matrícula de um aluno em uma turma. As regras do ciclo de vida ficam nos estados. */
public class Matricula {

    private final Aluno aluno;
    private final String codigoTurma;
    private EstadoMatricula estado = new PreMatriculada();

    /**
     * Cria a matrícula no estado inicial, PRE_MATRICULADA.
     *
     * @param aluno       aluno que está sendo matriculado; é guardado como veio,
     *                    sem validação
     * @param codigoTurma código da turma, por exemplo {@code "DSM-2A"}; é só
     *                    guardado, sem conferir se a turma existe
     */
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

    /**
     * @return nome da situação atual: PRE_MATRICULADA, CONFIRMADA, TRANCADA,
     *         CONCLUIDA ou CANCELADA
     */
    public String getSituacao() {
        return estado.getNome();
    }

    /**
     * Avança a matrícula. O efeito depende da situação: PRE_MATRICULADA passa
     * a CONFIRMADA, CONFIRMADA passa a CONCLUIDA (o segundo confirmar conclui)
     * e TRANCADA volta a CONFIRMADA.
     *
     * @throws IllegalStateException se a matrícula estiver CONCLUIDA ou
     *                               CANCELADA
     */
    public void confirmar() {
        estado.confirmar(this);
    }

    /**
     * Suspende a matrícula.
     *
     * @throws IllegalStateException se a situação não for CONFIRMADA
     */
    public void trancar() {
        estado.trancar(this);
    }

    /**
     * Cancela a matrícula; o cancelamento é definitivo.
     *
     * @throws IllegalStateException se a matrícula já estiver CONCLUIDA ou
     *                               CANCELADA
     */
    public void cancelar() {
        estado.cancelar(this);
    }

    void mudarPara(EstadoMatricula novoEstado) {
        this.estado = novoEstado;
    }
}
