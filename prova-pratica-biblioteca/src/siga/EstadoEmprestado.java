package siga;

public class EstadoEmprestado implements EstadoEmprestimo {

    @Override
    public String nome() {
        return "EMPRESTADO";
    }

    @Override
    public EstadoEmprestimo restituir() {
        return new EstadoEncerrado();
    }
}