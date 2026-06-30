public class Carrinho {
    //ATRIBUTOS
    private Produto[] produtos = new Produto[2];

    public Carrinho() {
        this.produtos = produtos;
    }

    public void inserir(int posicao, Produto p){
        for (int i = 0; i < this.produtos.length; i++) {
            if (posicao==i && this.produtos[i]==null){
                this.produtos[i] = p;
            }
        }
    }

    public String mostrar(int posicao){
        return this.produtos[posicao].getNome();
    }

    public Produto[] getProdutos() {
        return produtos;
    }
}
