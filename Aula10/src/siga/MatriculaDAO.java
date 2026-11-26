package siga;

import java.util.List;

/** Porta de persistência das matrículas. */
public interface MatriculaDAO {

    /**
     * Guarda uma matrícula. Não há verificação de matrícula duplicada: o
     * mesmo aluno pode ser gravado várias vezes na mesma turma.
     *
     * @param matricula matrícula já criada, com o desconto calculado
     */
    void inserir(Matricula matricula);

    /** @return todas as matrículas gravadas; vazia se não houver nenhuma */
    List<Matricula> listarTodas();
}
