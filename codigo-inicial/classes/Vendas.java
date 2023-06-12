package classes;

import java.time.LocalDate;

public class Vendas {
   private LocalDate dataVenda;
    private Produto produtoVendido;
    private int quantidadeVendida;
   
 public Vendas(LocalDate dataVenda2, Produto produtoVendido, int quantidadeVendida) {
        this.dataVenda = dataVenda2;
        this.produtoVendido = produtoVendido;
        this.quantidadeVendida = quantidadeVendida;
        }


    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public double getValorTotal() {
        return quantidadeVendida * produtoVendido.getValor();
    }
}
 