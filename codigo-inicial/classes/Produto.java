package classes;

public class Produto {
  private int codigo;
  private String nome;
  private double valor;
  private int quantidadeEstoque;

    public Produto(int codigo, String nome, double valor, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void adicionarEstoque(int quantidade) {
        quantidadeEstoque += quantidade;
    }

    public boolean removerEstoque(int quantidade) {
        if (quantidade <= quantidadeEstoque) {
            quantidadeEstoque -= quantidade;
            return true;
        } else {
            return false;
        }
    }

    public void setQuantidadeEstoque(int i) {
    }
}
