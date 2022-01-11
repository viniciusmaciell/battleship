package br.com.letscode.projetojava;

public enum Modificadores {

    NAVIO("N"),
    AGUA(" "),
    TIRO_NA_AGUA("-"),
    TIRO_NO_NAVIO("*"),
    TIRO_NO_NAVIO_NAVIO("X"),
    TIRO_NA_AGUA_NAVIO("n");

    private final String nome;

    Modificadores(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
