package siga;

import java.util.List;

public class RelatorioColecao {

    private final AcervoExemplares acervo;

    public RelatorioColecao(AcervoExemplares acervo) {
        this.acervo = acervo;
    }

    public List<Exemplar> listarTodos() {
        return acervo.listarTodos();
    }

    public void imprimir() {
        System.out.println("    Situacao segundo o relatorio:");
        for (Exemplar item : listarTodos()) {
            System.out.println("     " + item + " - "
                + (item.isDisponivel() ? "disponivel" : "baixado"));
        }
    }
}
