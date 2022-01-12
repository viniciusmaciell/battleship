package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Colors;
import br.com.letscode.projetojava.Game;
import br.com.letscode.projetojava.Grelha;
import br.com.letscode.projetojava.Modificadores;

import java.util.Scanner;

public class UserPlayer extends Player {

    public UserPlayer() {
        Grelha.imprimirGrelha(this.naviosPosicionados, this.nome);
    }

    public void posicionarManual() {
        int[] coordenadasInformadas;

        while (this.quantidadeDeNavios < this.TOTAL_NAVIOS) {

            String mensagem = "%nPosicione seu %dº navio [ linha coluna ] : %n" + this.quantidadeDeNavios + 1;
            coordenadasInformadas = escolherCoordenadas(mensagem);
            adicionarNavio(coordenadasInformadas);
            Grelha.imprimirGrelha(this.naviosPosicionados, nome);

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
            String mensagem = "Informe as coordenadas para o TIRO [ L - C ]: ";
            coordenadasInformadas = escolherCoordenadas(mensagem);
            System.out.printf("%n");

            linha = coordenadasInformadas[0];
            coluna = coordenadasInformadas[1];

            if (this.registroTirosJogador[linha][coluna] != " ") {
                System.err.println(Colors.MAGENTA + "Você já atirou nesta posição, tente outra!");
            } else {
                this.registroTirosJogador[linha][coluna] = "shot";
                this.coordenasdaDoTiro[0] = linha;
                this.coordenasdaDoTiro[1] = coluna;
                posicaoDisponivel = false;
            }
        }

        Game.avaliarTiro(coordenadasInformadas, this, adversario);
        System.out.printf("%n");
        Grelha.imprimirGrelha(this.naviosPosicionados, this.nome);
        System.out.printf("%n");
    }


}


