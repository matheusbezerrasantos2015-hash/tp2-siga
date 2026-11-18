package siga;

import java.util.ArrayList;
import java.util.List;

public class AcervoLegadoAdapter implements AcervoExemplares {

  private final AcervoLegadoWS legado;

  public AcervoLegadoAdapter(AcervoLegadoWS legado) {
    this.legado = legado;
  }

  @Override 
  public List<Exemplar> listarTodos() {
    List<Exemplar> todos = new ArrayList<>();
    for (String[] linha : legado.consultarExemplares()) {
      Exemplar item = new Exemplar(linha[0], linha[1]);
      item.setDisponivel("D".equalsIgnoreCase(linha[2]));
      todos.add(item);
    }
    return todos;
  }
}