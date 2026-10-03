package siga;

/**
 * Facade do subsistema de matrícula.
 *
 * Concentra os colaboradores e a ordem correta das chamadas (validar CPF,
 * localizar o aluno, verificar vaga, calcular desconto, registrar, reservar
 * a vaga e notificar), oferecendo uma única operação à camada de apresentação.
 */
public class FachadaMatricula {

    private final ValidadorCpf validadorCpf;
    private final AlunoDAO alunoDAO;
    private final ControleVagas controleVagas;
    private final CalculadoraDesconto calculadoraDesconto;
    private final MatriculaDAO matriculaDAO;
    private final NotificadorEmail notificador;

    public FachadaMatricula(ValidadorCpf validadorCpf, AlunoDAO alunoDAO,
                            ControleVagas controleVagas,
                            CalculadoraDesconto calculadoraDesconto,
                            MatriculaDAO matriculaDAO,
                            NotificadorEmail notificador) {
        this.validadorCpf = validadorCpf;
        this.alunoDAO = alunoDAO;
        this.controleVagas = controleVagas;
        this.calculadoraDesconto = calculadoraDesconto;
        this.matriculaDAO = matriculaDAO;
        this.notificador = notificador;
    }

    /**
     * @throws IllegalArgumentException se o CPF for inválido
     * @throws IllegalStateException    se o aluno não existir ou a turma não tiver vagas
     */
    public Matricula matricular(String matricula, String cpf, String codigoTurma) {
        if (!validadorCpf.valido(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new IllegalStateException("Aluno não encontrado: " + matricula);
        }
        if (!controleVagas.haVaga(codigoTurma)) {
            throw new IllegalStateException("Turma sem vagas: " + codigoTurma);
        }

        double desconto = calculadoraDesconto.calcular(aluno);
        Matricula nova = new Matricula(aluno, codigoTurma, desconto);

        matriculaDAO.inserir(nova);
        controleVagas.reservar(codigoTurma);
        notificador.enviarConfirmacao(aluno, nova);
        return nova;
    }
}
