package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Colors;
import br.com.letscode.projetojava.Game;
import br.com.letscode.projetojava.Grelha;

import java.util.Scanner;

public class UserPlayer extends Player {

    public UserPlayer() {
        Grelha.imprimirGrelha(this.naviosPosicionados, this.nome);
    }

    public void posicionarManual() {
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
                System.err.println(Colors.MAGENTA + "Ops! Você já posicionou um navio aqui, tente outra coordenada ...");
            }
        }
    }

    public void posicionarNavios() {

        Scanner input = new Scanner(System.in);
        Integer resposta = 0;

        System.out.printf("%n%s, como gostaria de posicionar seus navios ?%n" +
                "[1] - Manual \t [2] - Automático ", this.nome);

        resposta = input.nextInt();

        if (resposta == 1) {
            posicionarManual();

        } else if (resposta == 2) {
            posicionarNaviosAuto();

        }
    }

    @Override
    public void atacarNavio(Player adversario) {
        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas = {0, 0};
        int linha;
        int coluna;

        while (posicaoDisponivel) {
            System.out.printf("%nInforme as coordenadas para o TIRO [ L - C ]: ");
            coordenadasInformadas = escolherCoordenadas();

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] != " ") {
                System.err.println(Colors.MAGENTA + "Você já atirou nesta posição, tente outra !");
            } else {
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


