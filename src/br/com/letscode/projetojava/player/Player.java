package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Grelha;

import java.util.Scanner;

public abstract class Player {

    public String nome;
    public int naviosAbatidos;
    public int naviosRestantes;

    final int DIMENSAO_TABULEIRO = 10;
    public final int TOTAL_NAVIOS = 2;

    Integer[] coordenasdaDoTiro = new Integer[2];
    public String[][] naviosPosicionados = new String[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];
    String[][] registroTirosJogador = new String[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];

    public Player() {

        this.naviosAbatidos = 0;
        this.naviosRestantes = 10;
        setNome();
        inicializarGrelha(this.naviosPosicionados);
        inicializarGrelha(this.registroTirosJogador);
//        Grelha.imprimirGrelha(this.naviosPosicionados,this.nome);

    }

    public void setNome() {
        Scanner inputNome = new Scanner(System.in);
        System.out.printf("Informe seu nome: ");
        this.nome = inputNome.next().toUpperCase();
    }

    public void inicializarGrelha(String[][] grelha) {

        final int DIMENSAO_TABULEIRO = 10;

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                grelha[i][j] = " ";
            }
        }
    }

    public void posicionarNavios() {
        int[] coordenadasInformadas;

        int navios = 0;

        while (navios < this.TOTAL_NAVIOS) {

            System.out.printf("%nPosicione seu %dº navio [ linha coluna ] :", navios + 1);
            coordenadasInformadas = escolherCoordenadas();

            if (this.naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] == " ") {
                this.naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] = "N";
                navios++;
                Grelha.imprimirGrelha(this.naviosPosicionados, nome);
            } else {
                System.err.println("Ops! Você já posicionou um navio aqui, tente outra coordenada ...");
            }
        }
    }

    public abstract int[] escolherCoordenadas();

    public void atacarNavio(String[][] grelhaAdversario) {

        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas;
        int linha = 0;
        int coluna = 0;
//        String linhas = "ABCDEFGHIJ";


        while (posicaoDisponivel) {
            System.out.printf("%nInforme as coordenadas para o TIRO [ L - C ]: ");
            coordenadasInformadas = escolherCoordenadas();

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] != " ") {
//                System.out.println(this.registroTirosJogador[linha][coluna]);
                System.err.println("Você já atirou nesta posição, tente outra !");
            } else {
                this.registroTirosJogador[linha][coluna] = "shot";
                this.coordenasdaDoTiro[0] = linha;
                this.coordenasdaDoTiro[1] = coluna;
                posicaoDisponivel = false;
            }
        }

        if (grelhaAdversario[linha][coluna] == "N" || grelhaAdversario[linha][coluna] == "n") {
            if (this.naviosPosicionados[linha][coluna] == "N" || this.naviosPosicionados[linha][coluna] == "n") {
                this.naviosPosicionados[linha][coluna] = "X";
            } else {
                this.naviosPosicionados[linha][coluna] = "*";
                this.naviosAbatidos++;
            }
            grelhaAdversario[linha][coluna] = " ";
            System.out.printf("%nNO ALVO !!!%nVocê ABATEU um navio adversário !%n");

        } else if (grelhaAdversario[linha][coluna] == " ") {
            if (this.naviosPosicionados[linha][coluna] == "N") {
                this.naviosPosicionados[linha][coluna] = "n";
            } else {
                this.naviosPosicionados[linha][coluna] = "-";
            }
            System.out.printf("%nSPLASH... Tiro na água.%n");

        }

//        Grelha.imprimirGrelha(this.naviosPosicionados, nome);
    }

    //    public void inicializarRegistroTiros() {
//
//        final int DIMENSAO_TABULEIRO = 10;
//
//        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
//            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
//                this.registroTirosJogador[i][j] = " ";
//            }
//        }
////        Grelha.imprimirGrelha(registroTirosJogador, nome);
//    }
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
//        while (navios < DIMENSAO_TABULEIRO) {
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
}
