package siga;

/** Cliente de demonstração: monta as políticas, o publicador e a fachada. */
public class Main {

    private static final double VALOR_BASE = 800.00;

    public static void main(String[] args) {
        System.out.println("=== SIGA - Aula 12: Strategy e Observer ===");

        Aluno bolsista = new Aluno("2024001", "Maria Silva");
        bolsista.setBolsista(true);

        Aluno egresso = new Aluno("2024003", "Ana Pereira");
        egresso.setEgresso(true);

        SeletorPoliticaDesconto seletor = new SeletorPoliticaDesconto();
        demonstrarDescontos(seletor, bolsista, egresso);
        demonstrarReacoes(seletor, bolsista, egresso);
    }

    private static void demonstrarDescontos(SeletorPoliticaDesconto seletor, Aluno... alunos) {
        System.out.println();
        System.out.println("1) Desconto calculado pela mesma politica na matricula e no boleto");
        GeradorBoleto gerador = new GeradorBoleto();
        for (Aluno aluno : alunos) {
            PoliticaDesconto politica = seletor.escolherPara(aluno);
            Matricula matricula = new Matricula(aluno, "DSM-2A", politica);
            System.out.printf("   %-14s matricula: R$ %.2f%n",
                    aluno.getNome(), matricula.calcularValor(VALOR_BASE));
            gerador.emitir(aluno, VALOR_BASE, politica);
        }
    }

    private static void demonstrarReacoes(SeletorPoliticaDesconto seletor,
            Aluno bolsista, Aluno egresso) {
        System.out.println();
        System.out.println("2) Reacoes a matricula como observadores");
        PublicadorMatricula publicador = new PublicadorMatricula();
        publicador.registrar(new NotificadorEmail());
        publicador.registrar(new PainelVagas());
        publicador.registrar(new AvisoCoordenacao());
        publicador.registrar(new RegistroAuditoria());
        FachadaMatricula fachada = new FachadaMatricula(seletor, publicador);

        fachada.matricular(bolsista, "DSM-2A", false);

        System.out.println();
        System.out.println("   Turma lotada, com o aviso a coordenacao falhando:");
        fachada.matricular(egresso, "DSM-2B", true);

        System.out.println();
        System.out.println("3) Quinta reacao: uma classe nova e uma linha de registro");
        publicador.registrar(new NotificadorSms());
        fachada.matricular(bolsista, "DSM-2C", false);

        System.out.println();
        System.out.println("   Matriculas gravadas: " + fachada.listar().size());
    }
}
