package UI;

import javax.swing.*;
import java.awt.*;

// Classe para a janela da Máquina de Turing Transdutora
public class Transdutora {
    private JFrame frame; // Janela principal
    private JTextPane txtpnMquinaDeTuring; // Componente de texto para exibir informações sobre a Máquina de Turing Transdutora

    // Construtor da classe Transdutora
    public Transdutora() {
        initialize(); // Inicializa a interface gráfica da janela
    }

    // Método para inicializar a interface gráfica da janela
    private void initialize() {
        frame = new JFrame(); // Cria uma nova janela
        frame.setBounds(100, 100, 800, 320); // Define a posição e o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento de fechamento da janela
        frame.getContentPane().setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout

        // Criando um JScrollPane e configurando-o para ser rolável em ambas as direções
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Configura a política de rolagem horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Configura a política de rolagem vertical
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à região central da janela

        txtpnMquinaDeTuring = new JTextPane(); // Cria um novo componente JTextPane para exibir informações sobre a Máquina de Turing Transdutora
        txtpnMquinaDeTuring.setText("Máquina de Turing Transdutora\n\n"
                + "A Máquina de Turing Transdutora é um tipo de Máquina de Turing que difere da Máquina de Turing tradicional por sua capacidade de produzir uma saída além de apenas aceitar ou rejeitar uma entrada.\n\n"
                + "Enquanto uma Máquina de Turing tradicional é capaz apenas de reconhecer uma linguagem e responder 'sim' ou 'não' para uma entrada, uma Máquina de Turing Transdutora é capaz de gerar uma saída durante o processo de computação.\n\n"
                + "Por exemplo, uma Máquina de Turing Transdutora pode receber uma entrada em uma fita e produzir uma saída na mesma fita, realizando uma tradução ou transformação durante o processo.\n\n"
                + "Assim como a Máquina de Turing tradicional, a Máquina de Turing Transdutora é composta por uma fita, um cabeçote de leitura/escrita e um conjunto finito de estados e regras de transição.\n\n"
                + "A importância da Máquina de Turing Transdutora reside na sua capacidade de modelar e resolver problemas que exigem a geração de uma saída baseada em uma entrada, indo além da capacidade das Máquinas de Turing tradicionais.\n\n"
                + "Essa extensão das capacidades das Máquinas de Turing tem aplicações em diversas áreas, incluindo linguagens formais, computabilidade e teoria da computação, fornecendo um modelo poderoso para entender a natureza da computação e resolver problemas algorítmicos complexos."); // Define o texto do JTextPane
        txtpnMquinaDeTuring.setEditable(false); // Define o JTextPane como não editável

        scrollPane.setViewportView(txtpnMquinaDeTuring); // Define o componente de texto como a exibição de rolagem do JScrollPane
    }

    // Método para exibir a janela
    public void showWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame.setVisible(true); // Torna a janela visível
                } catch (Exception e) {
                    e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
                }
            }
        });
    }

    // Método main para iniciar a aplicação
    public static void main(String[] args) {
        Transdutora transdutoraWindow = new Transdutora(); // Cria uma nova instância da classe Transdutora
        transdutoraWindow.showWindow(); // Exibe a janela da Máquina de Turing Transdutora
    }
}
