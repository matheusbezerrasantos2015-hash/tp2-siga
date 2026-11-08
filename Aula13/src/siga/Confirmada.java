package siga;

/** A matrícula está ativa. */
public class Confirmada extends EstadoMatricula {

    @Override
    public String getNome() {
        return "CONFIRMADA";
    }

    @Override
    public void confirmar(Matricula matricula) {
        matricula.mudarPara(new Concluida());
    }

    @Override
    public void trancar(Matricula matricula) {
        matricula.mudarPara(new Trancada());
    }

    @Override
    public void cancelar(Matricula matricula) {
        matricula.mudarPara(new Cancelada());
    }
}
