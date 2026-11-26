package siga;

import java.util.ArrayList;
import java.util.List;

/** Mantém os ouvintes registrados e os avisa quando uma matrícula acontece. */
public class PublicadorMatricula {

    private final List<OuvinteMatricula> ouvintes = new ArrayList<>();

    /**
     * Inclui um ouvinte no fim da fila. Os ouvintes são avisados na ordem de
     * registro. Um ouvinte registrado durante uma publicação só é avisado a
     * partir da publicação seguinte.
     *
     * @param ouvinte quem será avisado a cada matrícula; não pode ser
     *                {@code null}. Registrar o mesmo ouvinte duas vezes faz com
     *                que ele seja avisado duas vezes
     */
    public void registrar(OuvinteMatricula ouvinte) {
        ouvintes.add(ouvinte);
    }

    /**
     * Avisa todos os ouvintes. A {@link RuntimeException} de um ouvinte é
     * registrada no console e os ouvintes seguintes continuam sendo avisados;
     * nada é relançado para quem publicou. Erros que não sejam
     * {@code RuntimeException} não são capturados.
     *
     * @param matricula   matrícula repassada a cada ouvinte
     * @param turmaLotada indicador repassado sem alteração
     */
    public void publicar(Matricula matricula, boolean turmaLotada) {
        for (OuvinteMatricula ouvinte : new ArrayList<>(ouvintes)) {
            try {
                ouvinte.aoMatricular(matricula, turmaLotada);
            } catch (RuntimeException falha) {
                // A falha de um ouvinte é problema dele: registra e segue para os demais.
                System.out.println("   [falha no ouvinte] "
                        + ouvinte.getClass().getSimpleName() + ": " + falha.getMessage());
            }
        }
    }
}
