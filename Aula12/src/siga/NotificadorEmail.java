package siga;

/** Reação à matrícula: envia a confirmação por e-mail (apenas imprime no console). */
public class NotificadorEmail implements OuvinteMatricula {

    @Override
    public void aoMatricular(Matricula matricula, boolean turmaLotada) {
        System.out.println("   [e-mail] confirmacao para "
                + matricula.getAluno().getNome());
    }
}
