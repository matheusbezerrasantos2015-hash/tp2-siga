package siga;

/** Atestado de frequência: único documento que dispensa a assinatura digital. */
public class AtestadoFrequencia extends DocumentoAcademico {

    public AtestadoFrequencia() {
        super("ATESTADO");
    }

    @Override
    protected String montarCorpo(Aluno aluno) {
        return "ATESTADO DE FREQUENCIA\n" + aluno.getNome();
    }

    @Override
    protected boolean exigeAssinatura() {
        return false;
    }
}
