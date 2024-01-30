package UI;

import javax.swing.*;
import java.awt.*;

public class Infos {
	private JFrame frame; // Instância da classe JFrame para criação da janela
	private JTextPane txtpnMquinaDeTuring; // Instância da classe JTextPane para exibição de texto

	// Construtor da classe Infos
	public Infos() {
		initialize(); // Chamada do método de inicialização
	}

	// Método de inicialização da janela de informações
	private void initialize() {
		frame = new JFrame(); // Inicialização da janela
		frame.setBounds(100, 100, 800, 320); // Definição das dimensões da janela
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Definição do comportamento ao fechar a janela
		frame.getContentPane().setLayout(new BorderLayout()); // Definição do layout da janela como BorderLayout

		// Criação de um JScrollPane e configuração para rolagem em ambas as direções
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Adição do JScrollPane ao centro da janela

		txtpnMquinaDeTuring = new JTextPane(); // Inicialização do JTextPane
		txtpnMquinaDeTuring.setText("Máquina de Turing (MT)\n\n"
				+ "A Máquina de Turing (MT) é um dispositivo teórico proposto por Allan Turing em 1936, antes mesmo da existência de implementações tecnológicas. Ela é um modelo abstrato utilizado como ferramenta para estudar a capacidade dos processos algorítmicos, diferenciando-se do Teste de Turing, que se concentra na inteligência artificial e na capacidade de um sistema passar por humano.\n\n"
				+ "A MT é um mecanismo reconhecedor com um poder computacional significativo, sendo capaz de realizar processamento de funções e reconhecimento de linguagens. Ela pode ser classificada em dois tipos principais: reconhecedora, que responde sim ou não para uma palavra indicando se pertence ou não à linguagem, e transdutora, que gera uma palavra na própria fita como saída da MT.\n\n"
				+ "Para reconhecer ou traduzir uma palavra, a MT processa-a, parando em um estado final, com o cabeçote da fita na mesma posição que começou. Ela é composta por uma fita utilizada para leitura e escrita, um cabeçote da fita que mostra a posição atual e se move para direita e esquerda, e uma função de transição que movimenta a máquina a partir de um símbolo, indo para um estado e movendo o cabeçote para direita ou esquerda.\n\n"
				+ "A importância da MT para a Ciência da Computação é imensa, pois representa a fronteira teórica da capacidade computacional para as máquinas modernas reais. Se um problema não puder ser resolvido por uma MT, não poderá ser resolvido por qualquer sistema algorítmico. Os computadores modernos são, de certa forma, uma realização prática dos conceitos da MT, onde o processador corresponde ao cabeçote da fita, a memória da máquina corresponde à fita, e os padrões de bits correspondem ao alfabeto da fita.");
		txtpnMquinaDeTuring.setEditable(false); // Definição do JTextPane como somente leitura

		scrollPane.setViewportView(txtpnMquinaDeTuring); // Adição do JTextPane ao JScrollPane
	}

	// Método para exibir a janela
	public void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true); // Definir a janela como visível
				} catch (Exception e) {
					e.printStackTrace(); // Exibir o rastreamento da pilha de exceções, se ocorrer um erro
				}
			}
		});
	}

	// Método principal para execução da aplicação
	public static void main(String[] args) {
		Infos infoWindow = new Infos(); // Instanciar um objeto da classe Infos
		infoWindow.showWindow(); // Chamar o método para exibir a janela
	}
}
