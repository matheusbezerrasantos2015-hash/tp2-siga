package siga;

import java.util.HashMap;
import java.util.Map;

/** Peça do subsistema de matrícula. Pronta. */
public class ControleVagas {

    private final Map<String, Integer> vagasPorTurma = new HashMap<>();

    public ControleVagas() {
        vagasPorTurma.put("DSM-2A", 2);
        vagasPorTurma.put("DSM-2B", 0);
    }

    /**
     * @param codigoTurma turma consultada, como {@code "DSM-2A"}; um código
     *                    desconhecido é tratado como turma com 0 vagas
     * @return {@code true} se restar ao menos uma vaga; turma que não existe
     *         devolve {@code false}. As vagas iniciais são fixas no
     *         construtor: DSM-2A com 2 e DSM-2B com 0
     */
    public boolean haVaga(String codigoTurma) {
        return vagasPorTurma.getOrDefault(codigoTurma, 0) > 0;
    }

    /**
     * Consome uma vaga da turma.
     *
     * @param codigoTurma turma que perde uma vaga; precisa ter pelo menos uma
     *                    disponível
     * @throws IllegalStateException se a turma não tiver vaga ou não existir
     */
    public void reservar(String codigoTurma) {
        int restantes = vagasPorTurma.getOrDefault(codigoTurma, 0);
        if (restantes <= 0) {
            throw new IllegalStateException("Sem vagas em " + codigoTurma);
        }
        vagasPorTurma.put(codigoTurma, restantes - 1);
    }
}
