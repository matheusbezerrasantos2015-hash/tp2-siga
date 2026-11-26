package siga;

/**
 * Estado do ciclo de vida da matrícula (State). Cada transição não permitida
 * falha por padrão; os estados concretos sobrescrevem só o que oferecem.
 *
 * Transições oferecidas por cada estado:
 * <ul>
 *   <li>{@link PreMatriculada}: confirmar vai a CONFIRMADA; cancelar vai a
 *       CANCELADA</li>
 *   <li>{@link Confirmada}: confirmar vai a CONCLUIDA; trancar vai a
 *       TRANCADA; cancelar vai a CANCELADA</li>
 *   <li>{@link Trancada}: confirmar vai a CONFIRMADA (retoma); cancelar vai a
 *       CANCELADA</li>
 *   <li>{@link Concluida} e {@link Cancelada}: nenhuma, são estados finais</li>
 * </ul>
 */
public abstract class EstadoMatricula {

    /** @return nome da situação em maiúsculas, como {@code "PRE_MATRICULADA"} */
    public abstract String getNome();

    /**
     * Confirma a matrícula. O resultado depende do estado atual: em
     * PRE_MATRICULADA confirma, em CONFIRMADA conclui e em TRANCADA retoma.
     *
     * @param matricula matrícula que muda de estado
     * @throws IllegalStateException se o estado atual não oferece a transição
     */
    public void confirmar(Matricula matricula) {
        throw transicaoInvalida("confirmar");
    }

    /**
     * Tranca a matrícula; só é permitido a partir de CONFIRMADA.
     *
     * @param matricula matrícula que muda de estado
     * @throws IllegalStateException se o estado atual não oferece a transição
     */
    public void trancar(Matricula matricula) {
        throw transicaoInvalida("trancar");
    }

    /**
     * Cancela a matrícula; é permitido em PRE_MATRICULADA, CONFIRMADA e
     * TRANCADA, e não nos estados finais.
     *
     * @param matricula matrícula que muda de estado
     * @throws IllegalStateException se o estado atual não oferece a transição
     */
    public void cancelar(Matricula matricula) {
        throw transicaoInvalida("cancelar");
    }

    private IllegalStateException transicaoInvalida(String acao) {
        return new IllegalStateException(
                "Nao e possivel " + acao + " uma matricula " + getNome());
    }
}
