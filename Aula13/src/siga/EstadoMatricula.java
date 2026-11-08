package siga;

/**
 * Estado do ciclo de vida da matrícula (State). Cada transição não permitida
 * falha por padrão; os estados concretos sobrescrevem só o que oferecem.
 */
public abstract class EstadoMatricula {

    public abstract String getNome();

    public void confirmar(Matricula matricula) {
        throw transicaoInvalida("confirmar");
    }

    public void trancar(Matricula matricula) {
        throw transicaoInvalida("trancar");
    }

    public void cancelar(Matricula matricula) {
        throw transicaoInvalida("cancelar");
    }

    private IllegalStateException transicaoInvalida(String acao) {
        return new IllegalStateException(
                "Nao e possivel " + acao + " uma matricula " + getNome());
    }
}
