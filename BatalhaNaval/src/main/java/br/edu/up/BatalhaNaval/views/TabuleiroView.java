package br.edu.up.BatalhaNaval.views;
public class TabuleiroView {
    public void exibirTabuleiro(Tabuleiro tabuleiro) {
        int tamanho = tabuleiro.getTamanho();
        String[][] matriz = tabuleiro.getMatrizCompleta();

        System.out.print("\t");
        for (int x = 0; x < tamanho; x++) {
            System.out.print("[" + x + "]\t");
        }

        for (int y = 0; y < tamanho; y++) {
            System.out.println();
            System.out.print("[" + y + "]\t");
            for (int x = 0; x < tamanho; x++) {
                System.out.print(" " + matriz[x][y] + " \t");
            }
        }
        System.out.println();
    }

    public void exibirTabuleiroEscondido (Tabuleiro tabuleiro) {
        int tamanho = tabuleiro.getTamanho();
        String[][] matriz = tabuleiro.getMatrizEscondida();

        System.out.print("\t");
        for (int x = 0; x < tamanho; x++) {
            System.out.print("[" + x + "]\t");
        }

        for (int y = 0; y < tamanho; y++) {
            System.out.println();
            System.out.print("[" + y + "]\t");
            for (int x = 0; x < tamanho; x++) {
                System.out.print(" " + matriz[x][y] + " \t");
            }
        }
        System.out.println();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
