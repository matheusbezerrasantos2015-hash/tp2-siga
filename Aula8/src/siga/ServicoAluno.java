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

    public ServicoAluno(AlunoDAO dao) {
        this.dao = dao;
    }

    /**
     * @throws IllegalArgumentException se os dados do aluno forem inválidos
     * @throws IllegalStateException    se a matrícula já estiver cadastrada
     */
    public void cadastrar(Aluno aluno) {
        validar(aluno);
        dao.inserir(aluno);
    }

    /**
     * @throws NoSuchElementException se a matrícula não estiver cadastrada
     */
    public Aluno consultar(String matricula) {
        Aluno aluno = dao.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new NoSuchElementException(
                    "Não existe aluno com a matrícula " + matricula + ".");
        }
        return aluno;
    }

    public List<Aluno> listar() {
        return dao.listarTodos();
    }

    /**
     * @throws IllegalArgumentException se os dados do aluno forem inválidos
     * @throws NoSuchElementException   se a matrícula não estiver cadastrada
     */
    public void alterar(Aluno aluno) {
        validar(aluno);
        consultar(aluno.getMatricula());
        dao.atualizar(aluno);
    }

    /**
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
