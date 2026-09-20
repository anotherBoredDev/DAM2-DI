public class GestorJuego {
    private Habitacion[] habitaciones;

    public void seleccionarDificultad(int dificultad) {
        switch (dificultad) {
            case 1 -> inicializarJuegoFacil();
            case 2 -> inicializarJuegoDificil();
            default -> System.out.println("Error: Valor de dificultad incorrecto (1-2)");
        }
    }

    private void inicializarJuegoFacil() {
        int numeroHabitaciones = 5;
        habitaciones = new Habitacion[numeroHabitaciones];
        for (int i = 0; i < numeroHabitaciones; i++) {
            habitaciones[i] = new Habitacion(i + 1);
        }
    }

    private void inicializarJuegoDificil() {
        int numeroHabitaciones = 10;
        habitaciones = new Habitacion[numeroHabitaciones];
        for (int i = 0; i < numeroHabitaciones; i++) {
            habitaciones[i] = new Habitacion(i + 1);
        }
    }
}
