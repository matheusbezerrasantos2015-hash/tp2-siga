package siga;

/** Observador: reage a uma matrícula já realizada. */
public interface OuvinteMatricula {

    /**
     * Chamado depois que a matrícula foi gravada.
     *
     * @param matricula   matrícula que acabou de ser feita
     * @param turmaLotada informado por quem chamou
     *                    {@link FachadaMatricula#matricular}; a fachada não
     *                    calcula se a turma lotou
     */
    void aoMatricular(Matricula matricula, boolean turmaLotada);
}
