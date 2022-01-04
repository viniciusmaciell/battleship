package br.com.letscode.projetojava;

import br.com.letscode.projetojava.player.ComputerPlayer;
import br.com.letscode.projetojava.player.Player;
import br.com.letscode.projetojava.player.UserPlayer;

import java.util.Scanner;

public class Game {

//    public static void main(String[] args) {
//
//        UserPlayer jogador = new UserPlayer();
//        jogador.posicionarNavios();
//
//
//        ComputerPlayer pc = new ComputerPlayer();
//        pc.posicionarNavios();
//
//        while (jogador.naviosAbatidos < jogador.TOTAL_NAVIOS && pc.naviosAbatidos < pc.TOTAL_NAVIOS) {
//
//            jogador.atacarNavio(pc.naviosPosicionados);
//
//            pc.atacarNavio(jogador.naviosPosicionados);
//
//            Game.exibirPlacar(jogador, pc);
//        }
//    }

    public static void exibirPlacar(UserPlayer jogador, ComputerPlayer computador) {

        System.out.printf("%nPLACAR%n%s %d x %d %s%n", jogador.nome, jogador.naviosAbatidos,
                computador.naviosAbatidos, computador.nome);
    }

    public static void inicializarGame() {

        System.out.println("Bem vindo ao jogo BATALHA NAVAL");

    }

    public static void executarGame() {
        inicializarGame();

        UserPlayer jogador = new UserPlayer();
        ComputerPlayer computador = new ComputerPlayer();


        jogador.posicionarNavios();
        computador.posicionarNavios();

        while (jogador.naviosAbatidos < jogador.TOTAL_NAVIOS &&
                computador.naviosAbatidos < computador.TOTAL_NAVIOS) {

            jogador.atacarNavio(computador.naviosPosicionados);
            if (jogador.naviosAbatidos == jogador.TOTAL_NAVIOS)
                break;

            computador.atacarNavio(jogador.naviosPosicionados);

            if (computador.naviosAbatidos == computador.TOTAL_NAVIOS)
                break;

            Game.exibirPlacar(jogador, computador);
            Grelha.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);
        }

        Game.exibirResultadosFinais(jogador, computador);

    }

    public static void exibirResultadosFinais(UserPlayer jogador, ComputerPlayer computador) {
        if (jogador.naviosAbatidos == jogador.TOTAL_NAVIOS) {
            System.out.printf("%nParábens! Você venceu o Jogo.%n");
        } else {
            System.out.printf("%nO Computador venceu ...");
        }

        Grelha.mostrarTracos();

        System.out.printf("%nRESULTADOS FINAIS%n");

        Game.exibirPlacar(jogador, computador);

        System.out.printf("%n");

        Grelha.imprimirGrelha(jogador.naviosPosicionados, jogador.nome);

        Grelha.imprimirGrelha(computador.naviosPosicionados, computador.nome);


    }
}
