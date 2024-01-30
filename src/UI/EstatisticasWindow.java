package UI;

import javax.swing.*;

import DB.Conexao;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import entities.Exercicio;

public class EstatisticasWindow extends JFrame {

    private static final long serialVersionUID = 1L; // Número de série para controle de versão da classe
    private Conexao conexao; // Instância da classe Conexao para manipulação de conexão com o banco de dados

    // Construtor da classe EstatisticasWindow
    public EstatisticasWindow() {
        try {
            conexao = new Conexao(); // Inicialização da conexão com o banco de dados
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
        }
        initialize(); // Chamada do método de inicialização
    }

    // Método de inicialização da janela de estatísticas
    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Definição do comportamento ao fechar a janela
        setBounds(100, 100, 800, 600); // Definição das dimensões da janela
        setResizable(false); // Desabilitação do redimensionamento da janela

        // Criação do painel para exibir a lista de exercícios mais feitos
        JPanel listPanel = new JPanel(new BorderLayout());
        getContentPane().add(listPanel, BorderLayout.CENTER); // Adição do painel ao centro da janela

        // Criação do modelo de lista
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> exerciciosList = new JList<>(listModel); // Inicialização da lista de exercícios

        // Adição da lista a um scroll pane
        JScrollPane scrollPane = new JScrollPane(exerciciosList);
        listPanel.add(scrollPane, BorderLayout.CENTER); // Adição do scroll pane ao painel de lista

        // Tentativa de obtenção da lista de exercícios mais feitos detalhados nos últimos 30 dias
        List<Exercicio> exerciciosMaisFeitos = null;
        try {
            exerciciosMaisFeitos = conexao.obterExerciciosMaisFeitosDetalhadosUltimos30Dias();
        } catch (Exception e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
        }
        // Adição dos exercícios mais feitos ao modelo de lista
        for (Exercicio exercicio : exerciciosMaisFeitos) {
            listModel.addElement(exercicio.getId() + " - " + exercicio.getTitulo() + " - Feito " + exercicio.getQuantidadeFeita() + " vezes");
        }
    }

    // Método main para inicialização da aplicação
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EstatisticasWindow window = new EstatisticasWindow();
                window.setVisible(true); // Torna a janela visível
            } catch (Exception e) {
                e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
            }
        });
    }
}