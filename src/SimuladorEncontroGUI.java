import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

    public class SimuladorEncontroGUI extends JFrame {

        private JTextField txtS0A;
        private JTextField txtVA;
        private JTextField txtS0B;
        private JTextField txtV0B;
        private JTextField txtAB;
        private JTextField txtTempo;

        private JTextArea txtResultado;

        public SimuladorEncontroGUI() {
            setTitle("Simulador de Encontro - MRU e MRUV");
            setSize(700, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JPanel painelEntrada = new JPanel(new GridLayout(7, 2, 10, 10));
            painelEntrada.setBorder(BorderFactory.createTitledBorder("Dados de entrada"));

            txtS0A = new JTextField();
            txtVA = new JTextField();
            txtS0B = new JTextField();
            txtV0B = new JTextField();
            txtAB = new JTextField();
            txtTempo = new JTextField();

            painelEntrada.add(new JLabel("Corpo A - posição inicial s0:"));
            painelEntrada.add(txtS0A);

            painelEntrada.add(new JLabel("Corpo A - velocidade v:"));
            painelEntrada.add(txtVA);

            painelEntrada.add(new JLabel("Corpo B - posição inicial s0:"));
            painelEntrada.add(txtS0B);

            painelEntrada.add(new JLabel("Corpo B - velocidade inicial v0:"));
            painelEntrada.add(txtV0B);

            painelEntrada.add(new JLabel("Corpo B - aceleração a:"));
            painelEntrada.add(txtAB);

            painelEntrada.add(new JLabel("Tempo t para calcular posições:"));
            painelEntrada.add(txtTempo);

            JButton btnCalcularPosicao = new JButton("Calcular posições no tempo t");
            JButton btnCalcularEncontro = new JButton("Calcular encontro");

            painelEntrada.add(btnCalcularPosicao);
            painelEntrada.add(btnCalcularEncontro);

            txtResultado = new JTextArea();
            txtResultado.setEditable(false);
            txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));

            JScrollPane scroll = new JScrollPane(txtResultado);
            scroll.setBorder(BorderFactory.createTitledBorder("Resultado"));

            add(painelEntrada, BorderLayout.NORTH);
            add(scroll, BorderLayout.CENTER);

            btnCalcularPosicao.addActionListener(e -> calcularPosicoes());
            btnCalcularEncontro.addActionListener(e -> calcularEncontro());
        }

        private void calcularPosicoes() {
            try {
                double s0A = Double.parseDouble(txtS0A.getText());
                double vA = Double.parseDouble(txtVA.getText());
                double s0B = Double.parseDouble(txtS0B.getText());
                double v0B = Double.parseDouble(txtV0B.getText());
                double aB = Double.parseDouble(txtAB.getText());
                double t = Double.parseDouble(txtTempo.getText());

                MovimentoMRU corpoA = new MovimentoMRU(s0A, vA);
                MovimentoMRUV corpoB = new MovimentoMRUV(s0B, v0B, aB);

                double posA = corpoA.calcularPosicao(t);
                double posB = corpoB.calcularPosicao(t);

                DecimalFormat df = new DecimalFormat("0.00");

                txtResultado.setText(
                        "Posição do Corpo A (MRU) em t = " + df.format(t) + ":\n" +
                                "sA = " + df.format(posA) + "\n\n" +
                                "Posição do Corpo B (MRUV) em t = " + df.format(t) + ":\n" +
                                "sB = " + df.format(posB) + "\n\n" +
                                "Diferença entre as posições:\n" +
                                "Δs = " + df.format(Math.abs(posA - posB)) + "\n"
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Preencha todos os campos com números válidos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private void calcularEncontro() {
            try {
                double s0A = Double.parseDouble(txtS0A.getText());
                double vA = Double.parseDouble(txtVA.getText());
                double s0B = Double.parseDouble(txtS0B.getText());
                double v0B = Double.parseDouble(txtV0B.getText());
                double aB = Double.parseDouble(txtAB.getText());

                double A = aB / 2.0;
                double B = v0B - vA;
                double C = s0B - s0A;

                String resultado;

                if (Math.abs(A) < 1e-9) {
                    if (Math.abs(B) < 1e-9) {
                        if (Math.abs(C) < 1e-9) {
                            resultado = "Os dois corpos estão sempre na mesma posição.";
                        } else {
                            resultado = "Não existe encontro.";
                        }
                    } else {
                        double t = -C / B;
                        if (t >= 0) {
                            resultado = "Encontro em t = " + new DecimalFormat("0.00").format(t);
                        } else {
                            resultado = "O encontro aconteceria no passado.";
                        }
                    }
                } else {
                    double delta = B * B - 4 * A * C;

                    if (delta < 0) {
                        resultado = "Não existe encontro real.";
                    } else {
                        double t1 = (-B + Math.sqrt(delta)) / (2 * A);
                        double t2 = (-B - Math.sqrt(delta)) / (2 * A);

                        StringBuilder sb = new StringBuilder();
                        sb.append("Possíveis tempos de encontro:\n");

                        boolean achou = false;

                        if (t1 >= 0) {
                            sb.append("t1 = ").append(new DecimalFormat("0.00").format(t1)).append("\n");
                            achou = true;
                        }
                        if (t2 >= 0 && Math.abs(t2 - t1) > 1e-9) {
                            sb.append("t2 = ").append(new DecimalFormat("0.00").format(t2)).append("\n");
                            achou = true;
                        }

                        if (!achou) {
                            sb.append("Os encontros ocorreriam no passado.");
                        }

                        resultado = sb.toString();
                    }
                }

                txtResultado.setText(resultado);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Preencha todos os campos com números válidos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new SimuladorEncontroGUI().setVisible(true);
            });
        }
    }

