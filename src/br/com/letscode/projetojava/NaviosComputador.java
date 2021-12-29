package br.com.letscode.projetojava;

import java.util.Random;

public class NaviosComputador {
    final int QUANTIDADE_NAVIOS = 10;
    String[][] naviosPosicionados = new String[QUANTIDADE_NAVIOS][QUANTIDADE_NAVIOS];

    public void inicializarGrelha() {
        final int DIMENSAO_TABULEIRO = 10;
        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                this.naviosPosicionados[i][j] = " ";
            }
        }
    }

    public void posicionarNaviosPc() {
        int[] coordenadasPc = new int[2];
        Random aleatorio = new Random();
        int naviosPc = 0;
        int linha, coluna;

        while (naviosPc < 3) {
            coordenadasPc[0] = aleatorio.nextInt(10);
            coordenadasPc[1] = aleatorio.nextInt(10);

            linha = coordenadasPc[0];
            coluna = coordenadasPc[1];

            if (naviosPosicionados[linha][coluna] == " ") {
                naviosPosicionados[linha][coluna] = String.valueOf("N");
                naviosPc++;
            }
            Grelha.atualizarGrelha(naviosPosicionados);
        }
    }
}