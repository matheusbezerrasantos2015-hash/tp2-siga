package siga;

public class Professor extends Pessoa {

    private String siape;
    private boolean ativo;

    public Professor(String nome) {
        super(nome);
    }

    public final void setSiape(String siape) {
        if (siape == null || siape.trim().isEmpty()) {
            throw new IllegalArgumentException("Não pode ficar vazio. ");
        }
        this.siape = siape;
    }

    public final void contratar() {
        this.ativo = true;
    }

    public void demitir() {
        this.ativo = false;
    }

    public String getSiape() {
        return this.siape;
    }

    public boolean isAtivo() {
        return this.ativo;
    }
}
