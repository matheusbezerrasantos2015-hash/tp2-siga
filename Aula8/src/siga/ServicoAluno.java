package siga;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Camada de serviço: concentra as regras de domínio do cadastro de alunos.
 *
 * Todas as validações ficam em {@link #validar(Aluno)}, de modo que a regra
 * existe em um único lugar e vale para qualquer operação.
 */
public class ServicoAluno {

    private static final double MEDIA_MINIMA = 0.0;
    private static final double MEDIA_MAXIMA = 10.0;

    private final AlunoDAO dao;

    /** @param dao armazenamento usado pelo serviço; não pode ser {@code null} */
    public ServicoAluno(AlunoDAO dao) {
        this.dao = dao;
    }

    /**
     * Cadastra um aluno novo depois de validá-lo.
     *
     * @param aluno aluno com nome e matrícula preenchidos (texto só com
     *              espaços conta como vazio) e média entre 0 e 10, extremos
     *              incluídos
     * @throws IllegalArgumentException se o aluno for {@code null} ou algum
     *                                  dado for inválido
     * @throws IllegalStateException    se a matrícula já estiver cadastrada
     */
    public void cadastrar(Aluno aluno) {
        validar(aluno);
        dao.inserir(aluno);
    }

    /**
     * Busca um aluno que deve existir.
     *
     * @param matricula texto procurado com igualdade exata: um espaço a mais no
     *                  fim já faz a busca falhar
     * @return o aluno cadastrado; nunca {@code null}
     * @throws NoSuchElementException se a matrícula não estiver cadastrada
     *                                (aqui a ausência é erro, diferente de
     *                                {@link AlunoDAO#buscarPorMatricula})
     */
    public Aluno consultar(String matricula) {
        Aluno aluno = dao.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new NoSuchElementException(
                    "Não existe aluno com a matrícula " + matricula + ".");
        }
        return aluno;
    }

    /**
     * @return cópia da lista de alunos, na ordem de cadastro; vazia se não
     *         houver nenhum
     */
    public List<Aluno> listar() {
        return dao.listarTodos();
    }

    /**
     * Substitui os dados de um aluno já cadastrado.
     *
     * @param aluno aluno com os dados novos e a matrícula de quem será
     *              alterado; como {@link Aluno} é imutável, o objeto inteiro
     *              é trocado e a matrícula não pode mudar. Passa pelas mesmas
     *              validações de {@link #cadastrar(Aluno)}
     * @throws IllegalArgumentException se os dados do aluno forem inválidos
     * @throws NoSuchElementException   se a matrícula não estiver cadastrada
     */
    public void alterar(Aluno aluno) {
        validar(aluno);
        consultar(aluno.getMatricula());
        dao.atualizar(aluno);
    }

    /**
     * Remove definitivamente um aluno cadastrado.
     *
     * @param matricula matrícula de um aluno já cadastrado; a existência é
     *                  conferida antes de remover
     * @throws NoSuchElementException se a matrícula não estiver cadastrada
     */
    public void excluir(String matricula) {
        consultar(matricula);
        dao.remover(matricula);
    }

    private void validar(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno é obrigatório.");
        }
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (aluno.getMatricula() == null || aluno.getMatricula().isBlank()) {
            throw new IllegalArgumentException("Matrícula é obrigatória.");
        }
        if (aluno.getMedia() < MEDIA_MINIMA || aluno.getMedia() > MEDIA_MAXIMA) {
            throw new IllegalArgumentException(
                    "Média deve estar entre " + MEDIA_MINIMA + " e " + MEDIA_MAXIMA + ".");
        }
    }
}
