package br.com.letscode.projetojava;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
       Player jogador = new Player("Jogador");
       jogador.inicializarGrelha();
       jogador.posicionarNavios();


//
//        jogador1.gerarGrelhaVazia();
//
//        for (int i = 0; i < jogador1.naviosPosicionados.length; i++) {
//            for (int j = 0; j <jogador1.naviosPosicionados.length ; j++) {
//                System.out.println(jogador1.naviosPosicionados[i][j]);
//            }
//
//        }
//
//        jogador1.posicionarNavios();
//        for (int i = 0; i < jogador1.naviosPosicionados.length; i++) {
//            for (int j = 0; j <jogador1.naviosPosicionados.length ; j++) {
//                System.out.println(jogador1.naviosPosicionados[i][j]);
//            }
//
//        }


//        Scanner input = new Scanner(System.in);
//        String coordenadas = " ";
//        String[] arrayCoordenadas = new String[2];
//        Character L, C = ' ';
//        int linha, coluna = 0;
//
//        final int larguraGrelha = 10;
//
////        String[] linhas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
//        String linhas = "ABCDEFGHIJ";
//        Integer[][] naviosPosicionados = new Integer[10][10];
//        int navios = 0;
//
//        while (navios < 3) {
//
//            System.out.printf("Escolha uma posição (L C): ");
//
//            coordenadas = input.next();
//            L = coordenadas.toUpperCase().charAt(0);
//            C = coordenadas.charAt(1);
//
//            arrayCoordenadas[0] = L.toString().toUpperCase();
//            arrayCoordenadas[1] = C.toString();
//
//
//            if (linhas.contains(arrayCoordenadas[0])) {
//                linha = linhas.indexOf(arrayCoordenadas[0]);
//                coluna = Integer.parseInt(arrayCoordenadas[1]);
//                naviosPosicionados[linha][coluna] = 1;
//                navios++;
//            }
//
//        }
//
//        for (int i = 0; i < naviosPosicionados.length; i++) {
//            for (int j = 0; j < naviosPosicionados.length; j++) {
//                System.out.println(naviosPosicionados[i][j]);
//            }
//        }


        public void atacar(Player jogadorAtacado){
            String[] posicaoDoTiro = escolherCoordenadas();
            Game.avaliarTiro(posicaoDoTiro, this, jogadorAtacado);
        }

        public void avaliarTiro(String[] posicaoDoTiro, Player jogadorAtacante, Player jogadorAtacado){
            String linhas = "ABCDEFGHIJ";
            int linha = linhas.indexOf(posicaoTiro[0]);
            int coluna = Integer.parseInt(coordenadasInformadas[1]);
            //com navio posicionado
            if(jogadorAtacante.grelha[linha][coluna] == "N"){
                if (jogadorAtacado.grelha[linha][coluna] == "N" || jogadorAtacado.grelha[linha][coluna] == "n"){
                    //tiro certeiro
                    jogadorAtacante.grelha[linha][coluna] = "X";
                    removerNavioAtacado(jogadorAtacado, linha, coluna);
                    System.out.println("Você acertou um navio!");
                }
                else{
                    //tiro na água
                    jogadorAtacante.grelha[linha][coluna] = "n";
                    System.out.println("Seu tiro foi na água!");
                }
            }
            //sem navio posicionado
            else{
                if (jogadorAtacado.grelha[linha][coluna] == "N" || jogadorAtacado.grelha[linha][coluna] == "n"){
                    //tiro certeiro
                    jogadorAtacante.grelha[linha][coluna] = "*";
                    removerNavioAtacado(jogadorAtacado, linha, coluna);
                    System.out.println("Você acertou um navio!");
                }
                else{
                    //tiro na água com navio posicionado
                    jogadorAtacante.grelha[linha][coluna] = "-";
                    System.out.println("Seu tiro foi na água!");
                }
            }

        }

        public void removerNavioAtacado(Player jogadorAtacado, int linha, int coluna){
            if (jogadorAtacado.grelha[linha][coluna] == "N"){
                jogadorAtacado.grelha[linha][coluna] = " ";
            }
            else{
                jogadorAtacado.grelha[linha][coluna] = "-";
            }
        }
    }
}
