package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Grelha;

import java.util.Random;
import java.util.Scanner;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {

    }


    @Override
    public void setNome() {
        this.nome = "Computador";
    }


    @Override
    public int[] escolherCoordenadas() {
        int[] coordenadasPc = new int[2];
        Random aleatorio = new Random();

        coordenadasPc[0] = aleatorio.nextInt(10);
        coordenadasPc[1] = aleatorio.nextInt(10);

        return coordenadasPc;
    }

    @Override
    public void posicionarNavios() {

        int[] coordenadasInformadas = new int[2];

        int navios = 0;

        while (navios < 3) {

            coordenadasInformadas = escolherCoordenadas();

            if (naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] == " ") {
                this.naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] = "N";
                navios++;
            }
        }
        System.out.printf("%n");
        Grelha.atualizarGrelha(naviosPosicionados, nome);
    }

    @Override
    public void atacarNavio(String[][] grelhaAdversario) {

        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas = new int[2];
        int linha = 0;
        int coluna = 0;
        String linhas = "ABCDEFGHIJ";


        while (posicaoDisponivel) {
            coordenadasInformadas = escolherCoordenadas();

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] == " ") {
//                System.out.println(this.registroTirosJogador[linha][coluna]);
                this.registroTirosJogador[linha][coluna] = "s";
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
            System.out.printf("O computador abateu um navio seu !");

        } else if (grelhaAdversario[linha][coluna] == " ") {
            if (this.naviosPosicionados[linha][coluna] == "N") {
                this.naviosPosicionados[linha][coluna] = "n";
            } else {
                this.naviosPosicionados[linha][coluna] = "-";
            }
            System.out.printf("O computador atirou na água !");

        }

    }
}