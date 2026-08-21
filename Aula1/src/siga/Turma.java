package siga;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private final String codigo;
    private final List<Aluno> alunos;

    public Turma(String codigo) {
        this.codigo = codigo;
        this.alunos = new ArrayList<>();
    }

    public void adicionar(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        if (this.alunos.contains(aluno)) {
            throw new IllegalArgumentException("Aluno já está matriculado nesta turma.");
        }
        this.alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(this.alunos);
    }

    public String getCodigo() {
        return this.codigo;
    }
}

