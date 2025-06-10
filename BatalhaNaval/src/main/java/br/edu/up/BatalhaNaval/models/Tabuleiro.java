package br.edu.up.BatalhaNaval.models;

public class Tabuleiro {

    private int largura;
    private int altura;
    private String dono;

    public Tabuleiro(String nome, int largura, int altura, String dono) {
        this.nome = nome;
        this.largura = largura;
        this.altura = altura;
        this.dono = dono;
        this.pontuacao = 0;
        this.matriz = new String[altura][largura];
        this.matrizEscondida = new String[altura][largura];
        preencheTabuleiro();
    }

    private int pontuacao;

    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    //=================================================================================================================
    // Atributos
    //=================================================================================================================
    private String nome;
    private int tamanho;
    private String[][] matriz;
    private String[][] matrizEscondida;

    //=================================================================================================================
    // Construtor
    //=================================================================================================================
    public Tabuleiro(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.matriz = new String[tamanho][tamanho];
        this.matrizEscondida = new String[tamanho][tamanho];
        preencheTabuleiro(); // já inicia o tabuleiro preenchido
    }

    //=================================================================================================================
    // Getters e Setters
    //=================================================================================================================
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getTamanho() { return tamanho; }
    public void setTamanho(int tamanho) { this.tamanho = tamanho; }

    public String getMatriz(int x, int y) { return matriz[x][y]; }

    public void setMatriz(int x, int y, String valor) { matriz[x][y] = valor; }

    public void setMatrizEscondida(int x, int y) {
        matrizEscondida[x][y] = matriz[x][y];
    }

    public boolean isCelulaLivre(int x, int y) {
        return dentroDosLimites(x, y) && matriz[x][y].equals("~");
    }

    public boolean dentroDosLimites(int x, int y) {
        return x >= 0 && y >= 0 && x < tamanho && y < tamanho;
    }

    //=================================================================================================================
    // Métodos
    //=================================================================================================================
    public void preencheTabuleiro() {
        for (int y = 0; y < this.tamanho; y++) {
            for (int x = 0; x < this.tamanho; x++) {
                matriz[x][y] = "~";
            }
        }
        for (int y = 0; y < this.tamanho; y++) {
            for (int x = 0; x < this.tamanho; x++) {
                matrizEscondida[x][y] = "X";
            }
        }
    }

    public String[][] getMatrizCompleta() {
        return matriz;
    }

    public String[][] getMatrizEscondida() { return matrizEscondida; }
}