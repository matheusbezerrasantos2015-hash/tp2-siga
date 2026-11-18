package siga;

import java.util.ArrayList;
import java.util.List;

public class CatalogoExemplares {

    private final AcervoExemplares acervo;

    public CatalogoExemplares(AcervoExemplares acervo) {
        this.acervo = acervo;
    }

    public List<Exemplar> listarDisponiveis() {
        List<Exemplar> disponiveis = new ArrayList<>();
        for (Exemplar item : acervo.listarTodos()) {
            if (item.isDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }
}
