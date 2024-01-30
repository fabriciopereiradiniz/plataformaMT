package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DB.Conexao;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class ExercicioWindow {

    private JFrame frame; // Instância da classe JFrame para criação da janela
    private String palavraTestada1; // Palavra a ser testada 1
    private String palavraTestada2; // Palavra a ser testada 2
    private String palavraTestada3; // Palavra a ser testada 3
    private int idExercicio; // ID do exercício
    private Conexao conexao; // Instância da classe Conexao para manipulação de conexão com o banco de dados

    // Construtor da classe ExercicioWindow
    public ExercicioWindow(Conexao conexao) throws ClassNotFoundException, SQLException {
        this.conexao = conexao; // Inicialização da conexão com o banco de dados
        initialize(); // Chamada do método de inicialização
    }

    // Método de inicialização da janela de exercício
    private void initialize() {
        frame = new JFrame(); // Inicialização da janela
        frame.setBounds(100, 100, 1300, 720); // Definição das dimensões da janela
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Definição do comportamento ao fechar a janela
        frame.setResizable(false); // Desabilitação do redimensionamento da janela

        // Criação do botão para abrir o CMD com AutomatoExecutor
        JButton btnAbrirCMD = new JButton("Abrir CMD com AutomatoExecutor");
        btnAbrirCMD.setBounds(380, 425, 250, 30); // Definição das dimensões e posição do botão
        btnAbrirCMD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Verificação se o exercício já foi realizado pelo usuário
                    boolean exercicioRealizado = conexao.exercicioJaRealizado(conexao.idUsuarioAutenticado, idExercicio);
                    if (exercicioRealizado) {
                        // Exibição de alerta caso o exercício já tenha sido realizado
                        exibirAlerta("Já realizado...", "Você já completou esse exercício com êxito, fazer ele novamente não te dará mais pontos.");
                        System.out.println("O exercício foi realizado pelo usuário.");
                    } else {
                        System.out.println("O exercício não foi realizado pelo usuário.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
                }
                // Chamada do método para abrir o CMD com AutomatoExecutor
                abrirCMDComAutomatoExecutor(conexao);
            }
        });
        frame.getContentPane().setLayout(null); // Definição do layout da janela como nulo
        frame.getContentPane().add(btnAbrirCMD); // Adição do botão à janela
    }

    // Método para abrir o CMD com AutomatoExecutor
    private void abrirCMDComAutomatoExecutor(Conexao conexao) {
        try {
            String auxId = String.valueOf(idExercicio);
            int useridAux = conexao.idUsuarioAutenticado;
            String userid = String.valueOf(useridAux);
            System.out.println("DEBUG IDEXERCICIO:" + auxId);
            System.out.println(userid);
            // Construção do comando com as palavras a serem testadas como argumentos
            String[] command = {"cmd.exe", "/c", "start", "cmd.exe", "/k", "java", "-jar", "src/UI/verifyTuring.jar",
                    palavraTestada1, palavraTestada2, palavraTestada3, auxId, userid};

            // Início do processo com o comando
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
        } catch (IOException e) {
            e.printStackTrace(); // Impressão do rastreamento da pilha em caso de erro
        }
    }

    // Método para exibir alerta
    private void exibirAlerta(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE); // Exibição do alerta
    }

    // Método para exibir informações do exercício na janela
    public void exibirInformacoes(String nome, int pontos, String palavraTestada1, String palavraTestada2, String palavraTestada3, int idExercicio) {
        this.palavraTestada1 = palavraTestada1; // Atribuição da palavra testada 1
        this.palavraTestada2 = palavraTestada2; // Atribuição da palavra testada 2
        this.palavraTestada3 = palavraTestada3; // Atribuição da palavra testada 3
        this.idExercicio = idExercicio; // Atribuição do ID do exercício
        // Criação e posicionamento dos componentes de texto na janela
        JLabel labelNome = new JLabel("Nome do Exercício: " + nome);
        JLabel labelPontos = new JLabel("Concluindo este exercício você ganha +" + pontos);
        JLabel label1 = new JLabel("Primeira palavra que deve ser testada: " + palavraTestada1);
        JLabel label2 = new JLabel("Segunda palavra que deve ser testada: " + palavraTestada2);
        JLabel label3 = new JLabel("Terceira palavra que deve ser testada: " + palavraTestada3);

        labelNome.setBounds(10, 40, 800, 20); // Definição das dimensões e posição do texto
        labelPontos.setBounds(10, 70, 800, 20); // Definição das dimensões e posição do texto
        label1.setBounds(10, 100, 800, 20); // Definição das dimensões e posição do texto
        label2.setBounds(10, 130, 800, 20); // Definição das dimensões e posição do texto
        label3.setBounds(10, 160, 800, 20); // Definição das dimensões e posição do texto

        frame.getContentPane().setLayout(null); // Definição do layout da janela como nulo
        frame.getContentPane().add(labelNome); // Adição do texto à janela
        frame.getContentPane().add(labelPontos); // Adição do texto à janela
        frame.getContentPane().add(label1); // Adição do texto à janela
        frame.getContentPane().add(label2); // Adição do texto à janela
        frame.getContentPane().add(label3); // Adição do texto à janela
    }

    // Método para obter a janela
    public JFrame getFrame() {
        return frame; // Retorno da instância da janela
    }
}