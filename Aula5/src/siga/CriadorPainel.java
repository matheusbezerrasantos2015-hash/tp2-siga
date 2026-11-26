package siga;

/**
 * Criador do Factory Method: cada perfil de usuário tem uma subclasse que
 * decide qual {@link Painel} concreto instanciar.
 */
public abstract class CriadorPainel {

    /**
     * @return um painel novo a cada chamada, ainda <b>não montado</b>: quem
     *         chama (por exemplo {@link GerenciadorLogin}) é que invoca
     *         {@link Painel#montar()}
     */
    public abstract Painel criarPainel();

}
