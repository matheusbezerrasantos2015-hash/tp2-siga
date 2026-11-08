package siga;

/** Estado inicial: a matrícula ainda não foi confirmada. */
public class PreMatriculada extends EstadoMatricula {

    @Override
    public String getNome() {
        return "PRE_MATRICULADA";
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
