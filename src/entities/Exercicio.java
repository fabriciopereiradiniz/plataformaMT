package entities;

public class Exercicio {
    private int id; // Identificador único do exercício
    private String pergunta; // A pergunta ou título do exercício
    private String palavraTestada1; // Palavra-chave testada 1
    private String palavraTestada2; // Palavra-chave testada 2
    private String palavraTestada3; // Palavra-chave testada 3
    private int pontos; // Pontuação associada ao exercício
    private int quantidadeFeita; // Novo atributo para armazenar a quantidade de vezes que o exercício foi feito

    // Construtor da classe Exercicio
    public Exercicio(int id, String pergunta, String palavraTestada1, String palavraTestada2, String palavraTestada3, int pontos) {
        this.id = id;
        this.pergunta = pergunta;
        this.palavraTestada1 = palavraTestada1;
        this.palavraTestada2 = palavraTestada2;
        this.palavraTestada3 = palavraTestada3;
        this.pontos = pontos;
    }

    // Métodos getters para acessar os atributos privados
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return pergunta; // Retorna o título do exercício (assumindo que o título é a pergunta)
    }

    public String getPergunta() {
        return pergunta; // Retorna a pergunta do exercício
    }

    public String getPalavraTestada1() {
        return palavraTestada1; // Retorna a palavra-chave testada 1 do exercício
    }

    public String getPalavraTestada2() {
        return palavraTestada2; // Retorna a palavra-chave testada 2 do exercício
    }

    public String getPalavraTestada3() {
        return palavraTestada3; // Retorna a palavra-chave testada 3 do exercício
    }

    public int getPontos() {
        return pontos; // Retorna a pontuação associada ao exercício
    }

    public int getQuantidadeFeita() {
        return quantidadeFeita; // Retorna a quantidade de vezes que o exercício foi feito
    }

    // Método setter para atualizar a quantidade de vezes que o exercício foi feito
    public void setQuantidadeFeita(int quantidadeFeita) {
        this.quantidadeFeita = quantidadeFeita;
    }
}
