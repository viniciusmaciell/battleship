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
        String resposta = "0";

        while( resposta != "1" && resposta != "2") {
            System.out.printf("%n%s, como gostaria de posicionar seus navios ?%n" +
                    "[1] - Manual \t [2] - Automático ", this.nome);
            resposta = input.next();
            if (resposta.equals("1")) {
                posicionarManual();
                break;
            } else if (resposta.equals("2")) {
                posicionarNaviosAuto();
                break;
            }
        }
    }

    public int[] escolherCoordenadas(String mensagem) {
        Scanner input = new Scanner(System.in);
        String linhas = "ABCDEFGHIJ";
        String coordenadas = " ";
        String[] coordenadasRecebidas = new String[2];
        int[] coordenadasInformadas = new int[2];
        Character L, C = ' ';
        int linha;
        int coluna = 0;
        boolean coordenadaInvalida = true;
        boolean colunaInvalida = false;

        while(coordenadaInvalida){
            while (coordenadas.length() != 2) {
                System.out.println(mensagem);
//                System.out.printf("%nPosicione seu %dº navio [ linha coluna ] : %n", this.quantidadeDeNavios + 1);
                coordenadas = input.next();
            }

            L = coordenadas.toUpperCase().charAt(0);
            coordenadasRecebidas[0] = L.toString();

            if(!linhas.contains(coordenadasRecebidas[0])){
                System.out.println("linha invalida");
                coordenadas = " ";
                continue;
            }

            C = coordenadas.charAt(1);
            coordenadasRecebidas[1] = C.toString();

            try {
                coluna = Integer.parseInt(coordenadasRecebidas[1]);
            } catch (Exception NumberFormatException) {
                System.out.println("kaka");
                colunaInvalida = true;
            }

            if(colunaInvalida){
                System.out.println("coluna invalida");
                colunaInvalida = false;
                coordenadas = " ";
                continue;
            }

            linha = linhas.indexOf(coordenadasRecebidas[0]);

            coordenadasInformadas[0] = linha;
            coordenadasInformadas[1] = coluna;

            if (!posicaoDisponivelParaNavio(coordenadasInformadas) && this.quantidadeDeNavios != 2) {
                System.err.println(Colors.MAGENTA + "Ops! Você já posicionou um navio aqui, tente outra coordenada...");
                colunaInvalida = false;
                coordenadas = " ";
                continue;
            }

            coordenadaInvalida = false;
            System.out.println(coordenadaInvalida);
        }
        return coordenadasInformadas;
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


