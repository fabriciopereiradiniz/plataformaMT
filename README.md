# 👨‍💻 plataformaMT

| ![instruction_process](https://i.imgur.com/1jZRPKp.gif) | This project was developed as a part of my coursework at UNESPAR (Universidade Estadual do Paraná) for the "Linguagens Formais e Autômatos" (Formal Languages and Automata) course. Learn Turing machines through interactive exercises. Earn points as you solve problems. Enhance your understanding of computational theory. Check the technical report [HERE](./Relatorio4BI.pdf). |
|---|---|

## Compilation and Execution of the Program

Make sure you have the Java Development Kit (JDK) installed on your machine. Open your IDE and configure postgresql and jgoodies on the classpath of the project, you also will need to install and configure Java Swing.

As the program is not compilated at all you must open directly on an IDE and run the login.java file.
In `Conexao.java` change `databaseURL = "jdbc:postgresql://localhost:5432/projetoNakahata"; user = "postgres"; password = "postgres";` to your configs, not connecting? create 
the tables on postgresql:

`CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255),
    pontos INTEGER
);`

`CREATE TABLE exercicio (
    id SERIAL PRIMARY KEY,
    pergunta TEXT,
    palavra_testada1 VARCHAR(100),
    palavra_testada2 VARCHAR(100),
    palavra_testada3 VARCHAR(100),
    pontos INTEGER
);`

`CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255)
);`

`CREATE TABLE exercicios_usuarios (
    id_usuarios INTEGER REFERENCES aluno(id),
    id_exercicio INTEGER REFERENCES exercicio(id),
    data_realizacao TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (id_usuarios, id_exercicio)
);`


<div align="right">
  <img src="https://i.imgur.com/wu5eh6H.gif" alt="java" width="150">
</div>

## Program Usage

Follow all the steps above, login in your account and you are ready to go! As a professor you can register, edit and delete exercises, and as an alumni you can complete and study exercises using a TM simulator.
