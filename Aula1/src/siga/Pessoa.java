package siga;

public class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        setNome(nome);
    }

    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Não pode ser vazio. ");
        }
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}