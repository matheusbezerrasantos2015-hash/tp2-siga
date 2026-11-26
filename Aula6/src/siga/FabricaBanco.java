package siga;

/**
 * Abstract Factory da família de acesso a dados. Conexão e comando criados
 * pela mesma fábrica pertencem ao mesmo fornecedor (MySQL ou PostgreSQL).
 */
public abstract class FabricaBanco {

    /** @return uma conexão do fornecedor desta fábrica, ainda fechada */
    public abstract Conexao criarConexao();

    /** @return um comando do mesmo fornecedor da conexão desta fábrica */
    public abstract Comando criarComando();

}