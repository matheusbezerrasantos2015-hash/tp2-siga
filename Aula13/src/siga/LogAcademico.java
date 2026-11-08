package siga;

import java.util.ArrayList;
import java.util.List;

/** Registro de emissões. Pronto. */
public class LogAcademico {

    private static final List<String> REGISTROS = new ArrayList<>();

    public static void registrar(String documento, Aluno aluno) {
        REGISTROS.add(documento + " - " + aluno.getMatricula());
        System.out.println("   [log] " + documento + " emitido para "
                + aluno.getMatricula());
    }

    public static List<String> getRegistros() {
        return new ArrayList<>(REGISTROS);
    }
}
