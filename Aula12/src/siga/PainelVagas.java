package siga;

/** Reação à matrícula: atualiza o painel de vagas da turma. */
public class PainelVagas implements OuvinteMatricula {

    @Override
    public void aoMatricular(Matricula matricula, boolean turmaLotada) {
        System.out.println("   [painel] vagas de " + matricula.getCodigoTurma() + " atualizadas");
    }
}
