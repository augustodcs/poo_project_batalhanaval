package br.edu.up.BatalhaNaval.views;
import java.util.List;
import java.util.Scanner;

import controller.LoginController;
import controller.TabuleiroController;
import model.Tabuleiro;
import model.Usuario;
import repository.TabuleiroRepository;
import repository.UsuarioRepository;

public class MainConsoleView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoginController loginController = new LoginController(new repository.UsuarioRepository());
    private static final TabuleiroController tabuleiroController = new TabuleiroController();

    public static void main(String[] args) {
        loginController.cadastrar("Admin", "0000", true);

        while (true) {
            System.out.println("==== MEGA SHIP BATTLE ====");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    if (loginController.logar(nome, senha)) {
                        menuUsuario();
                    } else {
                        System.out.println("Login inválido.");
                        pausar();
                    }
                }
                case "2" -> {
                    System.out.print("Novo nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String senha = scanner.nextLine();
                    if (loginController.cadastrar(nome, senha, false)) {
                        System.out.println("Usuário cadastrado.");
                    } else {
                        System.out.println("Nome já está em uso.");
                    }
                    pausar();
                }
                case "0" -> System.exit(0);
                default -> {
                    System.out.println("Opção inválida.");
                    pausar();
                }
            }
        }
    }

    private static void menuUsuario() {
        Usuario logado = loginController.getUsuarioLogado();
        while (logado != null) {
            System.out.println("Bem-vindo, " + logado.getNome());
            System.out.println("1 - Criar Tabuleiro");
            System.out.println("2 - Meus Tabuleiros");
            System.out.println("3 - Ranking");
            System.out.println("4 - Alterar Nome");
            System.out.println("5 - Alterar Senha");
            System.out.println("6 - Excluir Conta");
            System.out.println("7 - Logout");

            if (logado.isAdmin()) {
                System.out.println("8 - Listar Usuários");
                System.out.println("9 - Editar Usuário");
                System.out.println("10 - Excluir Usuário");
            }

            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Nome do tabuleiro: ");
                    String nome = scanner.nextLine();
                    System.out.print("Largura: ");
                    int largura = Integer.parseInt(scanner.nextLine());
                    System.out.print("Altura: ");
                    int altura = Integer.parseInt(scanner.nextLine());
                    tabuleiroController.criarTabuleiro(nome, largura, altura, logado.getNome());
                    System.out.println("Tabuleiro criado.");
                }
                case "2" -> {
                    List<Tabuleiro> meus = TabuleiroRepository.listarPorUsuario(logado.getNome());
                    if (meus.isEmpty()) System.out.println("Você não tem tabuleiros.");
                    else meus.forEach(System.out::println);
                }
                case "3" -> {
                    List<Tabuleiro> todos = TabuleiroRepository.listarTodos();
                    todos.sort((a, b) -> Integer.compare(b.getPontuacao(), a.getPontuacao()));
                    System.out.println("RANKING:");
                    todos.forEach(System.out::println);
                }
                case "4" -> {
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    if (UsuarioRepository.carregarUsuarios().stream().anyMatch(u -> u.getNome().equals(novoNome))) {
                        System.out.println("Nome já está em uso.");
                    } else {
                        logado.setNome(novoNome);
                        UsuarioRepository.salvarUsuarios(UsuarioRepository.carregarUsuarios());
                        System.out.println("Nome alterado.");
                    }
                }
                case "5" -> {
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    logado.setSenha(novaSenha);
                    UsuarioRepository.salvarUsuarios(UsuarioRepository.carregarUsuarios());
                    System.out.println("Senha alterada.");
                }
                case "6" -> {
                    System.out.print("Confirma exclusão? (s/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("s")) {
                        if (logado.getNome().equalsIgnoreCase("Admin")) {
                            System.out.println("Não é possível excluir o Admin.");
                        } else {
                            List<Usuario> usuarios = UsuarioRepository.carregarUsuarios();
                            usuarios.removeIf(u -> u.getNome().equals(logado.getNome()));
                            UsuarioRepository.salvarUsuarios(usuarios);
                            System.out.println("Conta excluída.");
                            loginController.deslogar();
                            return;
                        }
                    }
                }
                case "7" -> {
                    loginController.deslogar();
                    return;
                }
                case "8" -> {
                    if (logado.isAdmin()) {
                        List<Usuario> todos = UsuarioRepository.carregarUsuarios();
                        todos.forEach(u -> System.out.println("Usuário: " + u.getNome() + (u.isAdmin() ? " [Admin]" : "")));
                    }
                }
                case "9" -> {
                    if (logado.isAdmin()) {
                        System.out.print("Nome do usuário a editar: ");
                        String nome = scanner.nextLine();
                        List<Usuario> usuarios = UsuarioRepository.carregarUsuarios();
                        for (Usuario u : usuarios) {
                            if (u.getNome().equals(nome)) {
                                System.out.print("Novo nome: ");
                                String novoNome = scanner.nextLine();
                                System.out.print("Nova senha: ");
                                String novaSenha = scanner.nextLine();
                                u.setNome(novoNome);
                                u.setSenha(novaSenha);
                                UsuarioRepository.salvarUsuarios(usuarios);
                                System.out.println("Usuário atualizado.");
                                break;
                            }
                        }
                    }
                }
                case "10" -> {
                    if (logado.isAdmin()) {
                        System.out.print("Nome do usuário a excluir: ");
                        String nome = scanner.nextLine();
                        if (nome.equalsIgnoreCase("Admin")) {
                            System.out.println("Não é possível excluir o Admin.");
                        } else {
                            List<Usuario> usuarios = UsuarioRepository.carregarUsuarios();
                            boolean removido = usuarios.removeIf(u -> u.getNome().equals(nome));
                            UsuarioRepository.salvarUsuarios(usuarios);
                            if (removido) System.out.println("Usuário excluído.");
                            else System.out.println("Usuário não encontrado.");
                        }
                    }
                }
                default -> System.out.println("Opção inválida.");
            }

            pausar();
        }
    }

    private static void pausar() {
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}
