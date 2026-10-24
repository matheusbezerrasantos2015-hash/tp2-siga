package siga;

/** Observador: reage a uma matrícula já realizada. */
public interface OuvinteMatricula {

    void aoMatricular(Matricula matricula, boolean turmaLotada);
}
