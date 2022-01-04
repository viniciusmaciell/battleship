package br.com.letscode.projetojava;

import br.com.letscode.projetojava.player.Player;
import br.com.letscode.projetojava.player.UserPlayer;

import java.util.Scanner;

public class Grelha {

    public static void mostrarCabecalho(String nome) {

        final int QUANTIDADE_TRACOS = 67;
        final int POSICIONAR_NOME = (QUANTIDADE_TRACOS - nome.length()) / 2;

        String nomeJogador = nome.toUpperCase();

        mostrarTracos();

        System.out.printf("%n");

        for (int i = 0; i < POSICIONAR_NOME; i++) {
            System.out.printf(" ");

        }
        System.out.printf("%s%n", nomeJogador);

        mostrarTracos();
        final int DIMENSAO_TABULEIRO = 10;

        System.out.printf("%n|     |");

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            System.out.printf("  %d  |", i);
        }

        System.out.printf("%n");

        mostrarTracos();
    }

    public static void mostrarTracos() {
        final int QUANTIDADE_TRACOS = 67;

        for (int i = 0; i < QUANTIDADE_TRACOS; i++) {
            System.out.printf("-");
        }
    }

    public static void atualizarGrelha(String[][] grelha) {

        String[] linhas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        mostrarCabecalho("jogador");

        for (int i = 0; i < grelha.length; i++) {
            System.out.printf("%n|  %s  |", linhas[i]);
            for (int j = 0; j < grelha.length; j++) {
                System.out.printf("  %s  |", grelha[i][j]);
            }
            System.out.printf("%n");

            mostrarTracos();

        }

    }
}
