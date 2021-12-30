package br.com.letscode.projetojava;

import br.com.letscode.projetojava.Player;

import java.util.Scanner;

public class UserPlayer extends Player {

    public UserPlayer(String nome) {
        super(nome);
    }
    public int[] escolherCoordenadas(String mensagem){
        Scanner input = new Scanner(System.in);
        String linhas = "ABCDEFGHIJ";
        String coordenadas = " ";
        String[] coordenadasRecebidas = new String[2];
        int[] coordenadasInformadas = new int[2];
        Character L, C = ' ';
        int linha;
        int coluna;

        do{
            System.out.printf(mensagem);

            coordenadas = input.next();
            L = coordenadas.toUpperCase().charAt(0);
            C = coordenadas.charAt(1);

            coordenadasRecebidas[0] = L.toString();
            coordenadasRecebidas[1] = C.toString();

            coluna = Integer.parseInt(coordenadasRecebidas[1]);

        }while(!linhas.contains(coordenadasRecebidas[0]) || coluna < 0 || coluna > 9);

        linha = linhas.indexOf(coordenadasRecebidas[0]);

        coordenadasInformadas[0] = linha;
        coordenadasInformadas[1] = coluna;

        return coordenadasInformadas;

    }
}
