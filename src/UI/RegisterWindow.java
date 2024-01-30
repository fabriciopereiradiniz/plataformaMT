package UI;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.Conexao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

// Classe para a janela de registro de usuário
public class RegisterWindow {

    private JFrame frame; // Janela principal
    private JTextField textFieldLogin; // Campo de texto para inserir o login
    private JPasswordField passwordField; // Campo de texto para inserir a senha
    private JTextField textFieldNome; // Campo de texto para inserir o nome
    private JCheckBox checkBoxIsProfessor; // Caixa de seleção para indicar se o usuário é professor

    // Construtor da classe RegisterWindow
    public RegisterWindow(Conexao conexao) {
        initialize(); // Inicializa a interface gráfica da janela

        try {
            File fontFileUsuarioSenha = new File("src\\SFUIText-Light.otf");
            Font customFontUsuarioSenha = Font.createFont(Font.TRUETYPE_FONT, fontFileUsuarioSenha);
            GraphicsEnvironment geUsuarioSenha = GraphicsEnvironment.getLocalGraphicsEnvironment();
            geUsuarioSenha.registerFont(customFontUsuarioSenha);

            Font customFont14 = customFontUsuarioSenha.deriveFont(14f);

            MyButton btnRegistrar = new MyButton(); // Botão para registrar o usuário
            btnRegistrar.setText("Registrar"); // Define o texto do botão
            btnRegistrar.setFont(customFont14); // Define a fonte do botão
            btnRegistrar.setBounds(150, 210, 151, 23); // Define a posição e o tamanho do botão
            btnRegistrar.setColor(new Color(0xe3e3e3)); // Define a cor de fundo do botão
            btnRegistrar.setForeground(new Color(57, 149, 247)); // Define a cor do texto do botão

            // Adiciona um ouvinte de ação ao botão Registrar
            btnRegistrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    registrarUsuario(conexao); // Chama o método para registrar o usuário
                }
            });
            frame.getContentPane().add(btnRegistrar); // Adiciona o botão à janela
            frame.setResizable(false); // Define se a janela pode ser redimensionada ou não

            JLabel lblNome = new JLabel("Nome"); // Rótulo para o campo de nome
            lblNome.setFont(customFont14); // Define a fonte do rótulo
            lblNome.setBounds(150, 46, 46, 14); // Define a posição e o tamanho do rótulo
            frame.getContentPane().add(lblNome); // Adiciona o rótulo à janela

            JLabel lblLogin = new JLabel("Login"); // Rótulo para o campo de login
            lblLogin.setFont(customFont14); // Define a fonte do rótulo
            lblLogin.setBounds(150, 87, 46, 14); // Define a posição e o tamanho do rótulo
            frame.getContentPane().add(lblLogin); // Adiciona o rótulo à janela

            JLabel lblSenha = new JLabel("Senha"); // Rótulo para o campo de senha
            lblSenha.setFont(customFont14); // Define a fonte do rótulo
            lblSenha.setBounds(150, 127, 46, 14); // Define a posição e o tamanho do rótulo
            frame.getContentPane().add(lblSenha); // Adiciona o rótulo à janela

            textFieldNome = new JTextField(); // Campo de texto para inserir o nome
            textFieldNome.setBounds(150, 60, 151, 20); // Define a posição e o tamanho do campo de texto
            frame.getContentPane().add(textFieldNome); // Adiciona o campo de texto à janela
            textFieldNome.setColumns(10); // Define o número de colunas do campo de texto

            textFieldLogin = new JTextField(); // Campo de texto para inserir o login
            textFieldLogin.setBounds(150, 100, 151, 20); // Define a posição e o tamanho do campo de texto
            frame.getContentPane().add(textFieldLogin); // Adiciona o campo de texto à janela
            textFieldLogin.setColumns(10); // Define o número de colunas do campo de texto

            passwordField = new JPasswordField(); // Campo de texto para inserir a senha
            passwordField.setBounds(150, 140, 151, 20); // Define a posição e o tamanho do campo de texto
            frame.getContentPane().add(passwordField); // Adiciona o campo de texto à janela

            checkBoxIsProfessor = new JCheckBox("Sou professor"); // Caixa de seleção para indicar se o usuário é professor
            checkBoxIsProfessor.setFont(customFont14); // Define a fonte da caixa de seleção
            checkBoxIsProfessor.setBounds(150, 170, 151, 23); // Define a posição e o tamanho da caixa de seleção

            // Configurando a cor de fundo da JCheckBox
            checkBoxIsProfessor.setBackground(new Color(0xe3e3e3));

            frame.getContentPane().add(checkBoxIsProfessor); // Adiciona a caixa de seleção à janela
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
        }
    }

    // Método para inicializar a janela
    private void initialize() {
        frame = new JFrame(); // Cria uma nova janela
        frame.setBounds(100, 100, 450, 300); // Define a posição e o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento de fechamento da janela
        frame.getContentPane().setLayout(null); // Define o layout nulo para a janela
        frame.getContentPane().setBackground(Color.WHITE); // Define a cor de fundo da janela como branca
    }

    // Método para registrar um novo usuário
    private void registrarUsuario(Conexao conexao) {
        String login = textFieldLogin.getText(); // Obtém o login inserido pelo usuário
        String senha = new String(passwordField.getPassword()); // Obtém a senha inserida pelo usuário
        String nome = textFieldNome.getText(); // Obtém o nome inserido pelo usuário
        boolean isProfessor = checkBoxIsProfessor.isSelected(); // Verifica se o usuário é um professor

        // Verifica se a senha tem pelo menos 8 caracteres
        if (senha.length() < 8) {
            JOptionPane.showMessageDialog(frame, "A senha deve ter no mínimo 8 caracteres.");
        } else {
            try {
                // Verifica se o login já está em uso
                if (conexao.verificarLoginExistente(login)) {
                    JOptionPane.showMessageDialog(frame, "O login já está em uso. Escolha outro.");
                } else {
                    try {
                        // Registra o usuário como professor ou aluno, dependendo da seleção do usuário
                        if (isProfessor) {
                            conexao.registrarProfessor(login, senha, nome);
                        } else {
                            conexao.registrarAluno(login, senha, nome);
                        }
                        JOptionPane.showMessageDialog(frame, "Registro bem-sucedido!"); // Exibe uma mensagem de registro bem-sucedido
                        frame.dispose(); // Fecha a janela de registro
                    } catch (SQLException ex) {
                        ex.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
                        JOptionPane.showMessageDialog(frame, "Erro ao registrar. Verifique os dados e tente novamente."); // Exibe uma mensagem de erro ao registrar
                    }
                }
            } catch (HeadlessException | SQLException e1) {
                e1.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
            }
        }
    }

    // Método para obter a janela principal
    public JFrame getFrame() {
        return frame; // Retorna a janela principal
    }
}
