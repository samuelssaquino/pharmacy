import java.util.ArrayList;

public class RepositorioFornecedor {

    ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

    public void inserir(Fornecedor c) {
        this.fornecedores.add(c);
    }

    public int qtdDeFornecedoresArmazenadas() {
        return this.fornecedores.size();
    }

    public ArrayList<Fornecedor> getAll() {
        return this.fornecedores;
    }

    public Fornecedor procurarFornecedorPorCodigo(String codigo) {
        Fornecedor fornecedorProcurado = null;

        for (int i = 0; i < this.fornecedores.size(); i++) {
            Fornecedor f = this.fornecedores.get(i);

            if(f.getCodigo().equals(codigo)) {
                fornecedorProcurado = f;
                break;

            }
        }
        if (fornecedorProcurado == null) {
            System.out.println("");
            System.out.println("Código não cadastrado no sistema.");
            System.out.println("Informe um código de fornecedor válido");
            System.out.println("");
        }


        return fornecedorProcurado;
    }

    public Fornecedor procurarFornecedorPorNome(String nome) {
        Fornecedor fornecedorProcurado = null;

        for (int i = 0; i < this.fornecedores.size(); i++) {
            Fornecedor f = this.fornecedores.get(i);

            if(f.getNome().equals(nome)) {
                fornecedorProcurado = f;
                break;

            }
        }

        if (fornecedorProcurado == null) {
            System.out.println("");
            System.out.println("Nome não cadastrado no sistema.");
            System.out.println("Informe um nome de fornecedor válido");
            System.out.println("");
        }

        return fornecedorProcurado;
    }
}

