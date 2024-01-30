package UI;

import javax.swing.*;

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

import DB.Conexao;
import entities.Exercicio;

//Classe ProfessorWindow, que estende JFrame para criar a janela principal da aplicação
public class ProfessorWindow extends JFrame {

 private static final long serialVersionUID = 1L; // Número de série da classe
 private Conexao conexao; // Objeto para lidar com a conexão ao banco de dados
 private DefaultListModel<String> listModel; // Modelo de lista para exibir os exercícios
 private JList<String> exerciciosList; // Lista de exercícios
 @SuppressWarnings("unused")
 private JButton btnExcluirExercicio; // Botão para excluir exercícios
 @SuppressWarnings("unused")
 private JButton btnAdicionarExercicio; // Botão para adicionar exercícios
 @SuppressWarnings("unused")
 private JButton btnEditarExercicio; // Botão para editar exercícios

 // Construtor da classe ProfessorWindow
 public ProfessorWindow(Conexao conexao) throws SQLException {
     this.conexao = conexao; // Inicializa a conexão com o banco de dados
     this.listModel = new DefaultListModel<>(); // Inicializa o modelo de lista
     initialize(); // Inicializa a interface gráfica
 }

 // Método para inicializar a interface gráfica
 private void initialize() throws SQLException {
     this.setBounds(100, 100, 800, 600); // Define as dimensões da janela
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
     this.getContentPane().setBackground(Color.WHITE); // Define a cor de fundo da janela como branca
     this.setResizable(false); // Impede que o usuário redimensione a janela

     try {
         // Carrega uma fonte personalizada do arquivo SF-Pro-Display-Regular.otf
         File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
         Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
         GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
         geAreaLogin.registerFont(customFontAreaLogin);

         Font customFont12 = customFontAreaLogin.deriveFont(16f); // Define o tamanho da fonte como 16pt
         getContentPane().setLayout(null); // Define o layout como nulo
         
         // Cria um rótulo de boas-vindas com o nome do usuário logado
         JLabel lblNewLabel = new JLabel("Bem-vindo, " + (conexao.verificarTipoUsuario(conexao.loginAutenticado) ? "professor" : "aluno") + " " + conexao.loginAutenticado + "!");
         lblNewLabel.setBounds(200, 57, 400, 25); // Define a posição e o tamanho do rótulo
         lblNewLabel.setFont(customFont12); // Aplica a fonte personalizada ao rótulo
         lblNewLabel.setForeground(new Color(29, 29, 31)); // Define a cor do texto
         getContentPane().add(lblNewLabel); // Adiciona o rótulo ao conteúdo da janela
         
     } catch (Exception e) {
         e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
     }
     
     // Cria e configura os botões Excluir, Adicionar e Editar exercícios
     MyButton btnExcluirExercicio = new MyButton();
     btnExcluirExercicio.setBounds(200, 390, 120, 25); // Define a posição e o tamanho do botão
     btnExcluirExercicio.setText("Excluir"); // Define o texto do botão
     // Configura a fonte personalizada para o botão
     try {
         File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
         Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
         GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
         geAreaLogin.registerFont(customFontAreaLogin);
         Font customFont12 = customFontAreaLogin.deriveFont(11f); // Define o tamanho da fonte como 11pt
         btnExcluirExercicio.setFont(customFont12); // Aplica a fonte personalizada ao botão
     } catch (Exception e) {
         e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
     }
     btnExcluirExercicio.setColor(new Color(0xe3e3e3)); // Define a cor de fundo do botão
     btnExcluirExercicio.setForeground(new Color(57, 149, 247)); // Define a cor do texto do botão
     getContentPane().add(btnExcluirExercicio); // Adiciona o botão ao conteúdo da janela
     
     // Cria e configura o botão Adicionar exercício
     MyButton btnAdicionarExercicio = new MyButton();
     btnAdicionarExercicio.setBounds(340, 390, 120, 25); // Define a posição e o tamanho do botão
     btnAdicionarExercicio.setText("Adicionar"); // Define o texto do botão
     // Configura a fonte personalizada para o botão
     try {
         File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
         Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
         GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
         geAreaLogin.registerFont(customFontAreaLogin);
         Font customFont12 = customFontAreaLogin.deriveFont(11f); // Define o tamanho da fonte como 11pt
         btnAdicionarExercicio.setFont(customFont12); // Aplica a fonte personalizada ao botão
     } catch (Exception e) {
         e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
     }
     btnAdicionarExercicio.setColor(new Color(0xe3e3e3)); // Define a cor de fundo do botão
     btnAdicionarExercicio.setForeground(new Color(57, 149, 247)); // Define a cor do texto do botão
     getContentPane().add(btnAdicionarExercicio); // Adiciona o botão ao conteúdo da janela

     // Cria e configura o botão Editar exercício
     MyButton btnEditarExercicio = new MyButton();
     btnEditarExercicio.setBounds(480, 390, 120, 25); // Define a posição e o tamanho do botão
     btnEditarExercicio.setText("Editar"); // Define o texto do botão
     // Configura a fonte personalizada para o botão
     try {
         File fontFileAreaLogin = new File("src\\SF-Pro-Display-Regular.otf");
         Font customFontAreaLogin = Font.createFont(Font.TRUETYPE_FONT, fontFileAreaLogin);
         GraphicsEnvironment geAreaLogin = GraphicsEnvironment.getLocalGraphicsEnvironment();
         geAreaLogin.registerFont(customFontAreaLogin);
         Font customFont12 = customFontAreaLogin.deriveFont(11f); // Define o tamanho da fonte como 11pt
         btnEditarExercicio.setFont(customFont12); // Aplica a fonte personalizada ao botão
     } catch (Exception e) {
         e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
     }
     btnEditarExercicio.setColor(new Color(0xe3e3e3)); // Define a cor de fundo do botão
     btnEditarExercicio.setForeground(new Color(57, 149, 247)); // Define a cor do texto do botão
     getContentPane().add(btnEditarExercicio); // Adiciona o botão ao conteúdo da janela

     // Cria a lista de exercícios e a área de rolagem
     exerciciosList = new JList<>(listModel); // Inicializa a lista de exercícios com o modelo de lista
     JScrollPane scrollPane = new JScrollPane(exerciciosList); // Cria uma área de rolagem para a lista
     scrollPane.setBounds(200, 80, 400, 300); // Define a posição e o tamanho da área de rolagem
     getContentPane().add(scrollPane); // Adiciona a área de rolagem ao conteúdo da janela
     
     // Cria uma barra de menus
     JMenuBar menuBar = new JMenuBar();
     menuBar.setBackground(new Color(247, 246, 249)); // Define a cor de fundo da barra de menus
     setJMenuBar(menuBar); // Define a barra de menus da janela

     // Cria um menu "Mais opções" na barra de menus
     JMenu mnNewMenu = new JMenu("Mais opções");
     menuBar.add(mnNewMenu);

     // Cria itens de menu no menu "Mais opções"
     JMenuItem mntmNewMenuItem_1 = new JMenuItem("Estatísticas");
     mnNewMenu.add(mntmNewMenuItem_1); // Adiciona o item de menu ao menu "Mais opções"
     // Define o ouvinte de ação para abrir a janela de estatísticas quando o item de menu for selecionado
     mntmNewMenuItem_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             abrirEstatisticasWindow();
         }
     });

     JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consultar aluno");
     mnNewMenu.add(mntmNewMenuItem_2); // Adiciona o item de menu ao menu "Mais opções"
     // Define o ouvinte de ação para consultar um aluno quando o item de menu for selecionado
     mntmNewMenuItem_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             consultarAluno();
         }
     });

     // Define os ouvintes de ação para os botões Excluir, Adicionar e Editar exercícios
     btnExcluirExercicio.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             excluirExercicioSelecionado();
         }
     });

     btnAdicionarExercicio.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             adicionarNovoExercicio();
         }
     });

     btnEditarExercicio.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             editarExercicioSelecionado();
         }
     });

     // Cria um Timer para atualizar a lista de exercícios a cada 30 segundos
     Timer timer = new Timer();
     timer.scheduleAtFixedRate(new TimerTask() {
         @Override
         public void run() {
             atualizarListaExercicios();
         }
     }, 0, 30000);
 }

 // Método para atualizar a lista de exercícios
 private void atualizarListaExercicios() {
     try {
         listModel.clear(); // Limpa o modelo de lista
         List<Exercicio> exercicios = conexao.obterTodosExercicios(); // Obtém todos os exercícios do banco de dados

         if (exercicios.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Não há exercícios registrados."); // Exibe uma mensagem se não houver exercícios
         } else {
             // Adiciona os exercícios ao modelo de lista formatando-os como strings
             for (Exercicio exercicio : exercicios) {
                 String item = String.format("%d: %s - %s, %s, %s - Pontos: %d",
                         exercicio.getId(), exercicio.getPergunta(),
                         exercicio.getPalavraTestada1(), exercicio.getPalavraTestada2(), exercicio.getPalavraTestada3(),
                         exercicio.getPontos());
                 listModel.addElement(item); // Adiciona o item formatado ao modelo de lista
             }
         }
     } catch (SQLException e) {
         e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
     }
 }
 
 // Método para consultar um aluno
 private void consultarAluno() {
     ConsultarWindow consultarWindow = new ConsultarWindow(); // Cria uma instância da janela de consulta
     consultarWindow.setVisible(true); // Define a janela como visível
 }

 // Método para excluir um exercício selecionado
 private void excluirExercicioSelecionado() {
     int selectedIndex = exerciciosList.getSelectedIndex(); // Obtém o índice do exercício selecionado na lista
     if (selectedIndex != -1) {
         String selectedValue = exerciciosList.getSelectedValue(); // Obtém o valor do exercício selecionado
         int idExercicio = Integer.parseInt(selectedValue.split(":")[0].trim()); // Obtém o ID do exercício

         try {
             conexao.excluirExercicio(idExercicio); // Exclui o exercício do banco de dados
             JOptionPane.showMessageDialog(null, "Exercício excluído com sucesso."); // Exibe uma mensagem de sucesso

             atualizarListaExercicios(); // Atualiza a lista de exercícios após a exclusão
         } catch (SQLException e) {
             e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
             JOptionPane.showMessageDialog(null, "Erro ao excluir o exercício."); // Exibe uma mensagem de erro
         }
     } else {
         JOptionPane.showMessageDialog(null, "Por favor, selecione um exercício para excluir."); // Exibe uma mensagem se nenhum exercício estiver selecionado
     }
 }

 // Método para adicionar um novo exercício
 private void adicionarNovoExercicio() {
     // Cria campos de texto para inserir os detalhes do novo exercício
     JTextField perguntaField = new JTextField();
     JTextField palavra1Field = new JTextField();
     JTextField palavra2Field = new JTextField();
     JTextField palavra3Field = new JTextField();
     JTextField pontosField = new JTextField();

     // Define os elementos da caixa de diálogo para inserir os detalhes do novo exercício
     Object[] message = {
             "Pergunta:", perguntaField,
             "Palavra Testada 1:", palavra1Field,
             "Palavra Testada 2:", palavra2Field,
             "Palavra Testada 3:", palavra3Field,
             "Pontos:", pontosField
     };

     // Exibe uma caixa de diálogo para inserir os detalhes do novo exercício
     int option = JOptionPane.showConfirmDialog(null, message, "Adicionar Novo Exercício", JOptionPane.OK_CANCEL_OPTION);

     if (option == JOptionPane.OK_OPTION) { // Se o usuário clicar em "OK"
         String pergunta = perguntaField.getText(); // Obtém a pergunta do campo de texto
         String palavraTestada1 = palavra1Field.getText(); // Obtém a primeira palavra testada do campo de texto
         String palavraTestada2 = palavra2Field.getText(); // Obtém a segunda palavra testada do campo de texto
         String palavraTestada3 = palavra3Field.getText(); // Obtém a terceira palavra testada do campo de texto
         int pontos = Integer.parseInt(pontosField.getText()); // Obtém os pontos do campo de texto como um inteiro

         try {
             conexao.registrarExercicio(pergunta, palavraTestada1, palavraTestada2, palavraTestada3, pontos); // Registra o novo exercício no banco de dados
             JOptionPane.showMessageDialog(null, "Exercício adicionado com sucesso."); // Exibe uma mensagem de sucesso
         } catch (SQLException e) {
             e.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
             JOptionPane.showMessageDialog(null, "Erro ao adicionar o exercício."); // Exibe uma mensagem de erro
         }

         atualizarListaExercicios(); // Atualiza a lista de exercícios após a adição do novo exercício
     }
 }

 // Método para abrir a janela de estatísticas
 private void abrirEstatisticasWindow() {
     // Cria uma instância da janela de estatísticas
     EstatisticasWindow estatisticasWindow = new EstatisticasWindow();

     // Define a janela de estatísticas como visível
     estatisticasWindow.setVisible(true);
 }

 // Método para editar um exercício selecionado
 private void editarExercicioSelecionado() {
     int selectedIndex = exerciciosList.getSelectedIndex(); // Obtém o índice do exercício selecionado na lista
     if (selectedIndex != -1) {
         String selectedValue = exerciciosList.getSelectedValue(); // Obtém o valor do exercício selecionado
         int idExercicio = Integer.parseInt(selectedValue.split(":")[0].trim()); // Obtém o ID do exercício

         // Cria campos de texto preenchidos com os detalhes atuais do exercício selecionado
         JTextField perguntaField = new JTextField();
         JTextField palavra1Field = new JTextField();
         JTextField palavra2Field = new JTextField();
         JTextField palavra3Field = new JTextField();
         JTextField pontosField = new JTextField();

         // Obtém os detalhes atuais do exercício selecionado
         try {
             Exercicio exercicioAtual = conexao.obterExercicioPorId(idExercicio);
             perguntaField.setText(exercicioAtual.getPergunta());
             palavra1Field.setText(exercicioAtual.getPalavraTestada1());
             palavra2Field.setText(exercicioAtual.getPalavraTestada2());
             palavra3Field.setText(exercicioAtual.getPalavraTestada3());
             pontosField.setText(String.valueOf(exercicioAtual.getPontos()));
         } catch (SQLException ex) {
             ex.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
             JOptionPane.showMessageDialog(null, "Erro ao obter os detalhes do exercício para edição."); // Exibe uma mensagem de erro
             return;
         }

         // Define os elementos da caixa de diálogo para editar o exercício
         Object[] message = {
                 "Pergunta:", perguntaField,
                 "Palavra Testada 1:", palavra1Field,
                 "Palavra Testada 2:", palavra2Field,
                 "Palavra Testada 3:", palavra3Field,
                 "Pontos:", pontosField
         };

         // Exibe uma caixa de diálogo para editar o exercício
         int option = JOptionPane.showConfirmDialog(null, message, "Editar Exercício", JOptionPane.OK_CANCEL_OPTION);

         if (option == JOptionPane.OK_OPTION) { // Se o usuário clicar em "OK"
             String pergunta = perguntaField.getText(); // Obtém a pergunta do campo de texto
             String palavraTestada1 = palavra1Field.getText(); // Obtém a primeira palavra testada do campo de texto
             String palavraTestada2 = palavra2Field.getText(); // Obtém a segunda palavra testada do campo de texto
             String palavraTestada3 = palavra3Field.getText(); // Obtém a terceira palavra testada do campo de texto
             int pontos = Integer.parseInt(pontosField.getText()); // Obtém os pontos do campo de texto como um inteiro

             try {
                 // Edita o exercício no banco de dados com os novos detalhes
                 conexao.editarExercicio(idExercicio, pergunta, palavraTestada1, palavraTestada2, palavraTestada3, pontos);
                 JOptionPane.showMessageDialog(null, "Exercício editado com sucesso."); // Exibe uma mensagem de sucesso
             } catch (SQLException ex) {
                 ex.printStackTrace(); // Imprime o rastreamento da pilha se ocorrer uma exceção
                 JOptionPane.showMessageDialog(null, "Erro ao editar o exercício."); // Exibe uma mensagem de erro
             }

             atualizarListaExercicios(); // Atualiza a lista de exercícios após a edição do exercício
         }
     } else {
         JOptionPane.showMessageDialog(null, "Por favor, selecione um exercício para editar."); // Exibe uma mensagem se nenhum exercício estiver selecionado
     }
 }
}