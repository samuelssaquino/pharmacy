import java.util.ArrayList;

public class Venda {

    private int codigoDaVenda;
    private int qtdItens;
    private double precoTotal;
    Cliente cliente;
    ArrayList<Produto> produtos;

    public int getCodigoDaVenda() {
        return codigoDaVenda;
    }

    public void setCodigoDaVenda(int codigoDaVenda) {
        this.codigoDaVenda = codigoDaVenda;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens += qtdItens;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double preco, int qtd, double desconto) {
        this.precoTotal += (preco - desconto) * qtd;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

