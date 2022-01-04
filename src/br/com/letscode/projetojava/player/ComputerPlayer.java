package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Grelha;

import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {

    }


    @Override
    public void setNome() {
        this.nome = "Computador";
    }


    @Override
    public int[] escolherCoordenadas() {
        int[] coordenadasComputador = new int[2];
        Random aleatorio = new Random();

        coordenadasComputador[0] = aleatorio.nextInt(10);
        coordenadasComputador[1] = aleatorio.nextInt(10);

        return coordenadasComputador;
    }

    @Override
    public void posicionarNavios() {

        int[] coordenadasInformadas;

        int navios = 0;

        while (navios < this.TOTAL_NAVIOS) {

            coordenadasInformadas = escolherCoordenadas();

            if (naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] == " ") {
                this.naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] = "N";
                navios++;
            }
        }
        System.out.printf("%n");
        Grelha.imprimirGrelha(naviosPosicionados, nome);
    }

    @Override
    public void atacarNavio(String[][] grelhaAdversario) {

        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas;
        int linha = 0;
        int coluna = 0;

        while (posicaoDisponivel) {
            coordenadasInformadas = escolherCoordenadas();

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] == " ") {
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
            System.out.printf("O computador abateu um navio seu !");

        } else if (grelhaAdversario[linha][coluna] == " ") {
            if (this.naviosPosicionados[linha][coluna] == "N") {
                this.naviosPosicionados[linha][coluna] = "n";
            } else {
                this.naviosPosicionados[linha][coluna] = "-";
            }
            System.out.printf("%nO Computador errou o tiro.");

        }

    }
}