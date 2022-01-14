package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Game;

public class ComputerPlayer extends Player {

    @Override
    public void setNome() {
        this.nome = "COMPUTADOR";
    }

    @Override
    public void atacarNavio(Player adversario) {
        boolean posicaoDisponivel = true;

        int[] coordenadasInformadas = new int[2];
        int linha;
        int coluna;

        while (posicaoDisponivel) {
            coordenadasInformadas = gerarCoordenadasAuto();

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] == " ") {
                this.registroTirosJogador[linha][coluna] = "shot";
                posicaoDisponivel = false;
            }
        }

        Game.avaliarTiro(coordenadasInformadas, this, adversario);
    }
}