package siga;

/** A matrícula está suspensa e pode ser retomada ou cancelada. */
public class Trancada extends EstadoMatricula {

    @Override
    public String getNome() {
        return "TRANCADA";
    }

    @Override
    public void confirmar(Matricula matricula) {
        matricula.mudarPara(new Confirmada());
    }

    @Override
    public void cancelar(Matricula matricula) {
        matricula.mudarPara(new Cancelada());
    }
}
