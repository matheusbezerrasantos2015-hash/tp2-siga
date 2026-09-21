package siga;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAOLista implements AlunoDAO {

    private List<Aluno> alunos = new ArrayList<>();

    @Override
    public void inserir(Aluno aluno) {
        alunos.add(aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }

        return null;
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    @Override
    public void atualizar(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMatricula().equals(aluno.getMatricula())) {
                alunos.set(i, aluno);
                return;
            }
        }
    }

    @Override
    public void remover(String matricula) {
        alunos.removeIf(aluno -> aluno.getMatricula().equals(matricula));
    }
}