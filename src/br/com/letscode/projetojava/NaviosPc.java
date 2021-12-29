package br.com.letscode.projetojava;

import java.util.Random;

public class NaviosPc {
    public static void main(String[] args) {
        int[] coordenadasPc = new int[2];

        Random aleatorio = new Random();
        Integer[][] naviosPosicionados = new Integer[10][10];
        int naviosPc = 0;
        int linha, coluna;

        while (naviosPc < 10) {
            coordenadasPc[0] = aleatorio.nextInt(10);
            coordenadasPc[1] = aleatorio.nextInt(10);

            linha = coordenadasPc[0];
            coluna = coordenadasPc[1];

            if (naviosPosicionados[linha][coluna] == null) {
                naviosPosicionados[linha][coluna] = 1;
                naviosPc++;
            }
        }

        for (int i = 0; i < naviosPosicionados.length; i++) {
            for (int j = 0; j < naviosPosicionados.length; j++) {
                System.out.println(naviosPosicionados[i][j]);
            }
        }
    }
}
