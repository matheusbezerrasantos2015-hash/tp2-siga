package siga;

public class EstadoReservado implements EstadoEmprestimo {

    @Override
    public String nome() {
        return "RESERVADO";
    }

    @Override
    public EstadoEmprestimo emprestar() {
        return new EstadoEmprestado();
    }

    @Override
    public EstadoEmprestimo anular() {
        return new EstadoEncerrado();
    }
}