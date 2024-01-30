package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.Conexao;

public class Login {

    // Atributos da classe Login.
    private JFrame frame; // Janela principal da aplicação.
    private JTextField textField; // Campo de entrada para o nome de usuário.
    private JPasswordField passwordField; // Campo de entrada para a senha do usuário.
    private JLabel lblErro; // Rótulo para exibir mensagens de erro.
    private Conexao conexao; // Objeto para realizar a conexão com o banco de dados.

    // Método principal para iniciar a aplicação.
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cria uma instância da classe Login e torna a janela visível.
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Construtor da classe Login.
    public Login() {
        initialize(); // Inicializa os componentes da interface de login.

        try {
            conexao = new Conexao(); // Estabelece uma conexão com o banco de dados.
            new ProfessorWindow(conexao); // Abre a janela do professor após o login.
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para inicializar os componentes da interface de login.
    private void initialize() {
        // Configurações da janela principal.
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);

        // Configuração e adição da imagem de login.
        ImageIcon icon = new ImageIcon("src\\login.png");
        JLabel lblImagem = new JLabel(icon);
        lblImagem.setBounds(0, 0, 220, 263);
        lblImagem.setBackground(new Color(0, 0, 0, 0));
        frame.getContentPane().add(lblImagem);

        // Configuração e adição dos campos de texto para login e senha.
        try {
            // Carrega e define a fonte personalizada para a área de login.
            File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
            Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
            GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geAreaLogin.registerFont(customFontAreaLogin);
            Font customFont12 = customFontAreaLogin.deriveFont(20f);

            // Configuração do rótulo "Área de login".
            JLabel lblNewLabel = new JLabel("Área de login");
            lblNewLabel.setFont(customFont12);
            lblNewLabel.setBounds(280, 45, 110, 24);
            lblNewLabel.setForeground(new Color(29, 29, 31));
            frame.getContentPane().add(lblNewLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuração e adição dos rótulos para os campos de login e senha.
        try {
            // Carrega e define a fonte personalizada para os campos de login e senha.
            File fontFileUsuarioSenha = new File("src\\SFUIText-Light.otf");
            Font customFontUsuarioSenha = Font.createFont(Font.TRUETYPE_FONT, fontFileUsuarioSenha);
            GraphicsEnvironment geUsuarioSenha = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geUsuarioSenha.registerFont(customFontUsuarioSenha);
            Font customFont14 = customFontUsuarioSenha.deriveFont(14f);

            // Configuração dos rótulos "Login" e "Senha".
            JLabel lblNewLabel_1 = new JLabel("Login");
            lblNewLabel_1.setFont(customFont14);
            lblNewLabel_1.setBounds(230, 89, 50, 20);
            lblNewLabel_1.setForeground(new Color(29, 29, 31));
            frame.getContentPane().add(lblNewLabel_1);

            JLabel lblNewLabel_1_1 = new JLabel("Senha");
            lblNewLabel_1_1.setFont(customFont14);
            lblNewLabel_1_1.setBounds(230, 131, 60, 20);
            lblNewLabel_1_1.setForeground(new Color(29, 29, 31));
            frame.getContentPane().add(lblNewLabel_1_1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuração e adição dos campos de entrada para login e senha.
        textField = new JTextField();
        textField.setBounds(230, 111, 196, 19);
        textField.setForeground(new Color(29, 29, 31));
        frame.getContentPane().add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(230, 151, 196, 19);
        frame.getContentPane().add(passwordField);

        // Configuração e adição do botão de login.
        MyButton btnNewButton = new MyButton();
        btnNewButton.setText("Logar");
        try {
            // Carrega e define a fonte personalizada para o botão de login.
            File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
            Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
            GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geAreaLogin.registerFont(customFontAreaLogin);
            Font customFont12 = customFontAreaLogin.deriveFont(11f);
            btnNewButton.setFont(customFont12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnNewButton.setBounds(230, 191, 196, 21);
        btnNewButton.setColor(new Color(0xe3e3e3));
        btnNewButton.setForeground(new Color(57, 149, 247));
        frame.getContentPane().add(btnNewButton);

        // Configuração e adição do rótulo "Não tem uma conta? Registre-se".
        try {
            // Carrega e define a fonte personalizada para o rótulo de registro.
            File fontFileUsuarioSenha = new File("src\\SFUIText-Light.otf");
            Font customFontUsuarioSenha = Font.createFont(Font.TRUETYPE_FONT, fontFileUsuarioSenha);
            GraphicsEnvironment geUsuarioSenha = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geUsuarioSenha.registerFont(customFontUsuarioSenha);
            Font customFont14 = customFontUsuarioSenha.deriveFont(9f);

            // Configuração do rótulo de registro.
            JLabel lblRegistrar = new JLabel("Não tem uma conta? Registre-se");
            lblRegistrar.setForeground(Color.black);
            lblRegistrar.setFont(customFont14);
            lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblRegistrar.setBounds(288, 171, 138, 20);
            frame.getContentPane().add(lblRegistrar);

            // Adiciona um listener de mouse para o rótulo de registro.
            lblRegistrar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mostrarJanelaRegistro(); // Abre a janela de registro ao clicar no rótulo.
                }
            });

            lblErro = new JLabel(""); // Inicializa o rótulo de erro.
            lblErro.setForeground(Color.RED);
            lblErro.setFont(customFont14);
            lblErro.setBounds(388, 219, 38, 20);
            frame.getContentPane().add(lblErro);

            // Adiciona um listener de ação para o botão de login.
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String usuario = textField.getText();
                    String senha = new String(passwordField.getPassword());

                    autenticar(usuario, senha); // Autentica o usuário ao clicar no botão de login.
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar a janela de registro.
    private void mostrarJanelaRegistro() {
        RegisterWindow registerWindow = new RegisterWindow(conexao);
        registerWindow.getFrame().setVisible(true);
    }

    // Método para autenticar o usuário.
    private void autenticar(String usuario, String senha) {
        try {
            boolean autenticado = conexao.autenticarUsuario(usuario, senha);

            if (autenticado) {
                if (conexao.verificarTipoUsuario(usuario)) {
                    abrirJanelaProfessor(usuario); // Abre a janela do professor se o login for bem-sucedido.
                } else {
                    abrirJanelaAluno(usuario); // Abre a janela do aluno se o login for bem-sucedido.
                }
            } else {
                exibirMensagemErro("Credenciais inválidas.", lblErro); // Exibe mensagem de erro se as credenciais forem inválidas.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para abrir a janela do professor.
    private void abrirJanelaProfessor(String usuario) throws SQLException {
        ProfessorWindow professorWindow = new ProfessorWindow(conexao);
        professorWindow.setVisible(true);
        frame.dispose(); // Fecha a janela de login após abrir a janela do professor.
    }

    // Método para abrir a janela do aluno.
    private void abrirJanelaAluno(String usuario) throws SQLException {
        AlunoWindow alunoWindow = new AlunoWindow(conexao);
        alunoWindow.setVisible(true);
        frame.dispose(); // Fecha a janela de login após abrir a janela do aluno.
    }

    // Método para exibir mensagens de erro.
    private void exibirMensagemErro(String mensagem, JLabel lblErro) {
        lblErro.setText(mensagem);
        frame.revalidate();
        frame.repaint();
    }
}
