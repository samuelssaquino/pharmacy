import java.time.LocalDate;

public class Medicamento extends Produto{

    public Medicamento(String codigo, String descricao, int estoque, LocalDate validade, double preco, Fornecedor fornecedor) {
        super(codigo, descricao, estoque, validade, preco, fornecedor);
    }

    @Override
    public double descontoMax(){

        double desconto = 0.00;

        //5% DE DESCONTO NOS COSMÉTICOS ATÉ R$6,00
        //10% DE DESCONTO NOS COSMÉTICOS ATÉ R$12,00

        if(getPreco() <= 6.00){
            desconto = 00.5 * getPreco();
        }else if(getPreco() <=12.00){
            desconto = 0.1 * getPreco();
        }
        return desconto;
    }
}
