package entities;

// Classe abstrata Pessoa
public abstract class Pessoa {
    private String nome; // Nome da pessoa
    private String matricula; // Matrícula da pessoa

    // Construtor da classe Pessoa
    public Pessoa(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    // Método getter para obter o nome da pessoa
    public String getNome() {
        return nome;
    }

    // Método getter para obter a matrícula da pessoa
    public String getMatricula() {
        return matricula;
    }

    // Método abstrato para exibir detalhes da pessoa
    public abstract void exibirDetalhes();
}
