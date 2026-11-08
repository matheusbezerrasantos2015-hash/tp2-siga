package siga;

/** Declaração de vínculo: corpo com a declaração de vínculo ativo. */
public class DeclaracaoVinculo extends DocumentoAcademico {

    public DeclaracaoVinculo() {
        super("DECLARACAO");
    }

    @Override
    protected String montarCorpo(Aluno aluno) {
        return "DECLARACAO DE VINCULO\n"
                + "Declaramos que "
                + aluno.getNome()
                + " possui vinculo ativo com esta instituicao.";
    }
}
