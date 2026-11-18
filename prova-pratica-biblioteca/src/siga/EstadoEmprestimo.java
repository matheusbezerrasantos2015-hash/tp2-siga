package siga;

public interface EstadoEmprestimo {

  String nome();

  default EstadoEmprestimo emprestar() {
    throw recusa ("emprestar");
  }

  default EstadoEmprestimo restituir() {
    throw recusa("restituir");
  }

  default EstadoEmprestimo anular() {
    throw recusa("anular");
  }

  private IllegalStateException recusa(String operacao) {
    return new IllegalStateException(
      "Nao e possivel " + operacao + " em " + nome() + "."
    );
  }
}