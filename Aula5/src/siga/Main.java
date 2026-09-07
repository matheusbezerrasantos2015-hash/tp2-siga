package siga;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade Factory (Factory Method) ===\n");

        GerenciadorLogin login = new GerenciadorLogin();

        // Simulação de logins de diferentes perfis.
        login.montarPainel(new CriadorPainelAluno());
        System.out.println();

        login.montarPainel(new CriadorPainelProfessor());
        System.out.println();

        login.montarPainel(new CriadorPainelCoordenador());
        System.out.println();

        // Novo perfil adicionado sem modificar o GerenciadorLogin.
        login.montarPainel(new CriadorPainelSecretaria());

        System.out.println("\nNovo perfil SECRETARIA adicionado com sucesso!");
        System.out.println("O GerenciadorLogin não precisou ser modificado.");
        System.out.println("O sistema foi estendido através da criação de novas classes,");
        System.out.println("demonstrando o respeito ao Princípio Aberto/Fechado (OCP).");
    }
}


