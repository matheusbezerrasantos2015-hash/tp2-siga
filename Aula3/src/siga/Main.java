package siga;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade de Refatoração SOLID (código inicial) ===\n");

        List<Aluno> alunos = Arrays.asList(
            new Aluno("Maria Silva", "2026001", "maria@exemplo.edu", 8.5, true),
            new Aluno("João Souza",  "2026002", "joao@exemplo.edu",  6.0, false),
            new Aluno("Ana Pereira", "2026003", "ana@exemplo.edu",   9.2, false)
        );

        FormatadorRelatorioAluno formatador = new FormatadorRelatorioAluno();
        String conteudo = formatador.formatar(alunos);

        RepositorioRelatorio repositorio = new RepositorioRelatorio();
        repositorio.salvarEmArquivo(conteudo, "relatorios/alunos.txt");

        ComunicadorRelatorio comunicador = new ComunicadorRelatorio();
        comunicador.enviarPorEmail(conteudo, "coordenacao@exemplo.edu");

        GravadorMySQL gravador = new GravadorMySQL();

        Matricula m1 = new Matricula(
            alunos.get(0),
            1000.0,
            new DescontoBolsista(),
            gravador
        );

        Matricula m2 = new Matricula(
            alunos.get(1),
            1000.0,
            new SemDesconto(),
            gravador
        );

        System.out.println("Mensalidade (bolsista): " + m1.calcularMensalidade());
        System.out.println("Mensalidade (sem desconto): " + m2.calcularMensalidade());

        m1.salvar();
        m2.salvar();

        System.out.println("\nRefatoração concluída com sucesso!");
        System.out.println("Agora novos tipos de desconto podem ser adicionados sem modificar a classe Matricula.");
        System.out.println("Novas formas de persistência também podem ser utilizadas sem alterar a classe Matricula.");
        System.out.println("A classe Matricula agora depende de abstrações, seguindo os princípios OCP e DIP.");
    }
}
