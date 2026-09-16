package siga;

public class FabricaPostgreSQL extends FabricaBanco {

    @Override
    public Conexao criarConexao() {
        return new ConexaoPostgreSQL();
    }

    @Override
    public Comando criarComando() {
        return new ComandoPostgreSQL();
    }
}