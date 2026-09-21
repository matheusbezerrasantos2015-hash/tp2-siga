package siga;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade de Persistência e DAO ===\n");

        // Primeira implementação: armazenamento com Map
        AlunoDAO dao = new AlunoDAOMemoria();
        ServicoMatricula servico = new ServicoMatricula(dao);

        servico.matricular(new Aluno("Maria Silva", "2026001", 8.5));
        servico.matricular(new Aluno("João Souza", "2026002", 6.0));
        System.out.println();

        servico.gerarRelatorio();

        // A regra de negócio funciona: média inválida é rejeitada.
        System.out.println();
        try {
            servico.matricular(new Aluno("Teste Inválido", "2026003", -1));
        } catch (IllegalArgumentException e) {
            System.out.println("Regra de negócio funcionou: " + e.getMessage());
        }

        // Segunda implementação: armazenamento com List
        System.out.println(
            "\n=== Repetindo com outra implementação do DAO (AlunoDAOLista) ===\n"
        );

        AlunoDAO daoLista = new AlunoDAOLista();
        ServicoMatricula servicoLista = new ServicoMatricula(daoLista);

        servicoLista.matricular(
            new Aluno("Ana Pereira", "2026003", 9.2)
        );

        servicoLista.gerarRelatorio();

        System.out.println(
            "\nA mesma regra de negócio funcionou com duas implementações diferentes de AlunoDAO."
        );
    }
}