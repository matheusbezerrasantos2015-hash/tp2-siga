package siga;

import java.util.List;

/**
 * Contrato de acesso a dados de {@link Aluno} (padrão DAO).
 *
 * Nesta versão o contrato <b>não define</b> o que acontece com matrícula
 * repetida ou inexistente, e as duas implementações respondem de forma
 * diferente (detalhe em cada método). A Aula 8 fecha essa lacuna com
 * exceções.
 */
public interface AlunoDAO {

    /**
     * Guarda um aluno.
     *
     * @param aluno aluno a guardar. Se a matrícula já existir,
     *              {@link AlunoDAOMemoria} substitui o aluno anterior e
     *              {@link AlunoDAOLista} acrescenta uma segunda entrada com a
     *              mesma matrícula
     */
    void inserir(Aluno aluno);

    /**
     * Procura um aluno pela matrícula.
     *
     * @param matricula matrícula exata (diferencia maiúsculas e minúsculas)
     * @return o aluno, ou {@code null} se não existir (não lança exceção)
     */
    Aluno buscarPorMatricula(String matricula);

    /**
     * Lista os alunos guardados.
     *
     * @return uma cópia da lista; alterá-la não altera o armazenamento. A
     *         ordem é a de inserção em {@link AlunoDAOLista} e <b>não é
     *         garantida</b> em {@link AlunoDAOMemoria}
     */
    List<Aluno> listarTodos();

    /**
     * Troca o aluno que tem a mesma matrícula.
     *
     * @param aluno aluno com os dados novos. Se a matrícula não existir,
     *              {@link AlunoDAOMemoria} o insere e {@link AlunoDAOLista}
     *              não faz nada
     */
    void atualizar(Aluno aluno);

    /**
     * Apaga o aluno da matrícula informada.
     *
     * @param matricula matrícula do aluno; se não existir, nada acontece e
     *                  nenhuma exceção é lançada
     */
    void remover(String matricula);
}
