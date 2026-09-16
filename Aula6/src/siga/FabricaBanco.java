package siga;

public abstract class FabricaBanco {

    public abstract Conexao criarConexao();

    public abstract Comando criarComando();

}