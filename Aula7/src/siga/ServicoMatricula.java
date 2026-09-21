package siga;

import java.util.List;

public class ServicoMatricula {

    private AlunoDAO dao;

    public ServicoMatricula(AlunoDAO dao) {
        this.dao = dao;
    }

    public void matricular(Aluno aluno) {
        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
            throw new IllegalArgumentException("Média inválida: " + aluno.getMedia());
        }

        dao.inserir(aluno);
    }
    
    public void gerarRelatorio() {
    List<Aluno> alunos = dao.listarTodos();

    System.out.println("=== Relatório de Alunos ===");

        for (Aluno aluno : alunos) {
            System.out.println(
                aluno.getMatricula() + " - "
                + aluno.getNome() + " (média "
                + aluno.getMedia() + ")"
            );
        }
    }
}