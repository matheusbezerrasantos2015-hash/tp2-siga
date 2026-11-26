package siga;

/** Interface de relatório do SIGA, já utilizada na Aula 10. Pronta. */
public interface Relatorio {
    /**
     * @return o texto completo do relatório; os decoradores acrescentam
     *         cabeçalho, numeração ou marca d'água a esse texto
     */
    String gerar();
}
