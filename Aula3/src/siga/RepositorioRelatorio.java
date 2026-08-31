package siga;

public class RepositorioRelatorio {

    public void salvarEmArquivo(String conteudo, String caminho) {
        System.out.println("[disco] Gravando relatório em: " + caminho);
        System.out.println(conteudo);
    }
    
}
