public class Produto {
    //ATRIBUTOS
    private String nome;
    private double valor;

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return this.nome;
    }

    public double getValor() {
        return valor;
    }
}