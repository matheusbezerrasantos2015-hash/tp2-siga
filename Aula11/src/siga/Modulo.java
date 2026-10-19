package siga;

import java.util.ArrayList;
import java.util.List;

/** Composto da estrutura curricular: agrupa disciplinas e outros módulos. */
public class Modulo implements ItemCurricular {

    private final String nome;
    private final List<ItemCurricular> filhos = new ArrayList<>();

    public Modulo(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void adicionar(ItemCurricular filho) {
        filhos.add(filho);
    }

    public void remover(ItemCurricular filho) {
        filhos.remove(filho);
    }

    @Override
    public int calcularCarga() {
        int total = 0;
        for (ItemCurricular filho : filhos) {
            total += filho.calcularCarga();
        }
        return total;
    }

    @Override
    public void imprimir(int nivel) {
        System.out.println("  ".repeat(nivel) + "[" + nome + "]");
        for (ItemCurricular filho : filhos) {
            filho.imprimir(nivel + 1);
        }
    }
}
