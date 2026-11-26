package siga;

import java.util.ArrayList;
import java.util.List;

/** Orquestra os passos da matrícula: criar, gravar e publicar o fato consumado. */
public class FachadaMatricula {

    private final List<Matricula> armazem = new ArrayList<>();
    private final SeletorPoliticaDesconto seletorPolitica;
    private final PublicadorMatricula publicador;

    public FachadaMatricula(SeletorPoliticaDesconto seletorPolitica, PublicadorMatricula publicador) {
        this.seletorPolitica = seletorPolitica;
        this.publicador = publicador;
    }

    /**
     * Cria a matrícula com a política de desconto do aluno, guarda e só então
     * avisa os ouvintes.
     *
     * @param aluno       aluno a matricular
     * @param codigoTurma código da turma, por exemplo {@code "DSM-2A"}
     * @param turmaLotada informado por quem chama e apenas repassado aos
     *                    ouvintes; esta classe não controla vagas
     * @return a matrícula gravada. A falha de um ouvinte não desfaz a
     *         matrícula nem chega a quem chamou. Não há verificação de
     *         matrícula duplicada
     */
    public Matricula matricular(Aluno aluno, String codigoTurma, boolean turmaLotada) {
        Matricula nova = new Matricula(aluno, codigoTurma, seletorPolitica.escolherPara(aluno));
        armazem.add(nova);

        publicador.publicar(nova, turmaLotada);

        return nova;
    }

    /** @return cópia da lista de matrículas, na ordem em que foram feitas */
    public List<Matricula> listar() {
        return new ArrayList<>(armazem);
    }
}
