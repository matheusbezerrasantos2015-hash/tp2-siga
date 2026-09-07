package siga;

/**
 * Produto concreto: painel inicial exibido para o perfil ALUNO.
 * (Código inicial da atividade — esta classe está pronta.)
 */
public class PainelAluno implements Painel {

    @Override
    public void montar() {
        System.out.println("=== Painel do Aluno ===");
        System.out.println("- Minhas disciplinas");
        System.out.println("- Notas e frequência");
        System.out.println("- Histórico escolar");
    }
}
