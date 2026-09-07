package siga;

public class PainelSecretaria implements Painel {

    @Override
    public void montar() {
        System.out.println("=== Painel da Secretaria ===");
        System.out.println("Matrículas pendentes: 12");
        System.out.println("Documentos para análise: 5");
        System.out.println("Solicitações de alunos: 8");
    }
}
