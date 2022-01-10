package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Game;
import br.com.letscode.projetojava.Grelha;

import java.util.Random;

public class ComputerPlayer extends Player {

    @Override
    public void setNome() {
        this.nome = "COMPUTADOR";
    }

    @Override
    public void atacarNavio(Player adversario) {
        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas = {0,0};
        int linha;
        int coluna;

        while (posicaoDisponivel) {
            coordenadasInformadas = gerarCoordenadasAuto();

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
//        Grelha.imprimirGrelha(this.naviosPosicionados, nome);
    }
}