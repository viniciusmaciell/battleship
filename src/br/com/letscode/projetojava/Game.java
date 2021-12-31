package br.com.letscode.projetojava;

import br.com.letscode.projetojava.player.ComputerPlayer;
import br.com.letscode.projetojava.player.Player;
import br.com.letscode.projetojava.player.UserPlayer;

public class Game {
    public static void main(String[] args) {

       UserPlayer jogador = new UserPlayer();
       jogador.posicionarNavios();

       ComputerPlayer pc = new ComputerPlayer();
       pc.posicionarNavios();

       while (jogador.naviosAbatidos < 3 && pc.naviosAbatidos < 3 ){

           jogador.atacarNavio(pc.naviosPosicionados);
           System.out.println("navios abatidos do pc: " + pc.naviosAbatidos);
           pc.atacarNavio(jogador.naviosPosicionados);
           System.out.println("navios abatidos do jogador: " + jogador.naviosAbatidos);
       }
       System.out.printf("Resultado final");
       Grelha.atualizarGrelha(jogador.naviosPosicionados,jogador.nome);
       Grelha.atualizarGrelha(pc.naviosPosicionados, pc.nome);

    }
}
