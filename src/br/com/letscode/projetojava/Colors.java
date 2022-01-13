package br.com.letscode.projetojava;

public enum Colors {

    RESET("\033[0m"),

    // Regular Colors. Normal color, no bold, background color etc.
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE

    // Bold
    BLACK_BOLD("\033[1;30m"),   // BLACK
    GREEN_BOLD("\033[1;32m"),   // GREEN
    MAGENTA_BOLD("\033[1;35m"), // MAGENTA

    // Background
    RED_BACKGROUND("\033[41m"),     // RED
    GREEN_BACKGROUND("\033[42m"),   // GREEN
    BLUE_BACKGROUND("\033[44m"),    // BLUE
    CYAN_BACKGROUND("\033[46m"),    // CYAN
    WHITE_BACKGROUND("\033[47m"),   // WHITE
    RESET_BACKGROUND("\033[0m"),   // WHITE

    // High Intensity
    RED_BRIGHT("\033[0;91m"),       // RED
    YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
    WHITE_BRIGHT("\033[0;97m"),     // WHITE

    // Bold High Intensity
    RED_BOLD_BRIGHT("\033[1;91m"),      // RED
    GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
    WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

    // High Intensity backgrounds
    WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
