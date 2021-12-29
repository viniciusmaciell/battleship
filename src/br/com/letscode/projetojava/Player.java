package br.com.letscode.projetojava;

import java.util.Scanner;

public class Player {


    //ataca navio adversario
    //nome
    String nome;
    int naviosRestantes;
    int naviosAbatidos;


    final int QUANTIDADE_NAVIOS = 10;
    String[][] naviosPosicionados = new String[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];
    String[][] registroTirosJogador = new String[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];

    public Player(String nome) {

        this.nome = nome;
        this.naviosRestantes = 10;
        this.naviosAbatidos = 0;
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

    public void posicionarNavios() {


//        Scanner input = new Scanner(System.in);
//
//        String coordenadas = " ";
        String[] coordenadasInformadas = new String[2];
//        Character L, C = ' ';
        String linhas = "ABCDEFGHIJ";

        int linha, coluna, navios = 0;

        while (navios < 3) {

            coordenadasInformadas = escolherCoordenadas();

//            System.out.printf("%nEscolha uma posição na grelha ( L - C ): ");
//
//            coordenadas = input.next();
//            L = coordenadas.toUpperCase().charAt(0);
//            C = coordenadas.charAt(1);
//
//            coordenadasInformadas[0] = L.toString();
//            coordenadasInformadas[1] = C.toString();

            if (linhas.contains(coordenadasInformadas[0])) {
                linha = linhas.indexOf(coordenadasInformadas[0]);
                coluna = Integer.parseInt(coordenadasInformadas[1]);
                this.naviosPosicionados[linha][coluna] = "N";
                navios++;
            }
            Grelha.atualizarGrelha(naviosPosicionados);
        }
    }
    public String[] escolherCoordenadas(){
        Scanner input = new Scanner(System.in);

        String coordenadas = " ";
        String[] coordenadasInformadas = new String[2];
        Character L, C = ' ';

        System.out.printf("%nEscolha uma posição na grelha ( L - C ): ");

        coordenadas = input.next();
        L = coordenadas.toUpperCase().charAt(0);
        C = coordenadas.charAt(1);

        coordenadasInformadas[0] = L.toString();
        coordenadasInformadas[1] = C.toString();

        return coordenadasInformadas;

    }

    public void atacarNavio(String[][] grelhaAdversario) {

        boolean posicaoDisponivel = false;

        Scanner input = new Scanner(System.in);
        String coordenadas = " ";
        String[] coordenadasInformadas = new String[2];
        Character L, C = ' ';
        String linhas = "ABCDEFGHIJ";

        int linha = 0;
        int coluna = 0;


        while (posicaoDisponivel) {

            System.out.printf("Escolha uma posição para atirar ( L - C ): ");

            coordenadas = input.next();
            L = coordenadas.toUpperCase().charAt(0);
            C = coordenadas.charAt(1);

            coordenadasInformadas[0] = L.toString();
            coordenadasInformadas[1] = C.toString();

            linha = linhas.indexOf(coordenadasInformadas[0]);
            coluna = Integer.parseInt(coordenadasInformadas[1]);

            if (this.tirosRegistrados[linha][coluna] != " ") {
                System.err.println("Você já atirou nesta posição, tente outra !");
            } else {
                this.tirosRegistrados[linha][coluna] = "s";
                this.coordenasdaDoTiro[0] = linha;
                this.coordenasdaDoTiro[1] = coluna;
                posicaoDisponivel = true;

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

        Grelha.imprimirGrelha(this.naviosPosicionados);
    }
}
