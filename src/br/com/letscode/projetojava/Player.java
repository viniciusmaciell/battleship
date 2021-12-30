package br.com.letscode.projetojava;

import java.util.Scanner;

public abstract class Player {

    //ataca navio adversario
    //nome
    String nome;
    int naviosAbatidos;

    Integer[] coordenasdaDoTiro = new Integer[2];
    final int QUANTIDADE_NAVIOS = 10;
    String[][] naviosPosicionados = new String[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];
    String[][] registroTirosJogador = new String[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];


    public Player(String nome) {

        this.nome = nome;
        this.naviosAbatidos = 0;
        inicializarGrelha();
        inicializarRegistroTiros();
    }


    public void inicializarGrelha() {

        final int DIMENSAO_TABULEIRO = 10;

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                this.naviosPosicionados[i][j] = " ";
            }
        }
        Grelha.atualizarGrelha(naviosPosicionados);
    }

    public void inicializarRegistroTiros() {

        final int DIMENSAO_TABULEIRO = 10;

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                this.registroTirosJogador[i][j] = " ";
            }
        }
        Grelha.atualizarGrelha(registroTirosJogador);
    }
//    public void posicionarNavios() {
//
//        Scanner input = new Scanner(System.in);
//
//        String coordenadas = " ";
//        String[] coordenadasInformadas = new String[2];
//        Character L, C = ' ';
//        String linhas = "ABCDEFGHIJ";
//
//        int linha, coluna, navios = 0;
//
//        while (navios < QUANTIDADE_NAVIOS) {
//
//            System.out.printf("%nPosicione o %dº navio ( L - C ): ", navios + 1);
//
//            coordenadas = input.next();
//            L = coordenadas.toUpperCase().charAt(0);
//            C = coordenadas.charAt(1);
//
//            coordenadasInformadas[0] = L.toString();
//            coordenadasInformadas[1] = C.toString();
//
//            if (linhas.contains(coordenadasInformadas[0])) {
//                linha = linhas.indexOf(coordenadasInformadas[0]);
//                coluna = Integer.parseInt(coordenadasInformadas[1]);
//                this.naviosPosicionados[linha][coluna] = "N";
//                navios++;
//            }
//            Grelha.imprimirGrelha(naviosPosicionados);
//        }
////    }
    public void posicionarNavios() {

        int[] coordenadasInformadas = new int[2];

        int navios = 0;

        while (navios < 3) {

            String mensagem = "%nPosicione o " +(navios + 1)+ "º navio ( L - C ): ";
            coordenadasInformadas = escolherCoordenadas(mensagem);

                if (naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] == " ") {
                    this.naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] = "N";
                    navios++;
                    Grelha.atualizarGrelha(naviosPosicionados);
                } else {
                    System.out.println("Digite uma coordenada inexistente");
                }
            }
            Grelha.atualizarGrelha(naviosPosicionados);
        }

    public abstract int[] escolherCoordenadas(String mensagem);

    public void atacarNavio(String[][] grelhaAdversario) {

        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas = new int[2];
        int linha = 0;
        int coluna = 0;
        String linhas = "ABCDEFGHIJ";


        while (posicaoDisponivel) {
            String mensagem = "%nEscolha uma posição na grelha ( L - C ): ";
            coordenadasInformadas = escolherCoordenadas(mensagem);

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] != " ") {
                System.out.println(this.registroTirosJogador[linha][coluna]);
                System.err.println("Você já atirou nesta posição, tente outra !");
            } else {
                this.registroTirosJogador[linha][coluna] = "s";
                this.coordenasdaDoTiro[0] = linha;
                this.coordenasdaDoTiro[1] = coluna;
                posicaoDisponivel = false;

            }

        }


        if (grelhaAdversario[linha][coluna] == "N") {
            if (this.naviosPosicionados[linha][coluna] == "N") {
                this.naviosPosicionados[linha][coluna] = "X";
            } else {
                this.naviosPosicionados[linha][coluna] = "*";
                this.naviosAbatidos++;
            }
            grelhaAdversario[linha][coluna] = " ";
            System.out.printf("Você acertou um navio adversário !");

        } else if (grelhaAdversario[linha][coluna] == " ") {
            if (this.naviosPosicionados[linha][coluna] == "N") {
                this.naviosPosicionados[linha][coluna] = "n";
            } else {
                this.naviosPosicionados[linha][coluna] = "-";
            }
            System.out.printf("Tiro na água !");

        }

        Grelha.atualizarGrelha(this.naviosPosicionados);
    }
}
