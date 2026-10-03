package siga;

/**
 * Importa os alunos de uma {@link FonteDeAlunos} para o {@link AlunoDAO}.
 */
public class ImportadorAlunos {

    private final FonteDeAlunos fonte;
    private final AlunoDAO dao;

    public ImportadorAlunos(FonteDeAlunos fonte, AlunoDAO dao) {
        this.fonte = fonte;
        this.dao = dao;
    }

    public int importar() {
        int importados = 0;
        for (Aluno aluno : fonte.obterAlunos()) {
            dao.inserir(aluno);
            importados++;
        }
        return importados;
    }
}
