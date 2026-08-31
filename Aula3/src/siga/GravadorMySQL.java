package siga;

public class GravadorMySQL implements MatriculaRepositorio {
    
    @Override
    public void gravar(String dados) {
        // Simulação de gravação em banco MySQL.
        System.out.println("[MySQL] Gravando: " + dados);
    }
}
