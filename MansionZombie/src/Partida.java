public class Partida {
    private final GestorJuego gestorJuego;
    private final LectorConsola lectorConsola;

    public Partida(GestorJuego gestorJuego, LectorConsola lectorConsola) {
        this.gestorJuego = gestorJuego;
        this.lectorConsola = lectorConsola;
    }

    public void iniciar() {
        mostrarMenuDificultad();
        int dificultad = lectorConsola.leerEnteroEnRango("Opción", 1, 2);
        gestorJuego.seleccionarDificultad(dificultad);
    }

    private void imprimirSeparador() {
        System.out.println("----------------------------------------");
    }

    private void mostrarMenuDificultad() {
        imprimirSeparador();
        System.out.println("BIENVENIDO A LA MANSIÓN ZOMBIE");
        System.out.println("Elige dificultad:");
        System.out.println("1. Fácil (5 Habitaciones)");
        System.out.println("2. Difícil (10 Habitaciones)");
        imprimirSeparador();
    }
}
