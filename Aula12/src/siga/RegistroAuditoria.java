package siga;

/** Reação à matrícula: registra a operação na auditoria. */
public class RegistroAuditoria implements OuvinteMatricula {

    @Override
    public void aoMatricular(Matricula matricula, boolean turmaLotada) {
        System.out.println("   [auditoria] MATRICULA - " + matricula.getAluno().getMatricula());
    }
}
