package siga;

/** Reação à matrícula: avisa a coordenação quando a turma lota. Falha de propósito. */
public class AvisoCoordenacao implements OuvinteMatricula {

    @Override
    public void aoMatricular(Matricula matricula, boolean turmaLotada) {
        if (turmaLotada) {
            throw new IllegalStateException(
                    "servico de aviso indisponivel para " + matricula.getCodigoTurma());
        }
    }
}
