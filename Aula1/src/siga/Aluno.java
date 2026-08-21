package siga;

public class Aluno extends Pessoa{
    private String matricula;
    private double media;
    private boolean ativo;

    public Aluno(String nome) {
        super(nome);
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matricula não pode ser vazia. ");
        }
        this.matricula = matricula;
    }

    public void setMedia(double media) {
        if (media < 0 || media > 10) {
            throw new IllegalArgumentException("Média deve estar entre 0 e 10. ");
        }
        this.media = media;
    }

    public void matricular() {
        this.ativo = true;
    }

    public void cancelarMatricula() {
        this.ativo = false;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public double getMedia() {
        return this.media;
    }

    public boolean isAtivo() {
        return this.ativo;
    }
}
