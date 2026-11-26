package siga;

/**
 * Escolhe a política de desconto de um aluno. Quem monta a matrícula usa este
 * seletor; a Matricula e o GeradorBoleto apenas recebem a política e delegam.
 */
public class SeletorPoliticaDesconto {

    /**
     * @param aluno aluno cuja política será escolhida; não pode ser
     *              {@code null}
     * @return uma política nova, nunca {@code null}. Os descontos <b>não se
     *         acumulam</b>: vale a primeira condição verdadeira, na ordem
     *         bolsista (50%), servidor (20%), egresso (10%); sem nenhuma, a
     *         política {@link SemDesconto}
     */
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
