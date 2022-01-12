package br.com.letscode.projetojava;

public enum Modificadores {

//    NAVIO("N"),
//    AGUA(" "),
//    TIRO_NA_AGUA("-"),
//    TIRO_NO_NAVIO("*"),
//    TIRO_NO_NAVIO_NAVIO("X"),
//    TIRO_NA_AGUA_NAVIO("n");

    NAVIO(Colors.YELLOW_BRIGHT + "N" + Colors.RESET),
    AGUA(" "),
    TIRO_NA_AGUA(Colors.BLUE + "-" + Colors.RESET),
    TIRO_NO_NAVIO(Colors.RED + "*" + Colors.RESET),
    TIRO_NO_NAVIO_NAVIO(Colors.RED_BRIGHT + "X" + Colors.RESET),
    TIRO_NA_AGUA_NAVIO(Colors.YELLOW + "n" + Colors.RESET);

    private final String nome;

    Modificadores(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
