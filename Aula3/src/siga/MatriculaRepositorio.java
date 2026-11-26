package siga;

/**
 * Contrato de persistência de uma matrícula (abstração da qual
 * {@link Matricula} depende, em vez de depender de um banco específico).
 */
public interface MatriculaRepositorio {

    /**
     * Grava os dados de uma matrícula.
     *
     * @param dados texto já formatado, no padrão
     *              {@code "Matrícula de <nome> - mensalidade: <valor>"};
     *              o repositório não interpreta o conteúdo, apenas o guarda.
     *              Em {@link GravadorMySQL} a gravação é simulada: o texto é
     *              só impresso no console
     */
    void gravar(String dados);
}
