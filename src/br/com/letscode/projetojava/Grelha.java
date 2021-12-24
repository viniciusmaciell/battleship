package br.com.letscode.projetojava;

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

    public static String[][] gerarGrelhaVazia() {

        final int DIMENSAO_TABULEIRO = 10;

        String[][] grelha = new String[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                grelha[i][j] = " ";
            }
        }
        return grelha;
    }

    public static void  mostrarGrelha() {

        String[][] grelha = gerarGrelhaVazia();
        String[] linhas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        Scanner input = new Scanner(System.in);
        String jogada = " ";

        mostrarCabecalho("jogador");

        for (int i = 0; i < grelha.length; i++) {
            for (int j = 0; j < grelha.length; j++) {
                grelha[i][j] = jogada;
            }
        }
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
