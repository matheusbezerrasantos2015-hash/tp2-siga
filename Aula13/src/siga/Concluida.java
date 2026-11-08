package siga;

/** Estado final: a matrícula foi concluída e nenhuma transição é permitida. */
public class Concluida extends EstadoMatricula {

    @Override
    public String getNome() {
        return "CONCLUIDA";
    }
}
