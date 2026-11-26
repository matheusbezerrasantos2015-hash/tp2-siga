package siga;

/**
 * Componente do Composite: interface comum a Disciplina (folha) e Modulo
 * (composto). Quem percorre a estrutura trata ambos de forma uniforme.
 *
 * Adicionar e remover filhos não fazem sentido para uma folha, por isso ficam
 * apenas no composto (Modulo).
 */
public interface ItemCurricular {

    /** @return nome da disciplina ou do módulo */
    String getNome();

    /**
     * @return carga horária em horas. Em {@link Disciplina} é o valor
     *         informado; em {@link Modulo} é a soma de todos os filhos, em
     *         qualquer profundidade, e vale 0 para módulo vazio
     */
    int calcularCarga();

    /**
     * Imprime o item no console, recuado dois espaços por nível.
     *
     * @param nivel profundidade do item; 0 para o item raiz, sem recuo
     */
    void imprimir(int nivel);
}
