package siga;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Contrato de acesso a dados de Aluno (padrão DAO).
 *
 * Fala a linguagem do domínio: quem usa este contrato não precisa saber
 * como os dados são armazenados.
 */
public interface AlunoDAO {

    /**
     * @throws IllegalStateException se já existir aluno com a mesma matrícula
     */
    void inserir(Aluno aluno);

    /**
     * @return o aluno encontrado ou {@code null} se não existir
     */
    Aluno buscarPorMatricula(String matricula);

    /**
     * @return uma cópia da lista de alunos; alterá-la não afeta o armazenamento
     */
    List<Aluno> listarTodos();

    /**
     * @throws NoSuchElementException se não existir aluno com a matrícula informada
     */
    void atualizar(Aluno aluno);

    /**
     * @throws NoSuchElementException se não existir aluno com a matrícula informada
     */
    void remover(String matricula);
}
