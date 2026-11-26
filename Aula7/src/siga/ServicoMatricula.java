package siga;

import java.util.List;

public class ServicoMatricula {

    private AlunoDAO dao;

    /** @param dao armazenamento usado pelo serviço; não pode ser {@code null} */
    public ServicoMatricula(AlunoDAO dao) {
        this.dao = dao;
    }

    /**
     * Matricula um aluno depois de validar a média.
     *
     * @param aluno aluno a matricular; a média precisa estar entre 0 e 10,
     *              com os dois extremos aceitos. A matrícula repetida não é
     *              verificada aqui (veja {@link AlunoDAO#inserir(Aluno)})
     * @throws IllegalArgumentException se a média for menor que 0 ou maior
     *                                  que 10; nada é gravado nesse caso
     */
    public void matricular(Aluno aluno) {
        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
            throw new IllegalArgumentException("Média inválida: " + aluno.getMedia());
        }

        dao.inserir(aluno);
    }
    
    /**
     * Imprime no console uma linha por aluno. Não devolve nada; com o
     * armazenamento vazio, imprime só o título.
     */
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