import java.time.LocalDate;

public class Cosmetico extends Produto{

    public Cosmetico(String codigo, String descricao, int estoque, LocalDate validade, double preco, Fornecedor fornecedor) {
        super(codigo, descricao, estoque, validade, preco, fornecedor);
    }

    @Override
    public double descontoMax(){

        double desconto = 0.00;

        //15% DE DESCONTO NOS COSMÉTICOS ATÉ R$30,00
        //20% DE DESCONTO NOS COSMÉTICOS ATÉ R$70,00

        if(getPreco() <= 30.00){
            desconto = 0.15 * getPreco();
        }else if(getPreco() <=70.00){
            desconto = 0.2 * getPreco();
        }
        return desconto;
    }
}
