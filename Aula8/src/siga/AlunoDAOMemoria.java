package siga;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementação do {@link AlunoDAO} que mantém os alunos em memória.
 */
public class AlunoDAOMemoria implements AlunoDAO {

    private final List<Aluno> armazem = new ArrayList<>();

    @Override
    public void inserir(Aluno aluno) {
        if (buscarPorMatricula(aluno.getMatricula()) != null) {
            throw new IllegalStateException(
                    "Já existe aluno com a matrícula " + aluno.getMatricula() + ".");
        }
        armazem.add(aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno aluno : armazem) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(armazem);
    }

    @Override
    public void atualizar(Aluno aluno) {
        int posicao = localizarPosicao(aluno.getMatricula());
        armazem.set(posicao, aluno);
    }

    @Override
    public void remover(String matricula) {
        int posicao = localizarPosicao(matricula);
        armazem.remove(posicao);
    }

    private int localizarPosicao(String matricula) {
        for (int posicao = 0; posicao < armazem.size(); posicao++) {
            if (armazem.get(posicao).getMatricula().equals(matricula)) {
                return posicao;
            }
        }
        throw new NoSuchElementException(
                "Não existe aluno com a matrícula " + matricula + ".");
    }
}
