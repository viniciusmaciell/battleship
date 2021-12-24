package br.com.letscode.projetojava;

import java.util.Scanner;

public class Player {

    //posiciona navios
    //ataca navio adversario
    //nome
    String nome;
    int naviosRestantes;
    int naviosAbatidos;

    final int QUANTIDADE_NAVIOS = 10;
    Integer[][] naviosPosicionados = new Integer[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];
    Integer[][] registroTirosJogador = new Integer[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];

    public Player(String nome) {
        this.nome = nome;
        this.naviosRestantes = 10;
        this.naviosAbatidos = 0;
    }

    public void gerarGrelhaVazia() {



        final int DIMENSAO_TABULEIRO = 10;

        Integer[][] grelha = new Integer[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                grelha[i][j] = 0;
            }
        }
        this.naviosPosicionados = grelha;
    }

    public void posicionarNavios() {



        Scanner entradaCoordenada = new Scanner(System.in);
        String[] coordenadas = new String[2];
        int linha = 0;
        int coluna = 0;

        final int larguraGrelha = 10;

//        String[] linhas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String linhas = "ABCDEFGHIJ";

        int navios = 0;

        while (navios < 3) {

            System.out.printf("Escolha uma posição na grelha pra atirar (L C): ");

            coordenadas[0] = entradaCoordenada.next().toUpperCase();
            coordenadas[1] = entradaCoordenada.next();


            linha = linhas.indexOf(coordenadas[0]);


            coluna = Integer.parseInt(coordenadas[1]);

            this.naviosPosicionados[linha][coluna] = 1;

            navios += 1;

            System.out.printf("Escolha uma posição na grelha pra atirar (L C): ");
        }

    }


}


