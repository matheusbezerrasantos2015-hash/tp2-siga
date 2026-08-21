package siga;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Sistema de Gestão Acadêmica Simplificado ===");
        System.out.println("=== Versão INICIAL ===\n");

        Aluno a1 = new Aluno("Maria Silva");
        a1.setMatricula("2026001");
        a1.setMedia(8.5);
        a1.matricular();

        Aluno a2 = new Aluno("João Silva");
        a2.setMatricula("2026002");
        a2.setMedia(5);      //aqui ficava média inválida
        a2.matricular();

        a1.setMedia(10);      // aqui ficava a média maior que 10

        imprimirAluno(a1);
        imprimirAluno(a2);

        Professor p1 = new Professor("Ana Pereira");
        p1.setSiape("SP12345");
        p1.contratar();
        System.out.println("Professor: " + p1.getNome() + " (SIAPE " + p1.getSiape() + ")");

        // Cria a turma 
        Turma turma = new Turma("DSM3A-2026");

        // Adiciona os alunos à turma
        turma.adicionar(a1);
        turma.adicionar(a2);

        // Percorre e imprime os alunos da turma
        System.out.println("Alunos da turma:");
        for (Aluno aluno : turma.getAlunos()) {
            System.out.println("- " + aluno.getNome());
        }
    }

    // Método utilitário para exibir os dados de um aluno.
    private static void imprimirAluno(Aluno aluno) {
        System.out.println("Aluno: " + aluno.getNome()
                + " | Matrícula: " + aluno.getMatricula()
                + " | Média: " + aluno.getMedia()
                + " | Ativo: " + (aluno.isAtivo() ? "sim" : "não"));
    }
}
