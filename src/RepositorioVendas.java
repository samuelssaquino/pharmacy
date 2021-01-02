import java.util.ArrayList;

public class RepositorioVendas {

    //INSTANCIA REPOSITORIO DE VENDAS
    ArrayList<Venda> vendas = new ArrayList<>();

    public void inserirVenda(Venda V) {
        this.vendas.add(V);
    }

    public int qtdTotalDeVendasRealizadas() {
        return this.vendas.size();
    }

    public ArrayList<Venda> getAll() {
        return this.vendas;
    }

    public Venda procurarVendaPorCodigo(int codigoDaVenda) {
        Venda vendaProcurada = null;

        for (int i = 0; i < this.vendas.size(); i++) {
            Venda v = this.vendas.get(i);

            if(v.getCodigoDaVenda() == codigoDaVenda) {
                vendaProcurada = v;
                break;

            }
        }

        if (vendaProcurada == null) {
            System.out.println("");
            System.out.println("Código não cadastrado no sistema.");
            System.out.println("Informe um código de venda válido");
            System.out.println("");
        }

        return vendaProcurada;
    }
}

