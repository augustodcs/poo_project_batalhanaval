package br.edu.up.BatalhaNaval.views;
import controller.LoginController;
import controller.TabuleiroController;
import model.Jogador;
import model.Tabuleiro;
import repository.UsuarioRepository;

import java.util.Scanner;

public class Menu {
    UsuarioRepository usuarioRepository = new UsuarioRepository();
    LoginController loginController = new LoginController(usuarioRepository);

    public void menuEntrada() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Bem vindo ao MSB! Como gostaria de prosseguir? \n1 - Cadastrar\n2 - Entrar\n3 - Sair");
            try {
                int op = scanner.nextInt();
                if(op == 1) {
                    cadastrar();
                } else if (op == 2) {
                    logar();
                } else if (op == 3) {
                   System.exit(0);
                } else {
                    System.out.println("Opção inválida!");
                    scanner.close();
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
                scanner.close();
            }
            /*
            System.out.print("Nome de usuário: ");
            String nome = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            if (op == 1) {
                if (ctrl.cadastrar(nome, senha, false)) System.out.println("Cadastrado!");
                else System.out.println("Esse nome de usuário não está disponível.");
            } else {
                logado = ctrl.logar(nome, senha);
                if (logado == null) System.out.println("Falha no login.");
            }
             */
        } while(true);

        /*
        boolean sair = false;
        while (!sair) {
            System.out.println("\n1 - Alterar conta\n2 - Apagar conta \n3 - Ranking \n4 - Sair");
            if (ctrl.isAdmin(logado)) {
                System.out.println("5 - Listar usuários \n6 - Editar usuário \n7 - Remover usuário");
            }
            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Novo nome: ");
                    String nn = sc.nextLine();
                    System.out.print("Nova senha: ");
                    String ns = sc.nextLine();
                    if (ctrl.atualizarUsuario(logado, nn, ns)) {
                        System.out.println("Atualizado!");
                        logado = ctrl.logar(nn, ns);
                    } else {
                        System.out.println("Nome já existe.");
                    }
                    break;
                case 2:
                    ctrl.deletarProprioUsuario(logado);
                    System.out.println("Conta apagada.");
                    sair = true;
                    break;
                case 3:
                    System.out.println(ctrl.gerarRanking());
                    break;
                case 4:
                    sair = true;
                    break;
                case 5:
                    if (ctrl.isAdmin(logado)) {
                        for (Usuario u : ctrl.listarTodosUsuarios())
                            System.out.printf("- %s | %s | %d\n", u.getNome(), u.getSenha(), u.getPontuacao());
                    }
                    break;
                case 6:
                    if (ctrl.isAdmin(logado)) {
                        System.out.print("Usuário atual: ");
                        String atual = sc.nextLine();
                        System.out.print("Novo nome: ");
                        String novo = sc.nextLine();
                        System.out.print("Nova senha: ");
                        String ns2 = sc.nextLine();
                        System.out.print("Nova pontuação: ");
                        int pts = sc.nextInt(); sc.nextLine();
                        if (ctrl.adminAtualizarUsuario(atual, novo, ns2, pts)) System.out.println("Editado.");
                        else System.out.println("Erro!");
                    }
                    break;
                case 7:
                    if (ctrl.isAdmin(logado)) {
                        System.out.print("Nome do usuário: ");
                        String nomeDel = sc.nextLine();
                        if (ctrl.adminRemoverUsuario(nomeDel)) System.out.println("Removido.");
                        else System.out.println("Erro!");
                    }
                    break;
            }
        }

        sc.close();
         */

    }

    public void cadastrar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor insira o nome a ser cadastrado: ");
        String nome = scanner.nextLine();
        System.out.println("Por favor insira a senha a ser cadastrada: ");
        String senha = scanner.nextLine();
        loginController.cadastrar(nome, senha, false);
        menuEntrada();
    }

    public void logar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor insira o nome da conta: ");
        String nome = scanner.nextLine();
        System.out.print("Por favor insira a senha da conta: ");
        String senha = scanner.nextLine();
        if(loginController.logar(nome, senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Login inválido!");
            menuEntrada();
        };
    }

    public void intro() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        do {
            try {
                System.out.println("O que gostaria de jogar?\n1 - Multijogador \n2 - Sair");
                opcao = scanner.nextInt();
                if(opcao == 1) {
                    multijogador();
                } else if (opcao == 2) {
                    System.exit(0);
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 1 && opcao != 2);
    }

    public void multijogador() throws InterruptedException {
        Tabuleiro tabuleiro1 = new Tabuleiro("Padrão1", 10);
        TabuleiroView tabuleiroView1 = new TabuleiroView();
        TabuleiroController tabuleiroController1 = new TabuleiroController(tabuleiro1, tabuleiroView1);
        Tabuleiro tabuleiro2 = new Tabuleiro("Padrão2", 10);
        TabuleiroView tabuleiroView2 = new TabuleiroView();
        TabuleiroController tabuleiroController2 = new TabuleiroController(tabuleiro2, tabuleiroView2);
        Jogador jogador1 = new Jogador();
        Jogador jogador2 = new Jogador();
        System.out.println("\nJogador 1: Posicione suas embarcações.");
        jogador1.posicionar(tabuleiro1, tabuleiroController1, tabuleiroView1);
        Thread.sleep(500);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nJogador 2: Posicione suas embarcações.");
        jogador2.posicionar(tabuleiro2, tabuleiroController2, tabuleiroView2);
        Thread.sleep(500);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        do {
            System.out.println("Jogador 1: Ataque uma posição.");
            jogador1.atacar(tabuleiro2, tabuleiroController2, tabuleiroView2);
            Thread.sleep(500);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Jogador 2: Posicione suas embarcações.");
            jogador2.atacar(tabuleiro1, tabuleiroController1, tabuleiroView1);
            Thread.sleep(500);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        } while(jogador1.getPontos() < 19 || jogador2.getPontos() < 19);
    }
}
