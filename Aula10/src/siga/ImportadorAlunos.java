package siga;

/**
 * Importa os alunos de uma {@link FonteDeAlunos} para o {@link AlunoDAO}.
 */
public class ImportadorAlunos {

    private final FonteDeAlunos fonte;
    private final AlunoDAO dao;

    /**
     * @param fonte origem consultada a cada chamada de {@link #importar()}; o
     *              construtor não lê nada
     * @param dao   destino que recebe cada aluno por {@code inserir}; se a
     *              matrícula já existir, a importação para ali
     */
    public ImportadorAlunos(FonteDeAlunos fonte, AlunoDAO dao) {
        this.fonte = fonte;
        this.dao = dao;
    }

    /**
     * Copia todos os alunos da fonte para o DAO, inclusive os inativos.
     *
     * @return quantos alunos foram inseridos
     * @throws IllegalStateException se algum aluno da fonte já existir no
     *                               DAO; a importação para nesse aluno e os
     *                               anteriores continuam inseridos
     */
    public int importar() {
        int importados = 0;
        for (Aluno aluno : fonte.obterAlunos()) {
            dao.inserir(aluno);
            importados++;
        }
        return importados;
    }
}
