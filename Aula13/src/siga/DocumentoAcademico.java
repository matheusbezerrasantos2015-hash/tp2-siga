package siga;

import java.time.LocalDate;

/**
 * Esqueleto de emissão de documentos acadêmicos (Template Method).
 * O método emitir é final: a ordem dos passos não pode ser alterada pelas subclasses.
 */
public abstract class DocumentoAcademico {

    private static final String CABECALHO = "FATEC DE PORTO FERREIRA\n"
            + "Sistema de Gestao Academica\n"
            + "---\n";
    private static final String ASSINATURA = "\nAssinado digitalmente pela secretaria academica.";

    private final String identificadorLog;

    protected DocumentoAcademico(String identificadorLog) {
        this.identificadorLog = identificadorLog;
    }

    public final String emitir(Aluno aluno) {
        validar(aluno);

        StringBuilder documento = new StringBuilder();
        documento.append(CABECALHO);
        documento.append(montarCorpo(aluno));
        documento.append("\n---\nEmitido em ").append(LocalDate.now());
        if (exigeAssinatura()) {
            documento.append(ASSINATURA);
        }

        LogAcademico.registrar(identificadorLog, aluno);
        return documento.toString();
    }

    private void validar(Aluno aluno) {
        if (!aluno.isRegular()) {
            throw new IllegalStateException(
                    "Aluno irregular: " + aluno.getMatricula());
        }
    }

    /** Passo variável: o conteúdo próprio de cada documento. */
    protected abstract String montarCorpo(Aluno aluno);

    /** Gancho: por padrão o documento leva assinatura digital. */
    protected boolean exigeAssinatura() {
        return true;
    }
}
