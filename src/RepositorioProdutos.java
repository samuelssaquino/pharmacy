import java.util.ArrayList;

public class RepositorioProdutos {

    ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void inserirProduto(Produto p) {
        this.produtos.add(p);
    }

    public int qtdDeProdutosArmazenados() {
        return this.produtos.size();
    }

    public ArrayList<Produto> getAll() {
        return this.produtos;
    }

    public Produto procurarPorNome(String descricao) {
        Produto produtoProcurado = null;

        for (int i = 0; i < this.produtos.size(); i++) {
            Produto p = this.produtos.get(i);

            if (p.getDescricao().equals(descricao)) {
                produtoProcurado = p;
                break;

            }
        }

        if (produtoProcurado == null) {
            System.out.println("");
            System.out.println("Nome não cadastrado no sistema.");
            System.out.println("Informe um nome de produto válido");
            System.out.println("");
        }

        return produtoProcurado;
    }

    public Produto procurarPorCodigo(String codigo) {
        Produto produtoProcurado = null;

        for (int i = 0; i < this.produtos.size(); i++) {
            Produto p = this.produtos.get(i);

            if (p.getCodigo().equals(codigo)) {
                produtoProcurado = p;
                break;

            }
        }

        if (produtoProcurado == null) {
            System.out.println("");
            System.out.println("Código não cadastrado no sistema.");
            System.out.println("Informe um código de produto válido");
            System.out.println("");
        }

        return produtoProcurado;
    }
}

