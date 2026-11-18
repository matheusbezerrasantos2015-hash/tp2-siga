package siga;

public class Emprestimo {

    private final Leitor solicitante;
    private final Exemplar item;
    private EstadoEmprestimo estado = new EstadoReservado();

    public Emprestimo(Leitor solicitante, Exemplar item) {
        this.solicitante = solicitante;
        this.item = item;
    }

    public String getSituacao() {
        return estado.nome();
    }

    public void emprestar() {
        estado = estado.emprestar();
    }

    public void restituir() {
        estado = estado.restituir();
    }

    public void anular() {
        estado = estado.anular();
    }

    public Leitor getSolicitante() {
        return solicitante;
    }

    public Exemplar getExemplar() {
        return item;
    }
}