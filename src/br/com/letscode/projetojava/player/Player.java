package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Grid;
import br.com.letscode.projetojava.Markers;

import java.util.Random;
import java.util.Scanner;

public abstract class Player {

    public String nome;
    public int quantidadeDeNavios;
    public int naviosAbatidos;

    public final int TOTAL_NAVIOS = 10;

    Integer[] coordenasdaDoTiro = new Integer[2];
    public String[][] naviosPosicionados = new String[TOTAL_NAVIOS][TOTAL_NAVIOS];
    String[][] registroTirosJogador = new String[TOTAL_NAVIOS][TOTAL_NAVIOS];

    public Player() {
        this.naviosAbatidos = 0;
        setNome();
        inicializarGrelha(this.naviosPosicionados);
        inicializarGrelha(this.registroTirosJogador);
    }

    public void setNome() {
        Scanner inputNome = new Scanner(System.in);
        System.out.printf("Informe seu nome: ");
        this.nome = inputNome.next().toUpperCase();
    }

    public void inicializarGrelha(String[][] grelha) {
        for (int i = 0; i < TOTAL_NAVIOS; i++) {
            for (int j = 0; j < TOTAL_NAVIOS; j++) {
                grelha[i][j] = " ";
            }
        }
    }

    public abstract void atacarNavio(Player adversario);

    public int[] gerarCoordenadasAuto() {
        int[] coordenadasComputador = new int[2];
        Random aleatorio = new Random();

        coordenadasComputador[0] = aleatorio.nextInt(10);
        coordenadasComputador[1] = aleatorio.nextInt(10);

        return coordenadasComputador;
    }

    public void posicionarNaviosAuto() {

        int[] coordenadasInformadas;

        while (this.quantidadeDeNavios < this.TOTAL_NAVIOS) {
            coordenadasInformadas = gerarCoordenadasAuto();
            if (posicaoDisponivelParaNavio(coordenadasInformadas)) {
                adicionarNavio(coordenadasInformadas);
            }
        }

        System.out.printf("%n");

        if (this instanceof UserPlayer) {
            Grid.imprimirGrelha(this.naviosPosicionados, this.nome);
        }
    }

    public boolean posicaoDisponivelParaNavio(int[] coordenadas) {
        return this.naviosPosicionados[coordenadas[0]][coordenadas[1]] == Markers.AGUA.toString();
    }

    public void adicionarNavio(int[] coordenadas) {
        this.naviosPosicionados[coordenadas[0]][coordenadas[1]] = Markers.NAVIO.toString();
        this.quantidadeDeNavios++;
    }

}

