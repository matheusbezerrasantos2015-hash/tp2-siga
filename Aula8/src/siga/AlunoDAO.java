package siga;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Contrato de acesso a dados de Aluno (padrão DAO).
 *
 * Fala a linguagem do domínio: quem usa este contrato não precisa saber
 * como os dados são armazenados. Diferente da Aula 7, aqui o contrato é
 * explícito sobre matrícula repetida e inexistente.
 */
public interface AlunoDAO {

    /**
     * Guarda um aluno novo.
     *
     * @param aluno aluno a guardar; a matrícula é a chave de identificação
     * @throws IllegalStateException se já existir aluno com a mesma matrícula
     *                               (o aluno existente não é alterado)
     */
    void inserir(Aluno aluno);

    /**
     * Procura um aluno pela matrícula.
     *
     * @param matricula matrícula procurada; a comparação diferencia maiúsculas
     *                  de minúsculas e uma matrícula {@code null} só resulta em
     *                  {@code null}, sem erro
     * @return o aluno encontrado ou {@code null} se não existir (aqui não há
     *         exceção; quem converte a ausência em erro é o
     *         {@link ServicoAluno})
     */
    Aluno buscarPorMatricula(String matricula);

    /**
     * Lista todos os alunos.
     *
     * @return uma cópia da lista, na ordem de inserção; alterá-la não afeta o
     *         armazenamento
     */
    List<Aluno> listarTodos();

    /**
     * Troca o aluno que tem a mesma matrícula, mantendo a posição dele.
     *
     * @param aluno aluno com os dados novos; a matrícula não muda, ela
     *              identifica quem será trocado
     * @throws NoSuchElementException se não existir aluno com a matrícula
     *                                informada (nada é inserido)
     */
    void atualizar(Aluno aluno);

    /**
     * Apaga o aluno da matrícula informada.
     *
     * @param matricula chave do aluno a apagar; precisa estar cadastrada, caso
     *                  contrário vale a exceção abaixo
     * @throws NoSuchElementException se não existir aluno com a matrícula
     *                                informada
     */
    void remover(String matricula);
}
