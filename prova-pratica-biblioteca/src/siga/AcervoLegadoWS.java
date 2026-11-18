package siga;

/**
 * COMPONENTE EXTERNO - simula o acervo central da instituicao, mantido por
 * outra equipe.
 *
 * NAO ALTERE ESTA CLASSE. Ela representa uma dependencia de terceiros cujo
 * codigo-fonte voce nao controla.
 *
 * Formato devolvido: cada linha e {codigo, descricao, situacao}, em que a
 * situacao vem como "D" (disponivel) ou "B" (baixado) - e, por descuido do
 * sistema de origem, as vezes em minuscula.
 */
public class AcervoLegadoWS {

    public String[][] consultarExemplares() {
        return new String[][] {
            {"TOM-2001", "Clean Code", "D"},
            {"TOM-2002", "Design Patterns", "B"},
            {"TOM-2003", "Refactoring", "d"},
            {"TOM-2004", "Effective Java", "D"}
        };
    }
}
