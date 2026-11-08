package siga;

/** Cliente de demonstração: emissão de documentos e ciclo de vida da matrícula. */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Aula 13: Template Method e State ===");

        Aluno regular = new Aluno("2024001", "Maria Silva");
        regular.setCargaCumprida(560);

        Aluno irregular = new Aluno("2024002", "Joao Souza");
        irregular.setRegular(false);

        DocumentoAcademico[] documentos = {
            new HistoricoEscolar(), new DeclaracaoVinculo(), new AtestadoFrequencia()
        };
        demonstrarDocumentos(documentos, regular, irregular);
        demonstrarCicloDeVida(regular);
    }

    private static void demonstrarDocumentos(DocumentoAcademico[] documentos,
            Aluno regular, Aluno irregular) {
        System.out.println();
        System.out.println("1) Tres documentos, um unico esqueleto de emissao");
        for (DocumentoAcademico documento : documentos) {
            System.out.println(recuar(documento.emitir(regular)));
        }

        System.out.println();
        System.out.println("2) Aluno irregular: nenhum documento e nenhum rastro no log");
        for (DocumentoAcademico documento : documentos) {
            try {
                documento.emitir(irregular);
            } catch (IllegalStateException e) {
                System.out.println("   [erro] " + e.getMessage());
            }
        }
        System.out.println("   Registros no log: " + LogAcademico.getRegistros());
    }

    private static void demonstrarCicloDeVida(Aluno aluno) {
        System.out.println();
        System.out.println("3) Ciclo de vida da matricula com o padrao State");
        Matricula matricula = new Matricula(aluno, "DSM-2A");
        System.out.println("   situacao inicial: " + matricula.getSituacao());
        executar(matricula, "confirmar", matricula::confirmar);
        executar(matricula, "trancar", matricula::trancar);
        executar(matricula, "confirmar", matricula::confirmar);
        executar(matricula, "cancelar", matricula::cancelar);
        executar(matricula, "confirmar", matricula::confirmar);

        System.out.println();
        System.out.println("4) Estado Concluida");
        Matricula outra = new Matricula(aluno, "DSM-2B");
        executar(outra, "confirmar", outra::confirmar);
        executar(outra, "confirmar", outra::confirmar);
        executar(outra, "cancelar", outra::cancelar);
    }

    private static void executar(Matricula matricula, String acao, Runnable transicao) {
        try {
            transicao.run();
            System.out.println("   apos " + acao + ": " + matricula.getSituacao());
        } catch (IllegalStateException e) {
            System.out.println("   [erro] " + e.getMessage());
        }
    }

    private static String recuar(String texto) {
        StringBuilder saida = new StringBuilder();
        for (String linha : texto.split("\n")) {
            saida.append("     ").append(linha).append("\n");
        }
        return saida.toString().stripTrailing();
    }
}
