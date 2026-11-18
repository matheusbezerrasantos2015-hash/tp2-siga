package siga;

import java.util.List;

/**
 * BRINDE - nao vale nota e nao precisa ser alterado.
 *
 * Confere, executando, duas coisas que o enunciado exige:
 *   (A) o TOM-2003 tem a MESMA situacao no catalogo e no relatorio;
 *   (B) o ciclo de vida de Emprestimo continua exatamente o de hoje.
 *
 * Antes de voce refatorar, (A) ja esta VERMELHO - e a prova de que o
 * Problema 1 existe. Os seis casos de (B) ja estao VERDES e devem
 * CONTINUAR verdes: se um deles virar vermelho, a sua refatoracao mudou o
 * comportamento, e o enunciado pede que ele seja preservado.
 *
 * Nao depende de biblioteca nem de internet. Roda junto com o Main.
 */
public final class VerificacaoRapida {

    private static int verdes = 0;
    private static int vermelhos = 0;

    private VerificacaoRapida() {
    }

    public static void verificar(CatalogoExemplares catalogo, RelatorioColecao relatorio) {
        verdes = 0;
        vermelhos = 0;
        System.out.println();
        System.out.println("=== VERIFICACAO RAPIDA (brinde - nao vale nota) ===");

        System.out.println("  (A) conversao em um lugar so");
        conferirDivergencia(catalogo, relatorio);

        System.out.println("  (B) ciclo de vida preservado");
        conferirCiclo();

        System.out.println();
        System.out.println("  " + verdes + " verde(s), " + vermelhos + " vermelho(s)");
        if (vermelhos == 0) {
            System.out.println("  Tudo verde.");
        } else {
            System.out.println("  Leia os vermelhos acima antes de entregar.");
        }
    }

    // ---------------------------------------------------------- (A)
    private static void conferirDivergencia(CatalogoExemplares catalogo,
                                            RelatorioColecao relatorio) {
        Boolean noCatalogo = null;
        for (Exemplar item : catalogo.listarDisponiveis()) {
            if ("TOM-2003".equals(item.getCodigo())) {
                noCatalogo = Boolean.TRUE;
            }
        }
        if (noCatalogo == null) {
            noCatalogo = Boolean.FALSE;
        }

        Boolean noRelatorio = null;
        List<Exemplar> todos = relatorio.listarTodos();
        for (Exemplar item : todos) {
            if ("TOM-2003".equals(item.getCodigo())) {
                noRelatorio = Boolean.valueOf(item.isDisponivel());
            }
        }
        if (noRelatorio == null) {
            registrar(false, "TOM-2003 nem aparece no relatorio");
            return;
        }
        registrar(noCatalogo.equals(noRelatorio),
                "TOM-2003 disponivel no catalogo=" + noCatalogo
                        + ", no relatorio=" + noRelatorio);
    }

    // ---------------------------------------------------------- (B)
    private static Emprestimo novo() {
        return new Emprestimo(new Leitor("Bruno Lima", "PROFESSOR"),
                new Exemplar("TOM-2001", "Clean Code"));
    }

    private static void conferirCiclo() {
        registrar("RESERVADO".equals(novo().getSituacao()),
                "um Emprestimo novo nasce em RESERVADO");

        Emprestimo a = novo();
        a.emprestar();
        registrar("EMPRESTADO".equals(a.getSituacao()),
                "emprestar leva de RESERVADO para EMPRESTADO");

        Emprestimo b = novo();
        b.emprestar();
        b.restituir();
        registrar("ENCERRADO".equals(b.getSituacao()),
                "restituir leva de EMPRESTADO para ENCERRADO");

        Emprestimo c = novo();
        c.anular();
        registrar("ENCERRADO".equals(c.getSituacao()),
                "anular leva de RESERVADO para ENCERRADO");

        registrar(recusa(novo(), "restituir"),
                "restituir em RESERVADO e recusado");

        Emprestimo d = novo();
        d.emprestar();
        registrar(recusa(d, "emprestar"),
                "emprestar em EMPRESTADO e recusado");

        Emprestimo e = novo();
        e.anular();
        registrar(recusa(e, "emprestar") && recusa(e, "restituir")
                        && recusa(e, "anular"),
                "ENCERRADO e final: recusa as tres operacoes");
    }

    private static boolean recusa(Emprestimo alvo, String operacao) {
        try {
            if ("emprestar".equals(operacao)) {
                alvo.emprestar();
            } else if ("restituir".equals(operacao)) {
                alvo.restituir();
            } else {
                alvo.anular();
            }
            return false;
        } catch (IllegalStateException esperado) {
            return true;
        }
    }

    private static void registrar(boolean passou, String descricao) {
        if (passou) {
            verdes++;
            System.out.println("      [verde]    " + descricao);
        } else {
            vermelhos++;
            System.out.println("      [VERMELHO] " + descricao);
        }
    }
}
