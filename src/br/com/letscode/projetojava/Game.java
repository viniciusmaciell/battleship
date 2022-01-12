package br.com.letscode.projetojava;

import br.com.letscode.projetojava.player.ComputerPlayer;
import br.com.letscode.projetojava.player.Player;
import br.com.letscode.projetojava.player.UserPlayer;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {

    public static void executarGame() {
        Scanner input = new Scanner(System.in);
        boolean continuarJogando = true;
        String resposta = "0";

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

                Game.exibirPlacar(jogador, computador);
                Grelha.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);
                System.out.printf("%n");
            }

            exibirResultadosFinais(jogador, computador);

            while( resposta != "1" && resposta != "2") {
                System.out.printf("%nDeseja jogar novamente ?%n [1] - Continuar \t [2]- Parar %n");
                resposta = input.next();
                if (resposta.equals("1")) {
                    continuarJogando = true;
                    break;
                } else if (resposta.equals("2")){
                    continuarJogando = false;
                    System.out.println("Até a próxima! ");
                    break;
                }
            }

        }
    }

    public static void exibirPlacar(UserPlayer jogador, ComputerPlayer computador) {
        System.out.printf("%n");
        System.out.printf("%n");
        System.out.print(Colors.WHITE_BACKGROUND_BRIGHT);
        System.out.println(Colors.BLACK_BOLD + "   PLACAR   " + Colors.RESET_BACKGROUND);
        System.out.print(Colors.WHITE_BACKGROUND_BRIGHT);
        System.out.println(Colors.BLACK_BOLD + jogador.nome + " " + jogador.naviosAbatidos + " x " + computador.naviosAbatidos + " " + computador.nome + Colors.RESET_BACKGROUND);

        //System.out.printf("%nPLACAR%n%s %d x %d %s%n", jogador.nome, jogador.naviosAbatidos,computador.naviosAbatidos, computador.nome);
    }

    public static void inicializarGame() {

        System.out.println("\uD83D\uDEA2" + " Bem vindo ao jogo BATALHA NAVAL " + "\uD83D\uDEA2"	);

    }

    public static void exibirResultadosFinais(UserPlayer jogador, ComputerPlayer computador) {
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

        Grelha.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);
        Grelha.imprimirGrelha(computador.naviosPosicionados, computador.nome);


        //Game.exibirPlacar(jogador, computador);
        //System.out.printf("%n");
    }

    public static void avaliarTiro(int[] posicaoDoTiro, Player jogadorAtacante, Player jogadorAtacado) {
        int linha = posicaoDoTiro[0];
        int coluna = posicaoDoTiro[1];

        if (jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.NAVIO.toString() ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.TIRO_NA_AGUA_NAVIO.toString() ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.TIRO_NO_NAVIO_NAVIO.toString()) {

            if (jogadorAtacante.naviosPosicionados[linha][coluna] == Modificadores.NAVIO.toString() ||
                    jogadorAtacante.naviosPosicionados[linha][coluna] == Modificadores.TIRO_NA_AGUA_NAVIO.toString()) {

                jogadorAtacante.naviosPosicionados[linha][coluna] = Modificadores.TIRO_NO_NAVIO_NAVIO.toString();
            } else {
                jogadorAtacante.naviosPosicionados[linha][coluna] = Modificadores.TIRO_NO_NAVIO.toString();
            }
            jogadorAtacante.naviosAbatidos++;

            removerNavioAtacado(jogadorAtacado, linha, coluna);

            if (jogadorAtacante instanceof UserPlayer) {
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

        } else if (jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.AGUA.toString() ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.TIRO_NA_AGUA.toString() ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.TIRO_NO_NAVIO.toString()) {
            if (jogadorAtacante.naviosPosicionados[linha][coluna] == Modificadores.NAVIO.toString()) {
                jogadorAtacante.naviosPosicionados[linha][coluna] = Modificadores.TIRO_NA_AGUA_NAVIO.toString();
            } else {
                jogadorAtacante.naviosPosicionados[linha][coluna] = Modificadores.TIRO_NA_AGUA.toString();
            }

            if (jogadorAtacante instanceof UserPlayer) {
                System.out.print(Colors.BLACK_BOLD);
                System.out.print(Colors.CYAN_BACKGROUND);
                System.out.println("  \uD83D\uDCA6  SPLASH... \uD83D\uDCA6  " + Colors.RESET);
                System.out.print(Colors.WHITE_BOLD_BRIGHT);
                System.out.print(Colors.CYAN_BACKGROUND);
                System.out.print(" Tiro na água. " + Colors.RESET);
            } else {
                System.out.printf(Colors.CYAN + "%n O computador ERROU o tiro, sua vez..." + Colors.RESET);
            }
        }
    }

    public static void removerNavioAtacado(Player jogadorAtacado, int linha, int coluna){
        if (jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.NAVIO.toString()) {
            jogadorAtacado.naviosPosicionados[linha][coluna] = Modificadores.AGUA.toString();
        } else if (jogadorAtacado.naviosPosicionados[linha][coluna] == Modificadores.TIRO_NA_AGUA_NAVIO.toString()) {
            jogadorAtacado.naviosPosicionados[linha][coluna] = Modificadores.TIRO_NA_AGUA.toString();
        } else {
            jogadorAtacado.naviosPosicionados[linha][coluna] = Modificadores.TIRO_NO_NAVIO.toString();
        }
    }
}

