package br.com.letscode.projetojava;

import br.com.letscode.projetojava.player.ComputerPlayer;
import br.com.letscode.projetojava.player.Player;
import br.com.letscode.projetojava.player.UserPlayer;

import java.util.Scanner;

public class Game {

    public static void executarGame() {
        Scanner input = new Scanner(System.in);
        boolean continuarJogando = true;
        int resposta;

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
            }

            Game.exibirResultadosFinais(jogador, computador);


            System.out.printf("%nDeseja jogar novamente ?%n [1] - Continuar \t [2]- Parar %n");
            resposta = input.nextInt();
            if (resposta == 1) {
                continuarJogando = true;

            } else {
                continuarJogando = false;
                System.out.println("Até a próxima !");
            }
        }
    }

    public static void exibirPlacar(UserPlayer jogador, ComputerPlayer computador) {

        System.out.printf("%nPLACAR%n%s %d x %d %s%n", jogador.nome, jogador.naviosAbatidos,
                computador.naviosAbatidos, computador.nome);
    }

    public static void inicializarGame() {

        System.out.println("Bem vindo ao jogo BATALHA NAVAL");

    }

    public static void exibirResultadosFinais(UserPlayer jogador, ComputerPlayer computador) {
        if (jogador.naviosAbatidos == jogador.TOTAL_NAVIOS) {
            System.out.printf("%nParábens! Você venceu o Jogo.%n");
        } else {
            System.out.printf("%nO Computador venceu ...%n");
        }

        Grelha.mostrarTracos();

        System.out.printf("%nRESULTADOS FINAIS%n");

        Game.exibirPlacar(jogador, computador);

        System.out.printf("%n");

        Grelha.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);

        Grelha.imprimirGrelha(computador.naviosPosicionados, computador.nome);

    }

    public static void avaliarTiro(int[] posicaoDoTiro, Player jogadorAtacante, Player jogadorAtacado) {
        int linha = posicaoDoTiro[0];
        int coluna = posicaoDoTiro[1];

        if (jogadorAtacado.naviosPosicionados[linha][coluna] == "N" ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == "n" ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == "X") {

            if (jogadorAtacante.naviosPosicionados[linha][coluna] == "N" ||
                    jogadorAtacante.naviosPosicionados[linha][coluna] == "n") {

                jogadorAtacante.naviosPosicionados[linha][coluna] = "X";
            } else {
                jogadorAtacante.naviosPosicionados[linha][coluna] = "*";
//                jogadorAtacante.naviosAbatidos++;
            }
//            jogadorAtacado.naviosPosicionados[linha][coluna] = " ";
            jogadorAtacante.naviosAbatidos++;

            removerNavioAtacado(jogadorAtacado, linha, coluna);

            if (jogadorAtacante instanceof UserPlayer) {

                System.out.printf("%nNO ALVO !!!%nVocê ABATEU um navio do computador !%n");
            } else {
                System.out.printf("%n NAVIO AO MAR... O computador AFUNDOU um de seus navios. %n");
            }

        } else if (jogadorAtacado.naviosPosicionados[linha][coluna] == " " ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == "-" ||
                jogadorAtacado.naviosPosicionados[linha][coluna] == "*") {
            if (jogadorAtacante.naviosPosicionados[linha][coluna] == "N") {
                jogadorAtacante.naviosPosicionados[linha][coluna] = "n";
            } else {
                jogadorAtacante.naviosPosicionados[linha][coluna] = "-";
            }

            if (jogadorAtacante instanceof UserPlayer) {
                System.out.printf("%nSPLASH... Tiro na água.%n");
            } else {
                System.out.printf("%n O computador ERROU o tiro, sua vez...%n");
            }
        }
    }

    public static void removerNavioAtacado(Player jogadorAtacado, int linha, int coluna){
        if (jogadorAtacado.naviosPosicionados[linha][coluna] == "N") {
            jogadorAtacado.naviosPosicionados[linha][coluna] = " ";
        } else if (jogadorAtacado.naviosPosicionados[linha][coluna] == "n") {
            jogadorAtacado.naviosPosicionados[linha][coluna] = "-";
        } else {
            jogadorAtacado.naviosPosicionados[linha][coluna] = "*";
        }
    }
}
