package siga;

import java.util.List;

/**
 * Abstração de uma origem de alunos, na linguagem do domínio do SIGA.
 *
 * Quem consome esta interface não conhece o formato da origem dos dados.
 */
public interface FonteDeAlunos {

    /**
     * Lê todos os alunos da origem.
     *
     * @return lista nova a cada chamada, vazia se a origem não tiver alunos.
     *         Alunos inativos <b>também</b> são devolvidos, com
     *         {@link Aluno#isAtivo()} falso; quem precisar só dos ativos
     *         tem de filtrar
     */
    List<Aluno> obterAlunos();
}
