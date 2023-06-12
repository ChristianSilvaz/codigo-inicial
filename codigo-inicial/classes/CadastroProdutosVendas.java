package classes;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class CadastroProdutosVendas {
     private List<Produto> produtos;
     private List<Vendas> vendas;
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public CadastroProdutosVendas() {
        produtos = new ArrayList<>();
        vendas = new ArrayList<>();
    }

    public void incluirProduto(Scanner scanner) {
        System.out.println("\n--- Incluir Produto ---");
        System.out.print("Informe o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Informe o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o valor do produto: ");
        double valor = scanner.nextDouble();
        System.out.print("Informe a quantidade em estoque: ");
        int quantidadeEstoque = scanner.nextInt();

        Produto produto = new Produto(codigo, nome, valor, quantidadeEstoque);
        produtos.add(produto);
        System.out.println("Produto incluído com sucesso!");
    }
  public void consultarProduto(Scanner scanner) {
        System.out.println("\n--- Consultar Produto ---");
        System.out.print("Informe o código do produto: ");
        int codigo = scanner.nextInt();

        boolean produtoEncontrado = false;
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                System.out.println("\n--- Detalhes do Produto ---");
                System.out.println("Código: " + produto.getCodigo());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Valor: " + produto.getValor());
                System.out.println("Quantidade em Estoque: " + produto.getQuantidadeEstoque());
                 produtoEncontrado = true;
            break;
        }
    }

    if (!produtoEncontrado) {
        System.out.println("Produto não encontrado!");
    }
}
public void listarProdutos() {
    System.out.println("\n--- Listagem de Produtos ---");
    System.out.println("Código\tNome\t\tValor\tQuantidade");

    for (Produto produto : produtos) {
        System.out.println(
            produto.getCodigo() + "\t" +
            produto.getNome() + "  \t" +
            produto.getValor() + "\t" +
            produto.getQuantidadeEstoque()
        );
    }

    if (produtos.isEmpty()) {
        System.out.println("Não há produtos cadastrados.");
    }

    double valorTotal = calcularValorTotalProdutos();
    double valorMedio = calcularValorMedioProdutos(valorTotal);
    double valorMaximo = calcularValorMaximoProdutos();
    double valorMinimo = calcularValorMinimoProdutos();

    System.out.println("\n--- Valor Médio, Máximo e Mínimo dos Produtos ---");
    System.out.println("Valor Médio: " + valorMedio);
    System.out.println("Valor Máximo: " + valorMaximo);
    System.out.println("Valor Mínimo: " + valorMinimo);
}

private double calcularValorTotalProdutos() {
    double valorTotal = 0.0;
    for (Produto produto : produtos) {
        valorTotal += produto.getValor() * produto.getQuantidadeEstoque();
    }
    return valorTotal;
}

private double calcularValorMedioProdutos(double valorTotalProdutos) {
    int quantidadeProdutos = produtos.size();
    if (quantidadeProdutos == 0) {
        return 0.0;
    } else {
        return valorTotalProdutos / quantidadeProdutos;
    }
}

private double calcularValorMaximoProdutos() {
    double valorMaximo = 0.0;
    for (Produto produto : produtos) {
        if (produto.getValor() > valorMaximo) {
            valorMaximo = produto.getValor();
        }
    }
    return valorMaximo;
}

private double calcularValorMinimoProdutos() {
    double valorMinimo = Double.MAX_VALUE;
    for (Produto produto : produtos) {
        if (produto.getValor() < valorMinimo) {
            valorMinimo = produto.getValor();
        }
    }
    return valorMinimo;
}
public void realizarVenda(Scanner scanner) {
   System.out.println("\n--- Realizar Venda ---");
    System.out.print("Informe o código do produto: ");
    int codigo = scanner.nextInt();
    scanner.nextLine();

    Produto produto = buscarProdutoPorCodigo(codigo);

    if (produto != null) {
        System.out.print("Informe a quantidade a ser vendida: ");
        int quantidade = scanner.nextInt();

        if (produto.removerEstoque(quantidade)) {
            System.out.print("Informe a data da venda (dd/MM/yyyy): ");
            String dataVendaStr = scanner.next();

            LocalDate dataVenda = LocalDate.parse(dataVendaStr, dtf);
            Vendas venda = new Vendas(dataVenda, produto, quantidade);
            vendas.add(venda);
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Quantidade em estoque insuficiente!");
        }
    } 

else {
System.out.print("Produto não encontrado!"); 
    }
}
private Produto buscarProdutoPorCodigo(int codigo) {
    for (Produto produto : produtos) {
        if (produto.getCodigo() == codigo) {
            return produto;
        }
    }
    return null;
}


public void vendasPorPeriodoDetalhado(Scanner scanner) {
    
    System.out.println("\n--- Vendas por Período - Detalhado ---");
    System.out.print("Informe a data de início (dd/mm/aaaa): ");
    String dataInicio = scanner.next();
    System.out.print("Informe a data de fim (dd/mm/aaaa): ");
    String dataFim = scanner.next();

    System.out.println("\n--- Relatório de Vendas ---");
    System.out.println("Período: " + dataInicio + " a " + dataFim);
    System.out.println("Data\t\tProduto\t\tQuantidade\tValor Unitário\tValor Total");

    double valorTotalVendas = 0.0;
    int quantidadeVendas = 0;
         


    for (Vendas venda : vendas) {
        LocalDate dataVenda = venda.getDataVenda();
        String dataFormatada =  dataVenda.format(dtf);
        String produtoNome = venda.getProdutoVendido().getNome();
        int quantidade = venda.getQuantidadeVendida();
        double valorUnitario = venda.getProdutoVendido().getValor();
        double valorTotal = venda.getValorTotal();
        valorTotal = venda.getProdutoVendido().getValor()*venda.getQuantidadeVendida();
        System.out.println(dataFormatada + "\t  " + produtoNome + "\t  " + quantidade + "\t\t" + valorUnitario + "\t\t" + valorTotal);

        valorTotalVendas += valorTotal;
        quantidadeVendas += quantidade;
        
    
    }

    System.out.println("\nValor médio das vendas: " + (valorTotalVendas / quantidadeVendas));
 }
}