package siga;

/** Cliente de demonstração: monta pilhas de decoradores e a estrutura curricular. */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Aula 11: Decorator e Composite ===");
        demonstrarRelatorios();
        demonstrarEstruturaCurricular();
    }

    private static void demonstrarRelatorios() {
        System.out.println();
        System.out.println("1) Relatorios: decoradores empilhaveis");

        Relatorio apenasCabecalho = new DecoradorCabecalho(new RelatorioDeAlunos());
        Relatorio numeradoComMarca = new DecoradorMarcaDagua(
                new DecoradorNumeracao(new RelatorioDeAlunos()));
        Relatorio completo = new DecoradorMarcaDagua(
                new DecoradorCabecalho(
                        new DecoradorNumeracao(new RelatorioDeAlunos())));

        exibirRelatorio("Cabecalho", apenasCabecalho);
        exibirRelatorio("Numeracao + marca d'agua", numeradoComMarca);
        exibirRelatorio("Numeracao + cabecalho + marca d'agua", completo);
    }

    private static void demonstrarEstruturaCurricular() {
        System.out.println();
        System.out.println("2) Estrutura curricular como Composite");

        Modulo curso = new Modulo("DSM - Desenvolvimento de Software Multiplataforma");

        Modulo primeiro = new Modulo("1o semestre");
        primeiro.adicionar(new Disciplina("Algoritmos", 80));
        primeiro.adicionar(new Disciplina("Banco de Dados I", 60));

        Modulo segundo = new Modulo("2o semestre");
        segundo.adicionar(new Disciplina("Tecnicas de Programacao II", 80));
        segundo.adicionar(new Disciplina("Desenvolvimento Web II", 80));

        curso.adicionar(primeiro);
        curso.adicionar(segundo);
        curso.adicionar(new Disciplina("Projeto Integrador", 40));

        System.out.println("   Antes do submodulo:");
        exibirEstrutura(curso);

        Modulo optativas = new Modulo("Optativas");
        optativas.adicionar(new Disciplina("Computacao em Nuvem", 40));
        optativas.adicionar(new Disciplina("Seguranca da Informacao", 40));
        segundo.adicionar(optativas);

        System.out.println();
        System.out.println("   Depois do submodulo de terceiro nivel:");
        exibirEstrutura(curso);
    }

    private static void exibirRelatorio(String titulo, Relatorio relatorio) {
        System.out.println("   --- " + titulo + " ---");
        for (String linha : relatorio.gerar().split("\n")) {
            System.out.println("   " + linha);
        }
    }

    private static void exibirEstrutura(ItemCurricular raiz) {
        raiz.imprimir(1);
        System.out.println("   Carga total: " + raiz.calcularCarga() + "h");
    }
}
