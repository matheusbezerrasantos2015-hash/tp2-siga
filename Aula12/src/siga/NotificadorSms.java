package siga;

/** Quinta reação à matrícula: envia um SMS de confirmação (apenas imprime no console). */
public class NotificadorSms implements OuvinteMatricula {

    @Override
    public void aoMatricular(Matricula matricula, boolean turmaLotada) {
        System.out.println("   [sms] confirmacao para " + matricula.getAluno().getNome());
    }
}
