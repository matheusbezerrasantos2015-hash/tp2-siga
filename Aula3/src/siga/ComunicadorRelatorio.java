package siga;

public class ComunicadorRelatorio {
    
    public void enviarPorEmail(String conteudo, String destinatario) {
        //Simulação de envio de email
        System.out.println("[email] Enviando relatorio para: " + destinatario);
        System.out.println(conteudo);
    }
}
