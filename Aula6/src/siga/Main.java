package siga;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade de Padrões Criacionais (código inicial) ===\n");

        AcessoDados acesso = AcessoDados.getInstancia();
        acesso.conectar("MYSQL");
        System.out.println();
        acesso.conectar("POSTGRESQL");

        System.out.println();

        ConsultaBuilder builder = new ConsultaBuilder("aluno");

        String consulta = builder
                .comFiltro("curso = 'DSM'")
                .comOrdenacao("nome")
                .comLimite(50)
                .comTimeoutSegundos(30)
                .somenteAtivos()
                .construir();

        System.out.println("Consulta montada: " + consulta);

        System.out.println();

        AcessoDados outraReferencia = AcessoDados.getInstancia();

        System.out.println("As duas referências apontam para a mesma instância? "
                + (acesso == outraReferencia));

        System.out.println("\nProblemas resolvidos:");
        System.out.println("- Abstract Factory: garante a criação de objetos compatíveis de cada fornecedor.");
        System.out.println("- Builder: facilita a montagem de consultas com vários parâmetros opcionais.");
        System.out.println("- Singleton: garante uma única instância de AcessoDados.");
    }
}