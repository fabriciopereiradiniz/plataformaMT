package entities;

import java.sql.SQLException;
import java.util.List;

import DB.Conexao;

// Classe Professor, subclasse de Pessoa
public class Professor extends Pessoa {
    private String disciplina; // Disciplina lecionada pelo professor

    // Construtor da classe Professor
    public Professor(String nome, String matricula, String disciplina) {
        super(nome, matricula); // Chama o construtor da superclasse Pessoa
        this.disciplina = disciplina; // Inicializa o atributo disciplina
    }

    // Método para visualizar todos os exercícios registrados
    public void verExercicios(Conexao conexao) {
        try {
            List<Exercicio> exercicios = conexao.obterTodosExercicios(); // Obtém todos os exercícios do banco de dados

            if (exercicios.isEmpty()) {
                System.out.println("Não há exercícios registrados.");
            } else {
                System.out.println("Exercícios registrados:");
                for (Exercicio exercicio : exercicios) {
                    System.out.println("ID: " + exercicio.getId() +
                            ", Pergunta: " + exercicio.getPergunta() +
                            ", Palavra Testada 1: " + exercicio.getPalavraTestada1() +
                            ", Palavra Testada 2: " + exercicio.getPalavraTestada2() +
                            ", Palavra Testada 3: " + exercicio.getPalavraTestada3());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções de SQL
        }
    }

    // Método para excluir um exercício pelo ID
    public void excluirExercicio(Conexao conexao, int idExercicio) {
        try {
            conexao.excluirExercicio(idExercicio); // Chama o método para excluir exercício na classe de conexão
            System.out.println("Exercício com ID " + idExercicio + " excluído pelo professor " + getNome() +
                    " na disciplina " + disciplina + ".");
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções de SQL
        }
    }

    // Método sobrescrito para exibir detalhes do professor
    @Override
    public void exibirDetalhes() {
        System.out.println("Professor: " + getNome() + ", Matrícula: " + getMatricula() +
                ", Disciplina: " + disciplina);
    }
}
