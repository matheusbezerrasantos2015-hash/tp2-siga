package siga;

/** Folha da estrutura curricular. */
public class Disciplina implements ItemCurricular {

    private final String nome;
    private final int cargaHoraria;

    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int calcularCarga() {
        return cargaHoraria;
    }

    @Override
    public void imprimir(int nivel) {
        System.out.println("  ".repeat(nivel) + nome + " (" + cargaHoraria + "h)");
    }
}
