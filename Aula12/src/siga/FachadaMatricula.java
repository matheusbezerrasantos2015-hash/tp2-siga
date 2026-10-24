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

    public Matricula matricular(Aluno aluno, String codigoTurma, boolean turmaLotada) {
        Matricula nova = new Matricula(aluno, codigoTurma, seletorPolitica.escolherPara(aluno));
        armazem.add(nova);

        publicador.publicar(nova, turmaLotada);

        return nova;
    }

    public List<Matricula> listar() {
        return new ArrayList<>(armazem);
    }
}
