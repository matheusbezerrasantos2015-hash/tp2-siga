package siga;

/** Entidade pronta. Nao precisa ser alterada. */
public class Exemplar {

    private final String codigo;
    private final String descricao;
    private boolean disponivel;

    public Exemplar(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
