import java.time.LocalDate;

public abstract class Produto {

    private String codigo;
    private String descricao;
    private int estoque;
    private LocalDate validade;
    private double preco;
    private Fornecedor fornecedor;

    public Produto(String codigo, String descricao, int estoque, LocalDate validade, double preco, Fornecedor fornecedor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.estoque = estoque;
        this.validade = validade;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public double getPreco() {
        return preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public int adicionaEstoque(int qtd) {
        return this.estoque += qtd;
    }

    public boolean subtraiEstoque(int qtdDesejada) {

        if (this.estoque <= 0) {
            System.out.println("");
            System.out.println("Produto em falta, estoque insufuciente!");
            System.out.println("");
            return false;
        } else if (this.estoque < qtdDesejada) {
            System.out.println("");
            System.out.println("Estoque menor que a quantidade desejada!");
            System.out.println("");
            return false;
        } else if (qtdDesejada <= 0) {
            System.out.println("");
            System.out.println("Quantidade inválida!");
            System.out.println("");
            return false;
        } else if (qtdDesejada <= this.estoque) {
            this.estoque -= qtdDesejada;
            return true;
        } else {
            System.out.println("");
            System.out.println("nenhum dos fluxos anteriores");
            System.out.println("");
        }
        return false;
    }

    public abstract double descontoMax();
}
