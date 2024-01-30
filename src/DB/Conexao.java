package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entities.Exercicio;

public class Conexao {
    // Variáveis de conexão e autenticação
    private String databaseURL;
    private String user;
    private String password;
    private Connection con;
    public String loginAutenticado; // Variável pública para armazenar o login autenticado
    public int idUsuarioAutenticado;
    public int debugger;

    // Construtor para inicializar a conexão com o banco de dados
    public Conexao() throws ClassNotFoundException, SQLException {
        // Configurações de conexão
        databaseURL = "jdbc:postgresql://localhost:5432/projetoNakahata";
        user = "postgres";
        password = "postgres";

        // Carrega o driver do PostgreSQL e estabelece a conexão
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(databaseURL, user, password);

        System.out.println("Conexão realizada com sucesso.");
    }

    // Método para obter os pontos de um aluno com base no login
    public int obterPontosAluno(String login) throws SQLException {
        String sql = "SELECT pontos FROM aluno WHERE login = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("pontos");
            }
        }
        return 0; // Retorna 0 se o aluno não for encontrado
    }

    // Método para obter todos os alunos cadastrados
    public List<String> obterTodosAlunos() throws SQLException {
        List<String> alunos = new ArrayList<>();
        String sql = "SELECT name FROM aluno";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                alunos.add(resultSet.getString("name"));
            }
        }
        return alunos;
    }

    // Método para obter os exercícios feitos por um aluno específico
    public List<Exercicio> obterExerciciosFeitosPorAluno(String aluno) throws SQLException {
        List<Exercicio> exerciciosFeitos = new ArrayList<>();
        String sql = "SELECT e.id, e.pergunta, e.palavra_testada1, e.palavra_testada2, e.palavra_testada3, e.pontos " +
                     "FROM exercicio e " +
                     "INNER JOIN usuarios_exercicios ue ON e.id = ue.id_exercicio " +
                     "INNER JOIN aluno a ON ue.id_usuario = a.id " +
                     "WHERE a.name = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, aluno);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pergunta = resultSet.getString("pergunta");
                String palavraTestada1 = resultSet.getString("palavra_testada1");
                String palavraTestada2 = resultSet.getString("palavra_testada2");
                String palavraTestada3 = resultSet.getString("palavra_testada3");
                int pontos = resultSet.getInt("pontos");
                Exercicio exercicio = new Exercicio(id, pergunta, palavraTestada1, palavraTestada2, palavraTestada3, pontos);
                exerciciosFeitos.add(exercicio);
            }
        }
        return exerciciosFeitos;
    }

    // Método para obter detalhes dos exercícios mais feitos nos últimos 30 dias
    public List<Exercicio> obterExerciciosMaisFeitosDetalhadosUltimos30Dias() throws SQLException {
        List<Exercicio> exercicios = new ArrayList<>();
        String sql = "SELECT id_exercicio, COUNT(*) AS quantidade_feita " +
                     "FROM usuarios_exercicios " +
                     "WHERE data_realizacao >= CURRENT_DATE - INTERVAL '30 days' " +
                     "GROUP BY id_exercicio " +
                     "ORDER BY quantidade_feita DESC";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int idExercicio = resultSet.getInt("id_exercicio");
                int quantidadeFeita = resultSet.getInt("quantidade_feita");
                Exercicio exercicio = obterExercicioPorId(idExercicio);
                if (exercicio != null) {
                    exercicio.setQuantidadeFeita(quantidadeFeita);
                    exercicios.add(exercicio);
                }
            }
        }
        return exercicios;
    }

    // Método para obter o ID de usuário logado
    public int obterIdUsuarioLogado() throws SQLException {
        String tableName = verificarTipoUsuario(loginAutenticado) ? "professor" : "aluno";
        String sql = "SELECT id FROM " + tableName + " WHERE login = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, loginAutenticado);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1; // Retorna -1 se o ID não for encontrado
    }

    // Método para obter todos os exercícios cadastrados
    public List<Exercicio> obterTodosExercicios() throws SQLException {
        List<Exercicio> listaExercicios = new ArrayList<>();
        String sql = "SELECT id, pergunta, palavra_testada1, palavra_testada2, palavra_testada3, pontos FROM exercicio";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pergunta = resultSet.getString("pergunta");
                String palavraTestada1 = resultSet.getString("palavra_testada1");
                String palavraTestada2 = resultSet.getString("palavra_testada2");
                String palavraTestada3 = resultSet.getString("palavra_testada3");
                int pontos = resultSet.getInt("pontos");
                Exercicio exercicio = new Exercicio(id, pergunta, palavraTestada1, palavraTestada2, palavraTestada3, pontos);
                listaExercicios.add(exercicio);
            }
        }
        return listaExercicios;
    }

    // Método para excluir um exercício pelo seu ID
    public void excluirExercicio(int idExercicio) throws SQLException {
        String sql = "DELETE FROM exercicio WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idExercicio);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Exercício com ID " + idExercicio + " excluído com sucesso.");
            } else {
                System.out.println("Nenhum exercício encontrado com o ID " + idExercicio + ".");
            }
        }
    }

    // Método para registrar um novo exercício
    public void registrarExercicio(String pergunta, String palavraTestada1, String palavraTestada2,
            String palavraTestada3, int pontos) throws SQLException {
        String sql = "INSERT INTO exercicio (pergunta, palavra_testada1, palavra_testada2, palavra_testada3, pontos) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pergunta);
            stmt.setString(2, palavraTestada1);
            stmt.setString(3, palavraTestada2);
            stmt.setString(4, palavraTestada3);
            stmt.setInt(5, pontos);
            stmt.executeUpdate();
            System.out.println("Exercício registrado no banco de dados com sucesso.");
        }
    }

    // Método para autenticar um usuário com base no login e senha
    public boolean autenticarUsuario(String usuario, String senha) throws SQLException {
        String sql = "SELECT id, login FROM aluno WHERE login = ? AND password = ? " + "UNION "
                + "SELECT id, login FROM professor WHERE login = ? AND password = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.setString(3, usuario);
            stmt.setString(4, senha);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                idUsuarioAutenticado = resultSet.getInt(1); // Armazena o ID autenticado
                loginAutenticado = resultSet.getString(2); // Armazena o login autenticado
                System.out.println(idUsuarioAutenticado);
                debugger = idUsuarioAutenticado;
                return true;
            } else {
                return false;
            }
        }
    }

    // Método para verificar se um login já existe no banco de dados
    public boolean verificarLoginExistente(String login) throws SQLException {
        String sqlProfessor = "SELECT COUNT(*) AS count FROM professor WHERE login = ?";
        String sqlAluno = "SELECT COUNT(*) AS count FROM aluno WHERE login = ?";
        try (PreparedStatement stmtProfessor = con.prepareStatement(sqlProfessor);
                PreparedStatement stmtAluno = con.prepareStatement(sqlAluno)) {
            stmtProfessor.setString(1, login);
            stmtAluno.setString(1, login);
            ResultSet resultSetProfessor = stmtProfessor.executeQuery();
            ResultSet resultSetAluno = stmtAluno.executeQuery();
            int countProfessor = resultSetProfessor.next() ? resultSetProfessor.getInt("count") : 0;
            int countAluno = resultSetAluno.next() ? resultSetAluno.getInt("count") : 0;
            return (countProfessor > 0 || countAluno > 0);
        }
    }

    // Método para excluir todos os professores e alunos cadastrados
    public void excluirTodosProfessoresAlunos() throws SQLException {
        String sqlDeleteProfessores = "DELETE FROM professor";
        String sqlDeleteAlunos = "DELETE FROM aluno";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sqlDeleteProfessores);
            stmt.executeUpdate(sqlDeleteAlunos);
        }
    }

    // Método para obter detalhes de um exercício pelo seu ID
    public Exercicio obterExercicioPorId(int idExercicio) throws SQLException {
        String sql = "SELECT id, pergunta, palavra_testada1, palavra_testada2, palavra_testada3, pontos FROM exercicio WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idExercicio);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pergunta = resultSet.getString("pergunta");
                String palavraTestada1 = resultSet.getString("palavra_testada1");
                String palavraTestada2 = resultSet.getString("palavra_testada2");
                String palavraTestada3 = resultSet.getString("palavra_testada3");
                int pontos = resultSet.getInt("pontos");
                return new Exercicio(id, pergunta, palavraTestada1, palavraTestada2, palavraTestada3, pontos);
            }
        }
        return null; // Retorna null se o exercício não for encontrado
    }

    // Método para editar um exercício pelo seu ID
    public void editarExercicio(int idExercicio, String pergunta, String palavraTestada1, String palavraTestada2,
            String palavraTestada3, int pontos) throws SQLException {
        String sql = "UPDATE exercicio SET pergunta = ?, palavra_testada1 = ?, palavra_testada2 = ?, palavra_testada3 = ?, pontos = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pergunta);
            stmt.setString(2, palavraTestada1);
            stmt.setString(3, palavraTestada2);
            stmt.setString(4, palavraTestada3);
            stmt.setInt(5, pontos);
            stmt.setInt(6, idExercicio);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Exercício com ID " + idExercicio + " editado com sucesso.");
            } else {
                System.out.println("Nenhum exercício encontrado com o ID " + idExercicio + ".");
            }
        }
    }

    // Método para realizar um exercício por um usuário
    public void realizarExercicio(int idUsuario, int idExercicio, int pontosDoExercicio) throws SQLException {
        Timestamp dataRealizacao = new Timestamp(new Date().getTime());
        if (!exercicioJaRealizado(idUsuario, idExercicio)) {
            String sqlInsert = "INSERT INTO usuarios_exercicios (id_usuario, id_exercicio, data_realizacao) VALUES (?, ?, ?)";
            try (PreparedStatement stmtInsert = con.prepareStatement(sqlInsert)) {
                stmtInsert.setInt(1, idUsuario);
                stmtInsert.setInt(2, idExercicio);
                stmtInsert.setTimestamp(3, dataRealizacao);
                stmtInsert.executeUpdate();
                String sqlUpdate = "UPDATE public.aluno SET pontos = pontos + ? WHERE id = ?";
                try (PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdate)) {
                    stmtUpdate.setInt(1, pontosDoExercicio);
                    stmtUpdate.setInt(2, idUsuario);
                    stmtUpdate.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para verificar se um exercício já foi realizado por um usuário
    public boolean exercicioJaRealizado(int idUsuario, int idExercicio) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM usuarios_exercicios WHERE id_usuario = ? AND id_exercicio = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idExercicio);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }

    // Método para obter os pontos de um exercício pelo seu ID
    public int obterPontosExercicio(int idExercicio) throws SQLException {
        String sql = "SELECT pontos FROM exercicio WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idExercicio);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("pontos");
            }
        }
        return 0; // Retorna 0 se o exercício não for encontrado
    }

    // Método privado para obter os pontos de um aluno pelo seu ID
    @SuppressWarnings("unused")
    private int obterPontosAluno(int idUsuario) throws SQLException {
        String sql = "SELECT pontos FROM aluno WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("pontos");
            }
        }
        return 0; // Retorna 0 se o aluno não for encontrado
    }

    // Método para obter o nome de um usuário pelo seu login
    public String obterNomePorLogin(String login) throws SQLException {
        String sql = "SELECT name FROM aluno WHERE login = ? " + "UNION "
                + "SELECT name FROM professor WHERE login = ?";
        System.out.println(login);
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, login);
            ResultSet resultSet = stmt.executeQuery();
            System.out.println("Executando SQL: " + sql);
            System.out.println("Parâmetro de login: " + login);
            if (resultSet.next()) {
                String nome = resultSet.getString("name");
                System.out.println("Nome encontrado: " + nome);
                return nome;
            } else {
                System.out.println("Nenhum nome encontrado para o login: " + login);
                return null;
            }
        }
    }

    // Método para registrar um novo aluno no banco de dados
    public void registrarAluno(String login, String senha, String nome) throws SQLException {
        String sqlInsertAluno = "INSERT INTO aluno (login, password, name) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sqlInsertAluno)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, nome);
            stmt.executeUpdate();
        }
    }

    // Método para registrar um novo professor no banco de dados
    public void registrarProfessor(String login, String senha, String nome) throws SQLException {
        String sqlInsertProfessor = "INSERT INTO professor (login, password, name) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sqlInsertProfessor)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, nome);
            stmt.executeUpdate();
        }
    }

    // Método para verificar se um usuário é professor
    public boolean verificarTipoUsuario(String login) throws SQLException {
        String sqlProfessor = "SELECT COUNT(*) AS count FROM professor WHERE login = ?";
        try (PreparedStatement stmtProfessor = con.prepareStatement(sqlProfessor)) {
            stmtProfessor.setString(1, login);
            ResultSet resultSetProfessor = stmtProfessor.executeQuery();
            return resultSetProfessor.next() && resultSetProfessor.getInt("count") > 0;
        }
    }
}
