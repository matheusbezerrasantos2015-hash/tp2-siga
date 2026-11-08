package siga;

/** Histórico escolar: corpo com a carga horária cumprida. */
public class HistoricoEscolar extends DocumentoAcademico {

    public HistoricoEscolar() {
        super("HISTORICO");
    }

    @Override
    protected String montarCorpo(Aluno aluno) {
        return "HISTORICO ESCOLAR\n"
                + aluno.getNome()
                + "\nCarga horaria cumprida: "
                + aluno.getCargaCumprida()
                + "h";
    }
}
