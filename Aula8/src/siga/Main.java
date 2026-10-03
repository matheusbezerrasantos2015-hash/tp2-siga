package siga;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Camada de apresentação do SIGA: demonstra o CRUD completo e traduz as
 * exceções do serviço em mensagens compreensíveis ao usuário.
 *
 * Esta camada não valida regras de domínio; isso é responsabilidade do
 * {@link ServicoAluno}.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - CRUD de Alunos (Etapa 1) ===\n");

        ServicoAluno servico = new ServicoAluno(new AlunoDAOMemoria());

        System.out.println("--- CREATE ---");
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 8.5));
        cadastrar(servico, new Aluno("João Souza", "2026002", 6.0));
        cadastrar(servico, new Aluno("Maria Repetida", "2026001", 7.0));
        cadastrar(servico, new Aluno("Média Absurda", "2026003", 50));
        cadastrar(servico, new Aluno(" ", "2026004", 5.0));

        System.out.println("\n--- READ ---");
        listar(servico);
        consultar(servico, "2026002");
        consultar(servico, "0000000");

        System.out.println("\n--- Listagem devolve cópia (coleção interna protegida) ---");
        List<Aluno> copia = servico.listar();
        copia.add(new Aluno("Intruso Silva", "9999999", 10));
        System.out.println("Cópia alterada pela tela tem " + copia.size() + " alunos;"
                + " o cadastro continua com " + servico.listar().size() + ".");

        System.out.println("\n--- UPDATE ---");
        alterar(servico, new Aluno("Maria Silva", "2026001", 9.0));
        alterar(servico, new Aluno("Aluno Fantasma", "0000000", 5.0));
        alterar(servico, new Aluno("Maria Silva", "2026001", 11.0));
        listar(servico);

        System.out.println("\n--- DELETE ---");
        excluir(servico, "2026002");
        excluir(servico, "2026002");
        listar(servico);
    }

    private static void cadastrar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.cadastrar(aluno);
            System.out.println("Cadastrado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private static void consultar(ServicoAluno servico, String matricula) {
        try {
            System.out.println("Encontrado: " + servico.consultar(matricula));
        } catch (NoSuchElementException e) {
            System.out.println("Não foi possível consultar: " + e.getMessage());
        }
    }

    private static void listar(ServicoAluno servico) {
        List<Aluno> alunos = servico.listar();
        System.out.println("Alunos cadastrados (" + alunos.size() + "):");
        for (Aluno aluno : alunos) {
            System.out.println("  " + aluno);
        }
    }

    private static void alterar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.alterar(aluno);
            System.out.println("Alterado: " + aluno);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Não foi possível alterar: " + e.getMessage());
        }
    }

    private static void excluir(ServicoAluno servico, String matricula) {
        try {
            servico.excluir(matricula);
            System.out.println("Excluído: matrícula " + matricula);
        } catch (NoSuchElementException e) {
            System.out.println("Não foi possível excluir: " + e.getMessage());
        }
    }
}
