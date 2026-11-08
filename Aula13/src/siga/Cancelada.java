package siga;

/** Estado final: nenhuma transição é permitida. */
public class Cancelada extends EstadoMatricula {

    @Override
    public String getNome() {
        return "CANCELADA";
    }
}
