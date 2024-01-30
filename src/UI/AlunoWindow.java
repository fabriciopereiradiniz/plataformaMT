package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import DB.Conexao;
import entities.Exercicio;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AlunoWindow extends JFrame {

    private static final long serialVersionUID = 1L; // Número de série para controle de versão da classe
    private Conexao conexao; // Instância da classe Conexao para manipulação de conexão com o banco de dados
    private JList<String> exerciciosList; // Lista de exercícios
    private DefaultListModel<String> listModel; // Modelo de lista padrão para os exercícios

    // Construtor da classe AlunoWindow
    public AlunoWindow(Conexao conexao) throws SQLException {
        this.conexao = conexao; // Inicialização da conexão
        this.listModel = new DefaultListModel<>(); // Inicialização do modelo de lista padrão
        initialize(); // Chamada do método de inicialização
    }

    // Método de inicialização da janela do aluno
    private void initialize() throws SQLException {
        this.setBounds(100, 100, 800, 500); // Definição das dimensões da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Definição do comportamento ao fechar a janela
        this.setResizable(false); // Desabilitação da redimensionamento da janela
        this.getContentPane().setBackground(Color.WHITE); // Definição da cor de fundo da janela
        getContentPane().setLayout(null); // Desabilitação do layout automático

        // Tentativa de carregar uma fonte personalizada
        try {
            // Carregamento da fonte personalizada
            File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
            Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
            GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geAreaLogin.registerFont(customFontAreaLogin);

            // Definição de uma fonte personalizada
            Font customFont12 = customFontAreaLogin.deriveFont(16f);

            // Criação e configuração do rótulo de boas-vindas
            JLabel lblNewLabel = new JLabel(
                    "Bem-vindo, " + (conexao.verificarTipoUsuario(conexao.loginAutenticado) ? "professor" : "aluno")
                            + " " + conexao.loginAutenticado + "!");
            lblNewLabel.setBounds(200, 57, 400, 25);
            lblNewLabel.setFont(customFont12);
            lblNewLabel.setForeground(new Color(29, 29, 31));
            getContentPane().add(lblNewLabel);

        } catch (Exception e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
        }

        // Criação e configuração do painel de rolagem para os exercícios
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(200, 80, 400, 300);
        getContentPane().add(scrollPane);

        exerciciosList = new JList<>(listModel); // Inicialização da lista de exercícios
        scrollPane.setViewportView(exerciciosList); // Definição do painel de rolagem para a lista de exercícios

        JLabel lblPontos = new JLabel(""); // Criação do rótulo para exibição de pontos
        lblPontos.setBounds(500, 59, 100, 20); // Definição da posição e dimensões do rótulo
        getContentPane().add(lblPontos); // Adição do rótulo ao conteúdo da janela

        MyButton btnNewButton = new MyButton(); // Criação de um botão personalizado
        btnNewButton.setBounds(340, 390, 120, 25); // Definição da posição e dimensões do botão
        btnNewButton.setText("Fazer exercício"); // Definição do texto do botão
        try {
            // Tentativa de carregar uma fonte personalizada para o botão
            File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
            Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
            GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geAreaLogin.registerFont(customFontAreaLogin);
            Font customFont12 = customFontAreaLogin.deriveFont(11f);
            btnNewButton.setFont(customFont12); // Definição da fonte personalizada para o botão
        } catch (Exception e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
        }
        btnNewButton.setColor(new Color(0xe3e3e3)); // Definição da cor de fundo do botão
        btnNewButton.setForeground(new Color(57, 149, 247)); // Definição da cor do texto do botão
        getContentPane().add(btnNewButton); // Adição do botão ao conteúdo da janela

        JMenuBar menuBar = new JMenuBar(); // Criação de uma barra de menu
        setJMenuBar(menuBar); // Definição da barra de menu para a janela

        JMenu menuMT = new JMenu("Menu"); // Criação de um menu
        menuBar.add(menuMT); // Adição do menu à barra de menu

        JMenuItem menuItemConhecendoMT = new JMenuItem("Conhecendo a MT"); // Criação de um item de menu
        menuMT.add(menuItemConhecendoMT); // Adição do item de menu ao menu

        JMenuItem mntmNewMenuItem2 = new JMenuItem("MT Transdutora"); // Criação de um item de menu
        menuMT.add(mntmNewMenuItem2); // Adição do item de menu ao menu

        JMenuItem mntmNewMenuItem = new JMenuItem("MT Reconhecedora"); // Criação de um item de menu
        menuMT.add(mntmNewMenuItem); // Adição do item de menu ao menu
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ranking"); // Criação de um item de menu
        menuBar.add(mntmNewMenuItem_1); // Adição do item de menu ao menu

        // ActionListener para o item "Ranking"
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a janela Reconhecedora
                Ranking ranking = new Ranking();
                ranking.showWindow(); // Exibe a janela Reconhecedora
            }
        });        
        
        // ActionListener para o item "MT Reconhecedora"
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a janela Reconhecedora
                Reconhecedora reconhecedora = new Reconhecedora();
                reconhecedora.showWindow(); // Exibe a janela Reconhecedora
            }
        });

        // ActionListener para o item "MT Transdutora"
        mntmNewMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a janela Transdutora
                Transdutora transdutora = new Transdutora();
                transdutora.showWindow(); // Exibe a janela Transdutora
            }
        });

        // ActionListener para o item "Conhecendo a MT"
        menuItemConhecendoMT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a janela Infos
                Infos infos = new Infos();
                infos.showWindow(); // Exibe a janela Infos
            }
        });

        // ActionListener para o botão "Fazer exercício"
        btnNewButton.addActionListener(e -> {
            if (exerciciosList.getSelectedIndex() != -1) {
                int selectedIndex = exerciciosList.getSelectedIndex();
                Exercicio exercicioSelecionado = null;
                try {
                    exercicioSelecionado = conexao.obterTodosExercicios().get(selectedIndex);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                ExercicioWindow exercicioWindow = null;
                try {
                    exercicioWindow = new ExercicioWindow(conexao);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                exercicioWindow.exibirInformacoes(exercicioSelecionado.getPergunta(), exercicioSelecionado.getPontos(),
                        exercicioSelecionado.getPalavraTestada1(), exercicioSelecionado.getPalavraTestada2(),
                        exercicioSelecionado.getPalavraTestada3(), exercicioSelecionado.getId());

                exercicioWindow.getFrame().setVisible(true); // Exibe a janela do exercício
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um exercício para continuar."); // Exibe mensagem de erro
            }
        });

        // Timer para atualização periódica da lista de exercícios e dos pontos
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    listModel.clear(); // Limpa o modelo de lista de exercícios
                    List<Exercicio> exercicios = conexao.obterTodosExercicios(); // Obtém a lista de exercícios do banco de dados

                    if (exercicios.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há exercícios registrados."); // Exibe mensagem se não houver exercícios
                    } else {
                        // Adiciona os exercícios ao modelo de lista
                        for (Exercicio exercicio : exercicios) {
                            String item = String.format("%d: %s - %s, %s, %s - Pontos: %d", exercicio.getId(),
                                    exercicio.getPergunta(), exercicio.getPalavraTestada1(),
                                    exercicio.getPalavraTestada2(), exercicio.getPalavraTestada3(),
                                    exercicio.getPontos());
                            listModel.addElement(item);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
                }
                try {
                    // Tentativa de carregar uma fonte personalizada para os pontos
                    File fontFileUsuarioSenha = new File("src\\SFUIText-Light.otf");
                    Font customFontUsuarioSenha = Font.createFont(Font.TRUETYPE_FONT, fontFileUsuarioSenha);
                    GraphicsEnvironment geUsuarioSenha = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    geUsuarioSenha.registerFont(customFontUsuarioSenha);

                    // Definição de uma fonte personalizada para os pontos
                    Font customFont14 = customFontUsuarioSenha.deriveFont(14f);

                    JLabel lblPontos = new JLabel("Pontos:"); // Criação do rótulo para exibição de pontos
                    lblPontos.setFont(customFont14); // Definição da fonte personalizada para os pontos
                    lblPontos.setBounds(500, 59, 100, 20); // Definição da posição e dimensões do rótulo
                    lblPontos.setForeground(new Color(29, 29, 31)); // Definição da cor do rótulo
                    getContentPane().add(lblPontos); // Adição do rótulo ao conteúdo da janela
                    lblPontos.setText("Pontos: " + conexao.obterPontosAluno(conexao.loginAutenticado)); // Atualização dos pontos exibidos

                } catch (Exception e) {
                    e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
                }
            }

        }, 0, 5000); // Executa a tarefa a cada 5 segundos
    }
}
