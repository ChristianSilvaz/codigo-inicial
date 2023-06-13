package aplicativos;

import java.util.InputMismatchException;
import java.util.Scanner;
import classes.CadastroProdutosVendas;

public class App {
    public static void main(String[] args) {
        CadastroProdutosVendas cadastro = new CadastroProdutosVendas();
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastro.incluirProduto(scanner);
                        break;
                    case 2:
                        cadastro.consultarProduto(scanner);
                        break;
                    case 3:
                        cadastro.listarProdutos();
                        break;
                    case 4:
                        cadastro.vendasPorPeriodoDetalhado(scanner);
                        break;
                    case 5:
                        cadastro.realizarVenda(scanner);
                        break;
                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                scanner.nextLine(); 
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n----- MENU -----");
        System.out.println("1 - Incluir produto");
        System.out.println("2 - Consultar produto");
        System.out.println("3 - Listagem de produtos");
        System.out.println("4 - Vendas por período - detalhado");
        System.out.println("5 - Realizar venda");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}