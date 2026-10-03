package siga;

/**
 * Imprime a situação dos alunos de uma {@link FonteDeAlunos}.
 */
public class RelatorioSituacao {

    private final FonteDeAlunos fonte;

    public RelatorioSituacao(FonteDeAlunos fonte) {
        this.fonte = fonte;
    }

    public void imprimir() {
        System.out.println("   Situação segundo o relatório:");
        for (Aluno aluno : fonte.obterAlunos()) {
            System.out.println("     " + aluno);
        }
    }
}
