package br.com.letscode.projetojava;

import br.com.letscode.projetojava.player.ComputerPlayer;
import br.com.letscode.projetojava.player.Player;
import br.com.letscode.projetojava.player.UserPlayer;
import java.util.Scanner;

public class Game {

    public static void executarGame() {
        boolean continuarJogando = true;

        inicializarGame();

        while (continuarJogando) {
            UserPlayer jogador = new UserPlayer();
            ComputerPlayer computador = new ComputerPlayer();

            jogador.posicionarNavios();
            computador.posicionarNaviosAuto();

            while (jogador.naviosAbatidos < jogador.TOTAL_NAVIOS &&
                    computador.naviosAbatidos < computador.TOTAL_NAVIOS) {

                jogador.atacarNavio(computador);
                if (jogador.naviosAbatidos == jogador.TOTAL_NAVIOS) {
                    break;
                }

                computador.atacarNavio(jogador);
                if (computador.naviosAbatidos == computador.TOTAL_NAVIOS) {
                    break;
                }

                exibirPlacar(jogador, computador);
                Grid.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);
                System.out.printf("%n");
            }

            exibirResultadosFinais(jogador, computador);

            continuarJogando = jogarNovamente();
        }
    }

    public static boolean jogarNovamente() {
        String resposta = "0";
        boolean continuarJogo = false;
        Scanner input = new Scanner(System.in);

        while (resposta != "1" && resposta != "2") {
            System.out.printf("%nDeseja jogar novamente?%n [1] - Continuar \t [2]- Parar %n");
            resposta = input.next();
            if (resposta.equals("1")) {
                continuarJogo = true;
                break;
            } else if (resposta.equals("2")) {
                System.out.println("Até a próxima! ");
                continuarJogo = false;
                break;
            }
        }
        return continuarJogo;
    }

    public static void instrucoesGame(UserPlayer jogador) {
        Scanner input = new Scanner(System.in);
        String resposta = "0";

        while (resposta != "1" && resposta != "2") {
            System.out.printf("%n%s, você gostaria de instruções sobre o jogo?%n" +
                    "[1] - Sim \t [2] - Não ", jogador.nome);
            resposta = input.next();
            if (resposta.equals("1")) {
                System.out.printf("%n");
                System.out.print(Colors.WHITE_BOLD_BRIGHT);
                System.out.print(Colors.BLUE_BACKGROUND);
                System.out.println(" INSTRUÇÕES BATALHA NAVAL " + Colors.RESET);
                System.out.printf(Colors.WHITE_BRIGHT + "Para começar você deve distribuir 10 navios na grelha, podendo escolher as posições de forma manual ou automática. %n" +
                        Colors.WHITE_BRIGHT + "Seus movimentos no jogo serão representados da seguinte maneira:%n" + Colors.RESET +
                        Colors.WHITE_BOLD_BRIGHT + "Seus navios: " + Colors.RESET + Markers.NAVIO + " %n" +
                        Colors.WHITE_BOLD_BRIGHT + "Tiro na água: " + Colors.RESET + Markers.TIRO_NA_AGUA + " %n" +
                        Colors.WHITE_BOLD_BRIGHT + "Tiro na água com seu navio posicionado: " + Colors.RESET + Markers.TIRO_NA_AGUA_NAVIO + " %n" +
                        Colors.WHITE_BOLD_BRIGHT + "Tiro certeiro: " + Colors.RESET + Markers.TIRO_NO_NAVIO + " %n" +
                        Colors.WHITE_BOLD_BRIGHT + "Tiro certeiro com seu navio posicionado: " + Colors.RESET + Markers.TIRO_NO_NAVIO_NAVIO + " %n" +
                        Colors.WHITE_BRIGHT + "O primeiro a derrubar os 10 navios do oponente, vence o jogo. %n" +
                        Colors.GREEN_BOLD + " ☘    BOA SORTE    ☘ " + Colors.RESET + "%n"
                );
                break;
            } else if (resposta.equals("2")) {
                break;
            }
            input.close();
        }

    }

    private static void exibirPlacar(UserPlayer jogador, ComputerPlayer computador) {
        System.out.printf("%n");
        System.out.printf("%n");
        System.out.print(Colors.WHITE_BACKGROUND_BRIGHT);
        System.out.println(Colors.BLACK_BOLD + "   PLACAR   " + Colors.RESET_BACKGROUND);
        System.out.print(Colors.WHITE_BACKGROUND_BRIGHT);
        System.out.println(Colors.BLACK_BOLD + jogador.nome + " " + jogador.naviosAbatidos + " x " + computador.naviosAbatidos + " " + computador.nome + Colors.RESET_BACKGROUND);

    }

    private static void inicializarGame() {

        System.out.println("\uD83D\uDEA2" + " Bem vindo ao jogo BATALHA NAVAL " + "\uD83D\uDEA2"	);

    }

    private static void exibirResultadosFinais(UserPlayer jogador, ComputerPlayer computador) {
        if (jogador.naviosAbatidos == jogador.TOTAL_NAVIOS) {
            System.out.printf("%n %n");
            System.out.println(Colors.GREEN_BOLD_BRIGHT + "Parabéns! Você venceu o Jogo." + Colors.RESET);
        } else {
            System.out.printf("%n %n");
            System.out.println(Colors.RED_BOLD_BRIGHT + "O Computador venceu..." + Colors.RESET);
        }
        System.out.printf("%n");
        System.out.print(Colors.WHITE_BACKGROUND_BRIGHT);
        System.out.println(Colors.BLACK_BOLD + " RESULTADOS FINAIS " + Colors.RESET_BACKGROUND);
        System.out.println(Colors.WHITE_BRIGHT + jogador.nome + " " + jogador.naviosAbatidos + " x " + computador.naviosAbatidos + " " + computador.nome + Colors.RESET_BACKGROUND);

        Grid.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);
        Grid.imprimirGrelha(computador.naviosPosicionados, computador.nome);

    }

    public static void avaliarTiro(int[] posicaoDoTiro, Player atacante, Player adversario) {
        int linha = posicaoDoTiro[0];
        int coluna = posicaoDoTiro[1];

        if (adversario.naviosPosicionados[linha][coluna] == Markers.NAVIO.toString() ||
                adversario.naviosPosicionados[linha][coluna] == Markers.TIRO_NA_AGUA_NAVIO.toString() ||
                adversario.naviosPosicionados[linha][coluna] == Markers.TIRO_NO_NAVIO_NAVIO.toString()) {

            if (atacante.naviosPosicionados[linha][coluna] == Markers.NAVIO.toString() ||
                    atacante.naviosPosicionados[linha][coluna] == Markers.TIRO_NA_AGUA_NAVIO.toString()) {

                atacante.naviosPosicionados[linha][coluna] = Markers.TIRO_NO_NAVIO_NAVIO.toString();
            } else {
                atacante.naviosPosicionados[linha][coluna] = Markers.TIRO_NO_NAVIO.toString();
            }
            atacante.naviosAbatidos++;

            removerNavioAtacado(adversario, linha, coluna);

            if (atacante instanceof UserPlayer) {
                System.out.print(Colors.BLACK_BOLD);
                System.out.print(Colors.GREEN_BACKGROUND);
                System.out.println("  \uD83D\uDCA3  NO ALVO !!! \uD83D\uDCA5  " + Colors.RESET);
                System.out.print(Colors.WHITE_BOLD_BRIGHT);
                System.out.print(Colors.GREEN_BACKGROUND);
                System.out.print(" Você ABATEU um navio do computador! " + Colors.RESET);
            } else {
                System.out.printf("%n");
                System.out.print(Colors.BLACK_BOLD);
                System.out.print(Colors.RED_BACKGROUND);
                System.out.println("  \uD83D\uDCA3  NAVIO AO MAR... \uD83D\uDCA5  " + Colors.RESET);
                System.out.print(Colors.WHITE_BOLD_BRIGHT);
                System.out.print(Colors.RED_BACKGROUND);
                System.out.print(" O computador AFUNDOU um de seus navios. " + Colors.RESET);
            }

        } else if (adversario.naviosPosicionados[linha][coluna] == Markers.AGUA.toString() ||
                adversario.naviosPosicionados[linha][coluna] == Markers.TIRO_NA_AGUA.toString() ||
                adversario.naviosPosicionados[linha][coluna] == Markers.TIRO_NO_NAVIO.toString()) {
            if (atacante.naviosPosicionados[linha][coluna] == Markers.NAVIO.toString()) {
                atacante.naviosPosicionados[linha][coluna] = Markers.TIRO_NA_AGUA_NAVIO.toString();
            } else {
                atacante.naviosPosicionados[linha][coluna] = Markers.TIRO_NA_AGUA.toString();
            }

            if (atacante instanceof UserPlayer) {
                System.out.print(Colors.BLACK_BOLD);
                System.out.print(Colors.CYAN_BACKGROUND);
                System.out.println("  \uD83D\uDCA6  SPLASH... Tiro na água. \uD83D\uDCA6  " + Colors.RESET);

            } else {
                System.out.printf("%n");
                System.out.print(Colors.BLACK_BOLD);
                System.out.print(Colors.CYAN_BACKGROUND);
                System.out.print("  \uD83D\uDCA6  O computador ERROU o tiro. \uD83D\uDCA6  " + Colors.RESET);
            }
        }
    }

    private static void removerNavioAtacado(Player jogadorAtacado, int linha, int coluna) {
        if (jogadorAtacado.naviosPosicionados[linha][coluna] == Markers.NAVIO.toString()) {
            jogadorAtacado.naviosPosicionados[linha][coluna] = Markers.AGUA.toString();
        } else if (jogadorAtacado.naviosPosicionados[linha][coluna] == Markers.TIRO_NA_AGUA_NAVIO.toString()) {
            jogadorAtacado.naviosPosicionados[linha][coluna] = Markers.TIRO_NA_AGUA.toString();
        } else {
            jogadorAtacado.naviosPosicionados[linha][coluna] = Markers.TIRO_NO_NAVIO.toString();
        }
    }
}

