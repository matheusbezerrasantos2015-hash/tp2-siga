package siga;

/**
 * Componente do Composite: interface comum a Disciplina (folha) e Modulo
 * (composto). Quem percorre a estrutura trata ambos de forma uniforme.
 *
 * Adicionar e remover filhos não fazem sentido para uma folha, por isso ficam
 * apenas no composto (Modulo).
 */
public interface ItemCurricular {

    String getNome();

    int calcularCarga();

    void imprimir(int nivel);
}
