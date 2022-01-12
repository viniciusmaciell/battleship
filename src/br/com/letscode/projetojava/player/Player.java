package br.com.letscode.projetojava.player;

import br.com.letscode.projetojava.Colors;
import br.com.letscode.projetojava.Grelha;
import br.com.letscode.projetojava.Modificadores;

import java.util.Random;
import java.util.Scanner;

public abstract class Player {

    public String nome;
    public int quantidadeDeNavios;
    public int naviosAbatidos;
    public int naviosRestantes;

    final int DIMENSAO_TABULEIRO = 10;
    public final int TOTAL_NAVIOS = 2;

    Integer[] coordenasdaDoTiro = new Integer[2];
    public String[][] naviosPosicionados = new String[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];
    String[][] registroTirosJogador = new String[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];

    public Player() {

        this.naviosAbatidos = 0;
        this.naviosRestantes = 10;
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

//        final int DIMENSAO_TABULEIRO = 10;

        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                grelha[i][j] = " ";
            }
        }
    }

    public abstract void atacarNavio(Player adversario);

    public int[] gerarCoordenadasAuto() {
        int[] coordenadasComputador = new int[2];
        Random aleatorio = new Random();

        coordenadasComputador[0] = aleatorio.nextInt(2);
        coordenadasComputador[1] = aleatorio.nextInt(2);

        return coordenadasComputador;
    }

    public int[] escolherCoordenadas() {
        Scanner input = new Scanner(System.in);
        String linhas = "ABCDEFGHIJ";
        String coordenadas = " ";
        String[] coordenadasRecebidas = new String[2];
        int[] coordenadasInformadas = new int[2];
        Character L, C = ' ';
        int linha;
        int coluna;

        do {
            coordenadas = input.next();
            L = coordenadas.toUpperCase().charAt(0);
            C = coordenadas.charAt(1);

            coordenadasRecebidas[0] = L.toString();
            coordenadasRecebidas[1] = C.toString();

            coluna = Integer.parseInt(coordenadasRecebidas[1]);

            linha = linhas.indexOf(coordenadasRecebidas[0]);

            coordenadasInformadas[0] = linha;
            coordenadasInformadas[1] = coluna;

            return coordenadasInformadas;

        } while (!linhas.contains(coordenadasRecebidas[0]) || coluna < 0 || coluna > 9);
    }

    public void posicionarNaviosAuto() {

        int[] coordenadasInformadas;

//        int navios = 0;

        while (this.quantidadeDeNavios < this.TOTAL_NAVIOS) {

            coordenadasInformadas = gerarCoordenadasAuto();
//            if (naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] == Modificadores.AGUA.toString()) {
//                this.naviosPosicionados[coordenadasInformadas[0]][coordenadasInformadas[1]] = Modificadores.NAVIO.toString();
//                navios++;
//            }

            if (posicaoDisponivelParaNavio(coordenadasInformadas)) {
                adicionarNavio(coordenadasInformadas);
            }
        }
        System.out.printf("%n");
        if (this instanceof UserPlayer) {
            Grelha.imprimirGrelha(this.naviosPosicionados, this.nome);
        }
    }

    public boolean posicaoDisponivelParaNavio(int[] coordenadas){
        return this.naviosPosicionados[coordenadas[0]][coordenadas[1]] == Modificadores.AGUA.toString();
    }

    public void adicionarNavio(int[] coordenadas){
        this.naviosPosicionados[coordenadas[0]][coordenadas[1]] = Modificadores.NAVIO.toString();
        this.quantidadeDeNavios++;
    }

}

