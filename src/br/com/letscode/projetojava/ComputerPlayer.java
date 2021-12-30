package br.com.letscode.projetojava;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String nome) {
        super(nome);
    }

    @Override
    public int[] escolherCoordenadas(String mensagem) {
        int[] coordenadasPc = new int[2];
        return coordenadasPc;
    }
}


//        int[] coordenadasPc = new int[2];
//        Random aleatorio = new Random();
//        int naviosPc = 0;
//        int linha, coluna;
//
//        while (naviosPc < 3) {
//        coordenadasPc[0] = aleatorio.nextInt(10);
//        coordenadasPc[1] = aleatorio.nextInt(10);
//
//        linha = coordenadasPc[0];
//        coluna = coordenadasPc[1];
//
//        if (naviosPosicionados[linha][coluna] == " ") {
//        naviosPosicionados[linha][coluna] = String.valueOf("N");
//        naviosPc++;
//        }
