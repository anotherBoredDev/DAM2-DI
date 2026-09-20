
public class Main {
    public static void main(String[] args) {
        GestorJuego gestorJuego = new GestorJuego();
        LectorConsola lectorConsola = new LectorConsola();
        Partida partida = new Partida(gestorJuego, lectorConsola);

        partida.iniciar();
    }
}