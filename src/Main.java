public class Main {
    static void main(String[] args) {
        Carrinho c1 = new Carrinho();
        Produto p1 = new Produto("Figurinha", 7.00);

        c1.inserir(0, p1);
        System.out.println(p1.getNome());
        System.out.println(c1.mostrar(0));

    }
}
