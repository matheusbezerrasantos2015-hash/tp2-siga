package siga;

public class FabricaMySQL extends FabricaBanco {

    @Override
    public Conexao criarConexao() {
        return new ConexaoMySQL();
    }

    @Override
    public Comando criarComando() {
        return new ComandoMySQL();
    }
}