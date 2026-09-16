package siga;

public class ConsultaBuilder {

    private String tabela;
    private String filtro;
    private String ordenacao;
    private int limite;
    private int offset;
    private int timeoutSegundos;
    private boolean somenteAtivos;

    public ConsultaBuilder(String tabela) {
        this.tabela = tabela;
    }

    public ConsultaBuilder comFiltro(String filtro) {
        this.filtro = filtro;
        return this;
    }

    public ConsultaBuilder comOrdenacao(String ordenacao) {
        this.ordenacao = ordenacao;
        return this;
    }

    public ConsultaBuilder comLimite(int limite) {
        this.limite = limite;
        return this;
    }

    public ConsultaBuilder comOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public ConsultaBuilder comTimeoutSegundos(int timeoutSegundos) {
        this.timeoutSegundos = timeoutSegundos;
        return this;
    }

    public ConsultaBuilder somenteAtivos() {
        this.somenteAtivos = true;
        return this;
    }

    public String construir() {
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(this.tabela);

        if (this.filtro != null) {
            sb.append(" WHERE ").append(this.filtro);
        }

        if (this.somenteAtivos) {
            sb.append(this.filtro != null
                    ? " AND ativo = 1"
                    : " WHERE ativo = 1");
        }

        if (this.ordenacao != null) {
            sb.append(" ORDER BY ").append(this.ordenacao);
        }

        if (this.limite > 0) {
            sb.append(" LIMIT ").append(this.limite);
        }

        if (this.offset > 0) {
            sb.append(" OFFSET ").append(this.offset);
        }

        return sb.toString();
    }
}