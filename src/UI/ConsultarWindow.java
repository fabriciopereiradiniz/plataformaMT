package UI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import entities.Exercicio;
import DB.Conexao;

public class ConsultarWindow extends JFrame {

    private static final long serialVersionUID = 1L; // Número de série para controle de versão da classe
    private Conexao conexao; // Instância da classe Conexao para manipulação de conexão com o banco de dados

    // Construtor da classe ConsultarWindow
    public ConsultarWindow() {
        try {
            conexao = new Conexao(); // Inicialização da conexão com o banco de dados
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
        }
        initialize(); // Chamada do método de inicialização
    }

    // Método de inicialização da janela de consulta
    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Definição do comportamento ao fechar a janela
        setBounds(100, 100, 800, 600); // Definição das dimensões da janela
        setResizable(false); // Desabilitação do redimensionamento da janela

        // Criação do painel para exibir a lista de alunos
        JPanel listPanel = new JPanel(new BorderLayout());
        getContentPane().add(listPanel, BorderLayout.CENTER); // Adição do painel ao centro da janela

        // Criação do modelo de lista
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> alunosList = new JList<>(listModel); // Inicialização da lista de alunos

        // Adição da lista a um scroll pane
        JScrollPane scrollPane = new JScrollPane(alunosList);
        listPanel.add(scrollPane, BorderLayout.CENTER); // Adição do scroll pane ao painel de lista

        try {
            // Obtenção da lista de todos os alunos
            List<String> todosAlunos = conexao.obterTodosAlunos();
            for (String aluno : todosAlunos) {
                listModel.addElement(aluno); // Adição dos alunos ao modelo de lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
            JOptionPane.showMessageDialog(this, "Erro ao carregar alunos."); // Exibição de mensagem de erro
        }

        // Adição de um botão "Mostrar Informações"
        JButton btnMostrarInformacoes = new JButton("Mostrar Informações");
        btnMostrarInformacoes.addActionListener(e -> {
            // Obtenção do aluno selecionado na lista
            String alunoSelecionado = alunosList.getSelectedValue();
            if (alunoSelecionado != null) {
                try {
                    // Obtenção dos exercícios já feitos pelo aluno selecionado
                    List<Exercicio> exerciciosFeitos = conexao.obterExerciciosFeitosPorAluno(alunoSelecionado);
                    exibirInformacoesExerciciosFeitos(alunoSelecionado, exerciciosFeitos); // Exibição das informações dos exercícios feitos
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
                    JOptionPane.showMessageDialog(this, "Erro ao obter exercícios feitos pelo aluno."); // Exibição de mensagem de erro
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno."); // Exibição de mensagem de erro
            }
        });

        // Criação do painel para o botão
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnMostrarInformacoes); // Adição do botão ao painel de botões
        getContentPane().add(buttonPanel, BorderLayout.SOUTH); // Adição do painel de botões à parte inferior da janela
    }

    // Método para exibir informações dos exercícios feitos pelo aluno
    private void exibirInformacoesExerciciosFeitos(String aluno, List<Exercicio> exercicios) {
        StringBuilder message = new StringBuilder("Exercícios feitos pelo aluno: " + aluno + "\n\n");
        for (Exercicio exercicio : exercicios) {
            // Construção da mensagem com os detalhes dos exercícios feitos pelo aluno
            message.append("ID: ").append(exercicio.getId()).append(", Pergunta: ").append(exercicio.getPergunta()).append("\n");
        }
        JOptionPane.showMessageDialog(this, message.toString()); // Exibição da mensagem com as informações dos exercícios
    }

    // Método main para inicialização da aplicação
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConsultarWindow window = new ConsultarWindow();
                window.setVisible(true); // Torna a janela visível
            } catch (Exception e) {
                e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
            }
        });
    }
}