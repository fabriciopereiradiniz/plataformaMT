package UI;

import javax.swing.*;
import java.awt.*;

// Classe para a janela da aplicação Reconhecedora
public class Reconhecedora extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane txtpnMquinaDeTuring; // Componente para exibição de texto

    // Construtor da classe Reconhecedora
    public Reconhecedora() {
        initialize(); // Inicializa a interface gráfica da janela
    }

    // Método para inicializar a interface gráfica da janela
    private void initialize() {
        // Configurações da janela
        this.setBounds(100, 100, 800, 320); // Define a posição e o tamanho da janela
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento de fechamento da janela
        this.getContentPane().setLayout(new BorderLayout()); // Define o layout do conteúdo da janela

        // Configurações do componente de rolagem
        JScrollPane scrollPane = new JScrollPane(); // Cria um componente de rolagem
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Define a política de rolagem horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Define a política de rolagem vertical
        this.getContentPane().add(scrollPane, BorderLayout.CENTER); // Adiciona o componente de rolagem ao centro da janela

        // Configurações do componente de texto
        txtpnMquinaDeTuring = new JTextPane(); // Cria um componente de texto
        txtpnMquinaDeTuring.setEditable(false); // Define o componente como não editável
        scrollPane.setViewportView(txtpnMquinaDeTuring); // Define o componente de texto como visualizado no componente de rolagem

        // Define o texto inicial do componente de texto
        txtpnMquinaDeTuring.setText("Máquina de Turing Reconhecedora\n\n"
                + "A Máquina de Turing Reconhecedora é um tipo de Máquina de Turing que tem a capacidade de determinar se uma dada palavra pertence a uma linguagem ou não. Ela atua como um dispositivo de decisão, fornecendo uma resposta 'sim' ou 'não' para cada entrada.\n\n"
                + "Para realizar essa tarefa, a Máquina de Turing Reconhecedora opera de acordo com um conjunto de regras de transição que definem como a máquina se comporta em cada estado, dado um símbolo de entrada.\n\n"
                + "Essas regras de transição especificam ações como mover o cabeçote da fita para a esquerda ou para a direita, mudar para um novo estado e escrever um novo símbolo na fita. A máquina continua executando essas regras até alcançar um estado final, onde ela pára e emite sua decisão.\n\n"
                + "A importância da Máquina de Turing Reconhecedora reside em sua aplicação na teoria da computação e na formalização de linguagens formais. Ela fornece um modelo abstrato para entender a capacidade dos algoritmos em reconhecer padrões e determinar a pertinência de uma entrada a uma linguagem específica.");
    }

    // Método para exibir a janela
    public void showWindow() {
        this.setVisible(true); // Torna a janela visível
    }
}
