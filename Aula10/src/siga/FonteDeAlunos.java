package siga;

import java.util.List;

/**
 * Abstração de uma origem de alunos, na linguagem do domínio do SIGA.
 *
 * Quem consome esta interface não conhece o formato da origem dos dados.
 */
public interface FonteDeAlunos {

    List<Aluno> obterAlunos();
}
