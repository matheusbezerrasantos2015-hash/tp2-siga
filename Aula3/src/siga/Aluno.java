package siga;

public class Aluno {

    private final String nome;
    private final String matricula;
    private final String email;
    private final double media;
    private final boolean bolsista;

    public Aluno(String nome, String matricula, String email, double media, boolean bolsista) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.media = media;
        this.bolsista = bolsista;
    }

    public String getNome()      { return nome; }
    public String getMatricula() { return matricula; }
    public String getEmail()     { return email; }
    public double getMedia()     { return media; }
    public boolean isBolsista()  { return bolsista; }
}
