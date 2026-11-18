package siga;

/** Entidade pronta. Nao precisa ser alterada. */
public class Leitor {

    private final String nome;
    private final String tipo;

    public Leitor(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }
}
