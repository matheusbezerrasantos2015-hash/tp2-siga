package siga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunoDAOMemoria implements AlunoDAO {

    private Map<String, Aluno> alunos = new HashMap<>();

    @Override
    public void inserir(Aluno aluno) {
        alunos.put(aluno.getMatricula(), aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return alunos.get(matricula);
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos.values());
    }

    @Override
    public void atualizar(Aluno aluno) {
        alunos.put(aluno.getMatricula(), aluno);
    }

    @Override
    public void remover(String matricula) {
        alunos.remove(matricula);
    }
}