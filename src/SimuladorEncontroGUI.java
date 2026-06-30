import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    static double posCarro1 = 0 ;
    static double poscarro2 = 0 ;
    static double velocidade = 0;
    static double aceleracao = 0;
    static double tempo = 0;
    static void main(String[] args) {
        // VARIAVEIS DA FÓRMULA - as variáveis tem que ser aqui, se for dentro do static o java já le o valor que está lá, no caso o 0
        //BILBIOTECAs - instancias
        JFrame tela = new JFrame("MRU xs MRUV"); // criou o "frame"
        JPanel painel = new JPanel(); // biblioteca de painéis
        // TELA
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.add(painel); // adicionei o painel ao frame
        tela.setSize(800,600);// define largura e altura da tela
        tela.setLocationRelativeTo(null); // ISSO CENYTRALIZA A TELA
        // CARRO 01
        JLabel textoCarro1 = new JLabel("VELOCIDADE DO CARRO 2 EM (M/S): ");// é como o input, mas em interface
        JTextField insercaoCarro1 = new JTextField("0.0", 5); // aqui vai ser onde o usuário vai inserir o valor e depois aiciona o botão, já começa com 0 mas o usuário pode apagar
        //adicionando caixas - componentes da tela
        painel.add(textoCarro1); // aqui voce adicionou o componente visual, uma caixa de texto com aquele produto(o texto de cima)
        painel.add(insercaoCarro1);
        //CARRO02
        JLabel textoCarro2  = new JLabel("VELOCIDADE DO CARRO 2 EM (M/S): ");
        JTextField insercaoCarro2 = new JTextField("0.0", 5);
        //adicionando caixas - componentes da tela
        painel.add(textoCarro2);
        painel.add(insercaoCarro2);
        //SIMULAÇÃO
        JButton butao = new JButton("START");
        //COMPONENTES
        painel.add(butao);
        // alterando as posições com timer
        Timer kronos = new Timer(30, new ActionListener(){ // a cada 30 ms ele altera a imagem, é como um despertador
            @Override
            public void actionPerformed(ActionEvent e){
                // a cada 30 ms, ele executa esse bloco de código
                tempo+=0.03;
                posCarro1 = velocidade * tempo;// posição é zero e os carros estão parados nas duas equações, eles começam de um mesmo ponto
                // S = 0 + v* t
                poscarro2 = (aceleracao*(tempo * tempo))/2;
                // S = 0 + a
                System.out.println("Tempo: "+ tempo + " | Posicao: "+ posCarro1);
            }
        });
        butao.addActionListener(new ActionListener() {// indica que uma ação deve ser executada
            @Override
            public void actionPerformed(ActionEvent e) {
                //TRANFORMANDO O TEXTO EM NÚMEOR
                //carro 1
                double velocidade;
                velocidade = Double.parseDouble(insercaoCarro1.getText()); //peguei o texto do carro 1 e transformo em double - o texto do textField
                // acrro 2
                double aceleracao;
                aceleracao = Double.parseDouble(insercaoCarro2.getText());
                // clicou zerou, de novo no caso
                double posCarro1 = 0;
                double poscarro2 = 0;
                double tempo = 0;
                kronos.start();
            }
        });
        tela.setVisible(true); // mostra a janela no painle
    }
}

