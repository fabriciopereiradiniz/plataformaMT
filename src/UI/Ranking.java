package UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DB.Conexao;

import java.sql.SQLException;
import java.util.List;

public class Ranking {

    private JFrame frame;
    private Conexao conexao;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ranking window = new Ranking();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Estabelece conexao e chama janela
    public Ranking() {
        try {
            conexao = new Conexao();
            initialize();
            exibirTop5Alunos();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    // Iniciliaza 
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false); // Impede que o usuário redimensione a janela
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 414, 241);
        panel.add(scrollPane);
    }
    // Puxa top 5 do Conexao
    private void exibirTop5Alunos() {
        try {
            List<String> topAlunos = conexao.obterTop5AlunosMaisPontuadosDeSempre();
            JPanel panel = (JPanel) frame.getContentPane().getComponent(0);
            JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
            JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
            for (String aluno : topAlunos) {
                textArea.append(aluno + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para mostrar a janela
    public void showWindow() {
        frame.setVisible(true);
    }
}
