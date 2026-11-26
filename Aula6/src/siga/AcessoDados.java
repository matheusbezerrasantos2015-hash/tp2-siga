package siga;

/**
 * Ponto único de acesso aos dados (Singleton). A instância é criada na
 * primeira chamada de {@link #getInstancia()} e a criação <b>não</b> é
 * segura para várias threads.
 */
public class AcessoDados {

    private Conexao conexao;
    private Comando comando;

    // Instância única
    private static AcessoDados instancia;

    // Construtor privado
    private AcessoDados() {
    }

    // Ponto de acesso controlado
    public static AcessoDados getInstancia() {
        if (instancia == null) {
            instancia = new AcessoDados();
        }
        return instancia;
    }

    /**
     * Abre a conexão e executa uma consulta de teste ({@code SELECT * FROM
     * alunos}) usando a fábrica do fornecedor.
     *
     * @param fornecedor {@code "MYSQL"} (exatamente assim, em maiúsculas)
     *                   escolhe o MySQL; <b>qualquer outro texto</b>, mesmo
     *                   com erro de digitação, escolhe o PostgreSQL
     * @throws NullPointerException se {@code fornecedor} for {@code null}
     */
    public void conectar(String fornecedor) {
        FabricaBanco fabrica;

        if (fornecedor.equals("MYSQL")) {
            fabrica = new FabricaMySQL();
        } else {
            fabrica = new FabricaPostgreSQL();
        }

        conexao = fabrica.criarConexao();
        comando = fabrica.criarComando();

        conexao.abrir();
        comando.executar("SELECT * FROM alunos");
    }
}