package siga;

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