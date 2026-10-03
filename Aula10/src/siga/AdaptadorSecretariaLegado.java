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

    public AdaptadorSecretariaLegado(SecretariaLegadoWS servicoLegado) {
        this.servicoLegado = servicoLegado;
    }

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
