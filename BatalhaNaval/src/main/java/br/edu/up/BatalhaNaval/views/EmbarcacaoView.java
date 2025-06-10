package br.edu.up.BatalhaNaval.views;
import java.util.Scanner;

public class EmbarcacaoView {
    private Scanner scanner = new Scanner(System.in);

    public int solicitarOrientacao(String nome, int tamanho) {
        System.out.println("\n1 = Horizontal | 2 = Vertical");
        System.out.println("Escolha a orientacao do " + nome + " (Tamanho " + tamanho + "): ");
        return scanner.nextInt();
    }

    public int solicitarCoordenada(String coordenada) {
        System.out.print("Digite a posição " + coordenada + ": ");
        return scanner.nextInt();
    }

    public void exibirMensagemErro(String msg) {
        System.out.println("Erro: " + msg);
    }

    public void exibirMensagem(String msg) {
        System.out.println(msg);
    }
}
