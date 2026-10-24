package siga;

import java.util.ArrayList;
import java.util.List;

/** Mantém os ouvintes registrados e os avisa quando uma matrícula acontece. */
public class PublicadorMatricula {

    private final List<OuvinteMatricula> ouvintes = new ArrayList<>();

    public void registrar(OuvinteMatricula ouvinte) {
        ouvintes.add(ouvinte);
    }

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
