import java.util.ArrayList;

public class RepositorioClientes {

    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public void inserirCliente(Cliente c) {
        this.clientes.add(c);
    }

    public int qtdDeClientesArmazenados() {
        return this.clientes.size();
    }

    public ArrayList<Cliente> getAll() {
        return this.clientes;
    }

    public Cliente procurarPorCpf(String cpf) {
        Cliente clienteProcurado = null;

        for (int i = 0; i < this.clientes.size(); i++) {
            Cliente c = this.clientes.get(i);

            if (c.getCpf().equals(cpf)) {
                clienteProcurado = c;
                break;
            }
        }

        if (clienteProcurado == null) {
            System.out.println("");
            System.out.println("CPF não cadastrado no sistema.");
            System.out.println("Informe um CPF válido");
            System.out.println("");
        }

        return clienteProcurado;
    }

    public Cliente procurarPorNome(String nome) {
        Cliente clienteProcurado = null;

        for (int i = 0; i < this.clientes.size(); i++) {
            Cliente c = this.clientes.get(i);

            if (c.getNome().equals(nome)) {
                clienteProcurado = c;
                break;
            }
        }

        if (clienteProcurado == null) {
            System.out.println("");
            System.out.println("Nome não cadastrado no sistema.");
            System.out.println("Informe um nome válido");
            System.out.println("");
        }

        return clienteProcurado;
    }
}

