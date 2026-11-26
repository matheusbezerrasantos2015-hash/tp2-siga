package siga;

/** Monta o painel inicial do usuário sem conhecer o perfil dele. */
public class GerenciadorLogin {

    /**
     * @param criador criador do perfil que acabou de logar; não pode ser
     *                {@code null}
     * @return o painel que foi criado e já montado (já exibido no console)
     */
    public Painel montarPainel(CriadorPainel criador) {

        Painel painel = criador.criarPainel();

        painel.montar();

        return painel;
    }
}
