package siga;

/**
 * Produto concreto: painel inicial exibido para o perfil COORDENADOR.
 * (Código inicial da atividade — esta classe está pronta.)
 */
public class PainelCoordenador implements Painel {

    @Override
    public void montar() {
        System.out.println("=== Painel do Coordenador ===");
        System.out.println("- Turmas do curso");
        System.out.println("- Relatórios de desempenho");
        System.out.println("- Gestão de professores");
    }
}
