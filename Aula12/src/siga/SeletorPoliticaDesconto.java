package siga;

/**
 * Escolhe a política de desconto de um aluno. Quem monta a matrícula usa este
 * seletor; a Matricula e o GeradorBoleto apenas recebem a política e delegam.
 */
public class SeletorPoliticaDesconto {

    public PoliticaDesconto escolherPara(Aluno aluno) {
        if (aluno.isBolsista()) {
            return new DescontoBolsista();
        } else if (aluno.isServidor()) {
            return new DescontoServidor();
        } else if (aluno.isEgresso()) {
            return new DescontoEgresso();
        }
        return new SemDesconto();
    }
}
