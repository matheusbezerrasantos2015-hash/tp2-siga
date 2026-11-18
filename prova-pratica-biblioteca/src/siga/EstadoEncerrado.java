package siga;

public class EstadoEncerrado implements EstadoEmprestimo {

    @Override
    public String nome() {
        return "ENCERRADO";
    }
}