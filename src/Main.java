import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner teclado = new Scanner(System.in);

        LocalDateTime ldtNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");
        String format2 = formatter.format(ldtNow);

        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                                                                                          ");
        System.out.println("                                              ======== PHARMACIE =========                                "+ format2);
        System.out.println("                                               TESTS AGILES - CESAR SCHOOL                                                " );
        System.out.println("                                              ======= SAMUEL AQUINO ======                                                " );
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");

        //INSTÂNCIA DOS REPOSITÓRIOS
        RepositorioProdutos repositorioProdutos = new RepositorioProdutos();
        RepositorioClientes repositorioClientes = new RepositorioClientes();
        RepositorioFornecedor repositorioFornecedor = new RepositorioFornecedor();
        RepositorioVendas repositorioVendas = new RepositorioVendas();

        //INSTÂNCIAS DOS PRAZOS DE VALIDADE
        Period umAno = Period.ofYears(1);
        Period doisAnos = Period.ofYears(2);
        Period tresAnos = Period.ofYears(3);
        Period quatroAnos = Period.ofYears(4);

        //DATA DE HOJE
        LocalDate ld = LocalDate.now();

        LocalDate validadeDorflex = ld.plus(umAno);
        LocalDate validadeParacetamol = ld.plus(doisAnos);
        LocalDate validadeShampoo = ld.plus(tresAnos);
        LocalDate validadeProtetorSolar = ld.plus(quatroAnos);

        //CARREGAR MASSA ATRAVÉS DO CONSTRUTOR
        Cliente cliente1 = new Cliente("489.236.030-99", "José");
        Cliente cliente2 = new Cliente("564.590.960-74", "Maria");
        Cliente cliente3 = new Cliente("890.573.330-15", "Pedro");
        Cliente cliente4 = new Cliente("936.871.980-22", "Ana");
        Cliente cliente5 = new Cliente("686.467.440-19", "Carlos");
        Cliente cliente6 = new Cliente("763.663.030-37", "Bia");

        Fornecedor forn1 = new Fornecedor("01", "Aché");
        Fornecedor forn2 = new Fornecedor("02", "Medic");
        Fornecedor forn3 = new Fornecedor("03", "Dislab");
        Fornecedor forn4 = new Fornecedor("04", "Merck");

        Medicamento med1 = new Medicamento("01", "Dorflex", 100, validadeDorflex, 5.52, forn1);
        Medicamento med2 = new Medicamento("02", "Paracetamol", 100, validadeParacetamol, 7.50, forn2);
        Cosmetico cos1 = new Cosmetico("03", "Shampoo", 100, validadeShampoo, 22.79, forn3);
        Cosmetico cos2 = new Cosmetico("04", "ProtetorSolar", 100, validadeProtetorSolar, 68.99, forn4);

        //INSERIR MASSA NO REPOSITÓRIO
        repositorioProdutos.inserirProduto(med1);
        repositorioProdutos.inserirProduto(med2);
        repositorioProdutos.inserirProduto(cos1);
        repositorioProdutos.inserirProduto(cos2);

        repositorioFornecedor.inserir(forn1);
        repositorioFornecedor.inserir(forn2);
        repositorioFornecedor.inserir(forn3);
        repositorioFornecedor.inserir(forn4);

        repositorioClientes.inserirCliente(cliente1);
        repositorioClientes.inserirCliente(cliente2);
        repositorioClientes.inserirCliente(cliente3);
        repositorioClientes.inserirCliente(cliente4);
        repositorioClientes.inserirCliente(cliente5);
        repositorioClientes.inserirCliente(cliente6);

        String opcao = "";
        while (!opcao.equals("0")) {
            //MENU PRINCIPAL
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.print("                       [1-COMPRAR]");
            System.out.print(" [2-PRODUTOS]");
            System.out.print(" [3-CLIENTES]");
            System.out.print(" [4-FORNECEDORES]");
            System.out.print(" [5-VENDAS]");
            System.out.println(" [0-SAIR]");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.print("                                                Digite uma das opções: ");
            opcao = teclado.next();

            System.out.flush();


            switch (opcao) {
                //COMPRAR
                case "1":

                    System.out.println("");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                             ===== COMPRAR PRDDUTO =====                                 ");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("");

                    //INSTANCIA A VENDA
                    Venda v = new Venda();
                    //INSTANCIA A LISTA DE PRODUTOS DA VENDA
                    v.produtos = new ArrayList<Produto>();
                    //QUANTIDADE TOTAL DE ITENS VENDIDOS
                    int qtdTotalDeItensVendidos = 0;

                    Cliente clienteProcurado;
                    do {
                        //CLIENTE QUE REALIZA A COMPRA
                        String cpf;
                        System.out.print("Digite o CPF: ");
                        cpf = teclado.next();

                        //PROCURA O CLIENTE NO REPOSITÓRIO
                        clienteProcurado = repositorioClientes.procurarPorCpf(cpf);

                    } while (clienteProcurado == null);

                    System.out.println("Olá, " + clienteProcurado.getNome());
                    System.out.println("");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");


                    char resp;
                    //LOOP PARA ADICIONAR MAIS PRODUTOS À VENDA
                    do {
                        Produto produtoProcurado;
                        do {
                            //PRODUTO A SER COMPRADO INFORMADO
                            String nomeProduto;
                            System.out.print("Nome Produto: ");
                            nomeProduto = teclado.next();

                            //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                            produtoProcurado = repositorioProdutos.procurarPorNome(nomeProduto);

                        } while (produtoProcurado == null);


                        System.out.printf("Preço: R$ %.2f", produtoProcurado.getPreco());
                        System.out.println("");
                        System.out.printf("Desconto: R$ %.2f", produtoProcurado.descontoMax());
                        System.out.println("");
                        System.out.println("Estoque: " + produtoProcurado.getEstoque() + " unidade(s)");

                        boolean validaQtd = false;
                        String qtdS;
                        //VALIDA A QUANTIDADE NO ESTOQUE
                        do {
                            System.out.print("");
                            // VALIDACAO DE ESTOQUE ZERO
                            if (produtoProcurado.getEstoque() == 0) {
                                validaQtd = true;
                                System.out.println("");
                                System.out.println("Estoque insulficiente, selecione um produto que tenha no estoque!");
                                System.out.println("");
                            } else {

                                int qtd = 0;
                                boolean tipoInt;

                                // VALIDA SE O INPUT DA QUANTIDADE É UM INTEGER
                                do {
                                    System.out.print("Informe a quantidade desejada: ");
                                    qtdS = teclado.next();

                                    try {
                                        qtd = Integer.parseInt(qtdS);
                                        tipoInt = true;

                                    } catch (Exception e) {
                                        System.out.println("");
                                        System.out.println("O tipo é inválido, informe um tipo válido!");
                                        System.out.println("");
                                        tipoInt = false;
                                    }
                                } while (!tipoInt);

                                // VALIDA A SUBTRACAO QUANTIDADE NO ESTOQUE
                                validaQtd = produtoProcurado.subtraiEstoque(qtd);


                                if (validaQtd) {

                                    //INCREMENTA A QUANTIDADE TOTAL DE ITENS DA VENDA
                                    v.setQtdItens(qtd);

                                    //CALCULA E SETA O PREÇO TOTAL DA VENDA
                                    v.setPrecoTotal(produtoProcurado.getPreco(), qtd, produtoProcurado.descontoMax());

                                    //INSERE O CLIENTE DA VENDA
                                    v.setCliente(clienteProcurado);

                                    //INSERE O PRODUTO DA VENDA
                                    v.produtos.add(produtoProcurado);
                                }
                            }

                        } while (validaQtd == false);

                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("VALOR PARCIAL: R$ %.2f", v.getPrecoTotal());
                        System.out.println("");
                        //LOOP PARA CONTINUAR VENDENDO
                        System.out.print("Deseja adicionar mais produtos a esta compra? - (S/N)? ");
                        resp = teclado.next().charAt(0);
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                    } while (resp != 'n');

                    if (!(v.getQtdItens() <= 0)) {
                        //GERADOR DE CODIGO RANDOMICO
                        Random generator = new Random();
                        int codVenda;
                        codVenda = generator.nextInt(999999999);

                        //INSERE O CÓDIGO DA VENDA RANDOMICAMENTE
                        v.setCodigoDaVenda(codVenda);

                        //INSERE A VENDA NO REPOSITÓRIO DE VENDAS
                        repositorioVendas.inserirVenda(v);
                        System.out.println("                                                 ===== FATURA =====                           ");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");

                        System.out.println("Código da venda: " + v.getCodigoDaVenda());
                        System.out.println("                                ");
                        System.out.println("Produto(s): ");
                        for (int x = 0; x < v.produtos.size(); x++) {
                            System.out.println("  * " + v.produtos.get(x).getDescricao());
                        }
                        System.out.println("");
                        System.out.println("Quantidade total de itens: " + v.getQtdItens());
                        System.out.printf("VALOR TOTAL: R$ %.2f", v.getPrecoTotal());
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("                                          Sua compra foi realizada com sucesso!                       ");
                        System.out.println("                                                     Volte Sempre!                             ");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                    } else {
                        System.out.println("");
                        System.out.println("                                              A compra não foi realizada!");
                        System.out.println("");
                    }

                    break;

                //PRODUTOS
                case "2":

                    String opcaoProduto = "";
                    while (!opcaoProduto.equals("0")) {
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("                              [1-PRODUTO POR CÓDIGO]");
                        System.out.print(" [2-PRODUTO POR NOME]");
                        System.out.println(" [3-MEDICAMENTOS]");
                        System.out.println("");
                        System.out.print("                           [4-COSMÉTICOS]");
                        System.out.print(" [5-TODOS OS PRODUTOS]");
                        System.out.println(" [6-ADICIONAR ITENS NO ESTOQUE]");
                        System.out.println("");
                        System.out.println("                                                  [0-MENU PRINCIPAL]");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        System.out.print("                                                Digite uma das opções: ");
                        opcaoProduto = teclado.next();

                        switch (opcaoProduto) {
                            case "1":
                                Produto produtoProcuradoPorCodigo;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String codProduto;
                                    System.out.print("Código do Produto: ");
                                    codProduto = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    produtoProcuradoPorCodigo = repositorioProdutos.procurarPorCodigo(codProduto);

                                } while (produtoProcuradoPorCodigo == null);

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                             ===== PRODUTO POR CÓDIGO =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                System.out.println("Código do Produto: " + produtoProcuradoPorCodigo.getCodigo());
                                System.out.println("Descrição: " + produtoProcuradoPorCodigo.getDescricao());
                                System.out.println("Estoque: " + produtoProcuradoPorCodigo.getEstoque() + " unidade(s)");
                                System.out.println("Validade: F " + ld + " | V " + produtoProcuradoPorCodigo.getValidade());
                                System.out.println("Fornecedor: " + produtoProcuradoPorCodigo.getFornecedor().getNome());
                                System.out.printf("Preço Unitário: R$ %.2f", produtoProcuradoPorCodigo.getPreco());
                                System.out.println("");
                                System.out.printf("Desconto: R$ %.2f", produtoProcuradoPorCodigo.descontoMax());
                                System.out.println("");
                                System.out.println("");

                                break;
                            case "2":
                                Produto produtoProcuradoPorNome;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String nomeProduto;
                                    System.out.print("Nome do Produto: ");
                                    nomeProduto = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    produtoProcuradoPorNome = repositorioProdutos.procurarPorNome(nomeProduto);

                                } while (produtoProcuradoPorNome == null);

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                             ===== PRODUTO POR NOME =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                System.out.println("Código do Produto: " + produtoProcuradoPorNome.getCodigo());
                                System.out.println("Descrição: " + produtoProcuradoPorNome.getDescricao());
                                System.out.println("Estoque: " + produtoProcuradoPorNome.getEstoque() + " unidade(s)");
                                System.out.println("Validade: F " + ld + " | V " + produtoProcuradoPorNome.getValidade());
                                System.out.println("Fornecedor: " + produtoProcuradoPorNome.getFornecedor().getNome());
                                System.out.printf("Preço Unitário: R$ %.2f", produtoProcuradoPorNome.getPreco());
                                System.out.println("");
                                System.out.printf("Desconto: R$ %.2f", produtoProcuradoPorNome.descontoMax());
                                System.out.println("");
                                System.out.println("");
                                break;
                            case "3":
                                ArrayList<Produto> medicamentos = repositorioProdutos.getAll();

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                        ===== PRODUTOS - MEDICAMENTOS =====                             ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                for (int i = 0; i < medicamentos.size(); i++) {
                                    Produto p = medicamentos.get(i);

                                    if (p instanceof Medicamento) {
                                        System.out.println("Código do Produto: " + p.getCodigo());
                                        System.out.println("Descrição: " + p.getDescricao());
                                        System.out.println("Estoque: " + p.getEstoque() + " unidade(s)");
                                        System.out.println("Validade: F " + ld + " | V " + p.getValidade());
                                        System.out.println("Fornecedor: " + p.getFornecedor().getNome());
                                        System.out.printf("Preço Unitário: R$ %.2f", p.getPreco());
                                        System.out.println("");
                                        System.out.printf("Desconto: R$ %.2f", p.descontoMax());
                                        System.out.println("");
                                        System.out.println("");
                                    }
                                }
                                break;
                            case "4":

                                ArrayList<Produto> cosmeticos = repositorioProdutos.getAll();

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                         ===== PRODUTOS - COSMÉTICOS =====                                ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                for (int i = 0; i < cosmeticos.size(); i++) {
                                    Produto p = cosmeticos.get(i);

                                    if (p instanceof Cosmetico) {
                                        System.out.println("Código do Produto: " + p.getCodigo());
                                        System.out.println("Descrição: " + p.getDescricao());
                                        System.out.println("Estoque: " + p.getEstoque() + " unidade(s)");
                                        System.out.println("Validade: F " + ld + " | V " + p.getValidade());
                                        System.out.println("Fornecedor: " + p.getFornecedor().getNome());
                                        System.out.printf("Preço Unitário: R$ %.2f", p.getPreco());
                                        System.out.println("");
                                        System.out.printf("Desconto: R$ %.2f", p.descontoMax());
                                        System.out.println("");
                                        System.out.println("");
                                    }
                                }

                                break;
                            case "5":

                                ArrayList<Produto> todosOsProdutos = repositorioProdutos.getAll();
                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                             =====TODOS OS PRODUTOS =====                                                 ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");
                                System.out.println("Quantidade total de produtos cadastrados: " + repositorioProdutos.qtdDeProdutosArmazenados());
                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                                for (int i = 0; i < todosOsProdutos.size(); i++) {
                                    Produto p = todosOsProdutos.get(i);
                                    System.out.println("Código do Produto: " + p.getCodigo());
                                    System.out.println("Descrição: " + p.getDescricao());
                                    System.out.println("Estoque: " + p.getEstoque() + " unidade(s)");
                                    System.out.println("Validade: F " + ld + " | V " + p.getValidade());
                                    System.out.println("Fornecedor: " + p.getFornecedor().getNome());
                                    System.out.printf("Preço Unitário: R$ %.2f", p.getPreco());
                                    System.out.println("");
                                    System.out.printf("Desconto: R$ %.2f", p.descontoMax());
                                    System.out.println("");
                                    System.out.println("");
                                }

                                break;
                            case "6":

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                         ===== ADICIONAR ITENS AO ESTOQUE =====                                 ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                Produto produtoProcuradoaAddEstoque;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String nomeProduto;
                                    System.out.print("Nome Produto: ");
                                    nomeProduto = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    produtoProcuradoaAddEstoque = repositorioProdutos.procurarPorNome(nomeProduto);

                                } while (produtoProcuradoaAddEstoque == null);

                                System.out.println("");
                                System.out.println("Estoque: " + produtoProcuradoaAddEstoque.getEstoque() + " unidade(s)");
                                System.out.println("");
                                System.out.print("Deseja adicionar itens ao estoque - (S/N)? ");
                                char respAddEstoque = teclado.next().charAt(0);

                                if (respAddEstoque == 's') {

                                    String qtdAddEstoqueS;
                                    int qtdAddEstoque = 0;
                                    boolean tipoInt;
                                    System.out.println("");
                                    do {
                                        System.out.print("Informe a quantidade que deseja adicionar: ");
                                        qtdAddEstoqueS = teclado.next();

                                        try {
                                            qtdAddEstoque = Integer.parseInt(qtdAddEstoqueS);
                                            tipoInt = true;

                                        } catch (Exception e) {
                                            System.out.println("");
                                            System.out.println("O tipo é inválido, informe um tipo válido!");
                                            System.out.println("");
                                            tipoInt = false;
                                        }
                                    } while (!tipoInt);

                                    produtoProcuradoaAddEstoque.adicionaEstoque(qtdAddEstoque);

                                    System.out.println("");
                                    System.out.println("Estoque: " + produtoProcuradoaAddEstoque.getEstoque() + " unidade(s)");
                                    System.out.println("");
                                    System.out.println("Quantidade adicionada com sucesso!");
                                } else {
                                    System.out.println("");
                                    System.out.println("Nenhuma quantidade foi adicionada ao estoque!");
                                }

                                break;
                        }
                    }
                    break;
                //CLIENTES
                case "3":

                    String opcaoCliente = "";
                    while (!opcaoCliente.equals("0")) {
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("                    [1-CLIENTE POR CPF]");
                        System.out.print(" [2-CLIENTE POR NOME]");
                        System.out.print(" [3-TODOS OS CLIENTES]");
                        System.out.println(" [0-MENU PRINCIPAL]");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("                                               Digite uma das opções: ");
                        opcaoCliente = teclado.next();

                        switch (opcaoCliente) {
                            case "1":
                                Cliente clienteProcuradoPorCpf;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String cpf;
                                    System.out.print("CPF do cliente: ");
                                    cpf = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    clienteProcuradoPorCpf = repositorioClientes.procurarPorCpf(cpf);

                                } while (clienteProcuradoPorCpf == null);

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                             ===== CLIENTE POR CPF =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                System.out.println("CPF do cliente: " + clienteProcuradoPorCpf.getCpf());
                                System.out.println("Nome: " + clienteProcuradoPorCpf.getNome());
                                System.out.println("");
                                break;
                            case "2":
                                Cliente clienteProcuradoPorNome;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String nome;
                                    System.out.print("Nome do cliente: ");
                                    nome = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    clienteProcuradoPorNome = repositorioClientes.procurarPorNome(nome);

                                } while (clienteProcuradoPorNome == null);

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                             ===== CLIENTE POR NOME =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                System.out.println("CPF do cliente: " + clienteProcuradoPorNome.getCpf());
                                System.out.println("Nome: " + clienteProcuradoPorNome.getNome());
                                System.out.println("");
                                break;
                            case "3":
                                ArrayList<Cliente> todosOsClientes = repositorioClientes.getAll();

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                            ===== TODOS OS CLIENTES =====                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");
                                System.out.println("Quantidade total de clientes cadastrados: " + repositorioClientes.qtdDeClientesArmazenados());
                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                                for (int i = 0; i < todosOsClientes.size(); i++) {
                                    Cliente c = todosOsClientes.get(i);

                                    System.out.println("CPF: " + c.getCpf());
                                    System.out.println("Nome do cliente: " + c.getNome());
                                    System.out.println("");
                                }
                                break;
                        }
                    }

                    break;
                //FORNECEDORES
                case "4":
                    String opcaoFornecedor = "";
                    while (!opcaoFornecedor.equals("0")) {
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("            [1-FORNECEDOR POR CÓDIGO]");
                        System.out.print(" [2-FORNECEDOR POR NOME]");
                        System.out.print(" [3-TODOS OS FORNECEDORES]");
                        System.out.println(" [0-MENU PRINCIPAL]");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("                                               Digite uma das opções: ");
                        opcaoFornecedor = teclado.next();

                        switch (opcaoFornecedor) {
                            case "1":

                                Fornecedor fornecedorProcuradoPorCodigo;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String codFornecedor;
                                    System.out.print("Código do Fornecedor: ");
                                    codFornecedor = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    fornecedorProcuradoPorCodigo = repositorioFornecedor.procurarFornecedorPorCodigo(codFornecedor);

                                } while (fornecedorProcuradoPorCodigo == null);

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                            ===== FORNECEDOR POR CÓDIGO =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                System.out.println("Código do Fornecedor: " + fornecedorProcuradoPorCodigo.getCodigo());
                                System.out.println("Nome: " + fornecedorProcuradoPorCodigo.getNome());
                                System.out.println("");

                                break;
                            case "2":

                                Fornecedor fornecedorProcuradoPorNome;
                                do {
                                    //PRODUTO A SER COMPRADO INFORMADO
                                    String nomeFornecedor;
                                    System.out.print("Nome do Fornecedor: ");
                                    nomeFornecedor = teclado.next();

                                    //VERIFICA SE EXISTE O PRODUTO NO REPOSITÓRIO
                                    fornecedorProcuradoPorNome = repositorioFornecedor.procurarFornecedorPorNome(nomeFornecedor);

                                } while (fornecedorProcuradoPorNome == null);

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                           ===== FORNECEDOR POR NOME =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                System.out.println("Nome do Fornecedor: " + fornecedorProcuradoPorNome.getCodigo());
                                System.out.println("Nome: " + fornecedorProcuradoPorNome.getNome());
                                System.out.println("");

                                break;
                            case "3":
                                ArrayList<Fornecedor> todosOsFornecedores = repositorioFornecedor.getAll();

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                           ===== TODOS OS FORNECEDORES =====                                 ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");
                                System.out.println("Quantidade total de fornecedores cadastrados: " + repositorioFornecedor.qtdDeFornecedoresArmazenadas());
                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                                for (int i = 0; i < todosOsFornecedores.size(); i++) {
                                    Fornecedor f = todosOsFornecedores.get(i);

                                    System.out.println("Código do Fornecedor: " + f.getCodigo());
                                    System.out.println("Nome do fornecedor: " + f.getNome());
                                    System.out.println("");
                                }
                                break;
                        }
                    }
                    break;
                //VENDAS
                case "5":

                    String opcaoVenda = "";
                    while (!opcaoVenda.equals("0")) {
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("                [1-VENDA POR CÓDIGO]");
                        System.out.print(" [2-VENDA PELO NOME CLIENTE]");
                        System.out.print(" [3-TODAS AS VENDAS]");
                        System.out.println(" [0-MENU PRINCIPAL]");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("                                               Digite uma das opções: ");
                        opcaoVenda = teclado.next();

                        switch (opcaoVenda) {
                            case "1":

                                ArrayList<Venda> todasAsVendasPCodigo = repositorioVendas.getAll();
                                Venda vendaProcuradoPorCodigo;

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                            ===== VENDA POR CÓDIGO =====                                                     ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");


                                int codVendaInt = 0;
                                boolean tipoCodVendaInt;

                                if (todasAsVendasPCodigo.isEmpty()) {
                                    System.out.println("");
                                    System.out.println("                                            Não existe vendas realizadas!");
                                    System.out.println("                                            Quantidade total de vendas: " + repositorioVendas.qtdTotalDeVendasRealizadas());
                                    System.out.println("");
                                }else {
                                    do {
                                        //PRODUTO A SER COMPRADO INFORMADO
                                        String codVendaS;
                                        System.out.print("Código da Venda: ");
                                        codVendaS = teclado.next();

                                        try {
                                            codVendaInt = Integer.parseInt(codVendaS);
                                            tipoCodVendaInt = true;

                                        } catch (Exception e) {
                                            System.out.println("");
                                            System.out.println("O tipo é inválido, informe um tipo válido!");
                                            System.out.println("");
                                            tipoCodVendaInt = false;
                                        }
                                    } while (!tipoCodVendaInt);

                                    //VERIFICA SE EXISTE A VENDA NO REPOSITÓRIO DO O CÓDIGO INFORMADO
                                    vendaProcuradoPorCodigo = repositorioVendas.procurarVendaPorCodigo(codVendaInt);

                                    if(!(vendaProcuradoPorCodigo == null))   {
                                        System.out.println("");
                                        System.out.println("Nome do Cliente: " + vendaProcuradoPorCodigo.getCliente().getNome());
                                        System.out.println("");
                                        System.out.println("Produto(s): ");
                                        for (int x = 0; x < vendaProcuradoPorCodigo.produtos.size(); x++) {
                                            System.out.println(" * " + vendaProcuradoPorCodigo.produtos.get(x).getDescricao());
                                        }
                                        System.out.println("");
                                        System.out.println("Quantidade total de itens: " + vendaProcuradoPorCodigo.getQtdItens());
                                        System.out.printf("VALOR TOTAL: R$ %.2f", +vendaProcuradoPorCodigo.getPrecoTotal());
                                        System.out.println("");
                                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                        System.out.println("");
                                    }
                                }

                                break;
                            case "2":
                                System.out.println("Falta implementar");
                                break;
                            case "3":
                                ArrayList<Venda> todasAsVendas = repositorioVendas.getAll();

                                System.out.println("");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("                                          ===== LISTAR TODAS AS VENDAS =====                                      ");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("");

                                if (todasAsVendas.isEmpty()) {
                                    System.out.println("                                            Não existe vendas realizadas!");
                                    System.out.println("                                            Quantidade total de vendas: " + repositorioVendas.qtdTotalDeVendasRealizadas());
                                    System.out.println("");
                                } else {
                                    System.out.println("Quantidade total de vendas: " + repositorioVendas.qtdTotalDeVendasRealizadas());
                                    System.out.println("");
                                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                                    for (int i = 0; i < todasAsVendas.size(); i++) {
                                        v = todasAsVendas.get(i);

                                        System.out.println("Código da venda: " + v.getCodigoDaVenda());
                                        System.out.println("");
                                        System.out.println("Nome do Cliente: " + v.getCliente().getNome());
                                        System.out.println("Produto(s): ");
                                        for (int x = 0; x < v.produtos.size(); x++) {
                                            System.out.println(" * " + v.produtos.get(x).getDescricao());
                                        }
                                        System.out.println("");
                                        System.out.println("Quantidade total de itens: " + v.getQtdItens());
                                        System.out.printf("VALOR TOTAL: R$ %.2f", +v.getPrecoTotal());
                                        System.out.println("");
                                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                                    }
                                }
                                break;
                        }
                    }

                    break;
            }
        }

        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                           ===== Aplicação finalizada! =====                      ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
    }
}
