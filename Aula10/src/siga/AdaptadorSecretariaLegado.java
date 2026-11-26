package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter que traduz a matriz de texto do {@link SecretariaLegadoWS} para
 * objetos {@link Aluno}.
 *
 * É o único lugar do sistema que conhece o formato do legado: coluna 0 é a
 * matrícula, coluna 1 é o nome e coluna 2 é a situação ("A" ou "I", em
 * maiúscula ou minúscula).
 */
public class AdaptadorSecretariaLegado implements FonteDeAlunos {

    private static final int COLUNA_MATRICULA = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_SITUACAO = 2;
    private static final String SITUACAO_ATIVO = "A";

    private final SecretariaLegadoWS servicoLegado;

    /** @param servicoLegado serviço da outra equipe, que não pode ser alterado */
    public AdaptadorSecretariaLegado(SecretariaLegadoWS servicoLegado) {
        this.servicoLegado = servicoLegado;
    }

    /**
     * Converte cada linha do legado em um {@link Aluno}.
     *
     * @return os alunos na ordem do legado. A situação "A" ou "a" vira ativo;
     *         qualquer outro valor vira inativo. O legado não informa se o
     *         aluno é bolsista, então todos chegam como não bolsistas
     * @throws ArrayIndexOutOfBoundsException se alguma linha do legado tiver
     *                                        menos de três colunas
     */
    @Override
    public List<Aluno> obterAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        for (String[] linha : servicoLegado.consultarTabelaAlunos()) {
            Aluno aluno = new Aluno(linha[COLUNA_MATRICULA], linha[COLUNA_NOME]);
            aluno.setAtivo(SITUACAO_ATIVO.equalsIgnoreCase(linha[COLUNA_SITUACAO]));
            alunos.add(aluno);
        }
        return alunos;
    }
}
