package br.edu.up.BatalhaNaval.models;

public abstract class Embarcacao {
    private String nome;
    private String categoria;
    private int slots;
    private int orientacao;

    public Embarcacao(String nome, String categoria, int slots) {
        this.nome = nome;
        this.categoria = categoria;
        this.slots = slots;
    }

    public boolean posicionar(Tabuleiro tabuleiro, int posicaoX, int posicaoY, int orientacao) {
        this.orientacao = orientacao;
        boolean validacao = true;

        if (orientacao == 1) { // Horizontal
            for (int i = 0; i < slots; i++) {
                if (!tabuleiro.isCelulaLivre(posicaoX + i, posicaoY)) {
                    validacao = false;
                    break;
                }
            }
            if (validacao) {
                for (int i = 0; i < slots; i++) {
                    tabuleiro.setMatriz(posicaoX + i, posicaoY, categoria);
                }
            }
        } else if (orientacao == 2) { // Vertical
            for (int i = 0; i < slots; i++) {
                if (!tabuleiro.isCelulaLivre(posicaoX, posicaoY + i)) {
                    validacao = false;
                    break;
                }
            }
            if (validacao) {
                for (int i = 0; i < slots; i++) {
                    tabuleiro.setMatriz(posicaoX, posicaoY + i, categoria);
                }
            }
        }

        return validacao;
    }

    // Getters
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public int getSlots() { return slots; }
}