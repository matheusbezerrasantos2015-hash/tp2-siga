package siga;

/**
 * Sistema de Gestão Acadêmica Simplificado (SIGA)
 * Técnicas de Programação II - Fatec de Porto Ferreira
 * Atividade prática da Aula 5 (Factory Method / Simple Factory).
 *
 * Produto: contrato comum a todos os painéis iniciais do sistema.
 * Cada perfil de usuário (aluno, professor, coordenador) recebe, ao logar,
 * um painel que implementa esta interface.
 *
 * Esta interface já está pronta e NÃO precisa ser alterada na atividade —
 * ela é o "Produto" no vocabulário do padrão Factory Method.
 */
public interface Painel {

    /** Monta e exibe o painel inicial do usuário. */
    void montar();
}
