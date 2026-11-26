package siga;

import java.util.List;

/** Interface do DAO, estabelecida na Aula 7. Nesta versão só tem o necessário para a matrícula. */
public interface AlunoDAO {

    /**
     * Guarda um aluno novo.
     *
     * @param aluno aluno novo; a matrícula dele é a chave e não pode já existir
     *              no armazenamento
     * @throws IllegalStateException se já existir aluno com a mesma matrícula
     *                               (comportamento de {@link AlunoDAOMemoria})
     */
    void inserir(Aluno aluno);

    /**
     * @param matricula matrícula procurada, comparada sem ignorar maiúsculas e
     *                  minúsculas
     * @return o aluno, ou {@code null} se não existir
     */
    Aluno buscarPorMatricula(String matricula);

    /** @return todos os alunos, na ordem de inserção; vazia se não houver nenhum */
    List<Aluno> listarTodos();
}
