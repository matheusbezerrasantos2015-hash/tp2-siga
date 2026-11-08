package siga;

/** Entidade de domínio. Pronta — não precisa ser alterada nesta atividade. */
public class Aluno {

    private final String matricula;
    private final String nome;
    private boolean regular = true;
    private int cargaCumprida;

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public int getCargaCumprida() {
        return cargaCumprida;
    }

    public void setCargaCumprida(int cargaCumprida) {
        this.cargaCumprida = cargaCumprida;
    }
}
