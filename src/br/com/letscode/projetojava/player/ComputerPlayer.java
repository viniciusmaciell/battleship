package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Game;
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

        coordenadasComputador[0] = aleatorio.nextInt(2);
        coordenadasComputador[1] = aleatorio.nextInt(2);

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
    public void atacarNavio(Player adversario) {
        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas = {0,0};
        int linha;
        int coluna;

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

        Game.avaliarTiro(coordenadasInformadas, this, adversario);
        Grelha.imprimirGrelha(this.naviosPosicionados, nome);
    }
}