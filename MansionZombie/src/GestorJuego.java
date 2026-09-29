public class GestorJuego {
    private EstadoJuego estado;
    private Superviviente superviviente;
    private Habitacion[] habitaciones;
    private int habitacionActualIndex;

    public GestorJuego() {
        this.superviviente = new Superviviente();
        this.habitaciones = new Habitacion[0];
    }

    public void actualizarEstado() {
        if (superviviente.getVida() <= 0) {
            estado = EstadoJuego.PERDIDO;
        } else if (habitacionActualIndex >= habitaciones.length) {
            estado = EstadoJuego.GANADO;
        } else if (!habitaciones[habitacionActualIndex].getZombies().isEmpty()) {
            estado = EstadoJuego.COMBATE;
        } else {
            estado = EstadoJuego.EXPLORACION;
        }
    }

    public void iniciarJuego(int numeroHabitaciones) {
        habitaciones = new Habitacion[numeroHabitaciones];

        for (int i = 0; i < numeroHabitaciones; i++) {
            habitaciones[i] = new Habitacion(i + 1);
        }

        habitacionActualIndex = 0;

        actualizarEstado();
    }

    public void ejecutarTurnoAtaque() {

    }

    public void intentarCurarSuperviviente() {

    }

    public void buscarHabitacion() {

    }

    public void avanzarSiguienteHabitacion() {

    }

    public EstadoJuego getEstado() {
        return estado;
    }

    public Superviviente getSuperviviente() {
        return superviviente;
    }

    public int getHabitacionActualIndex() {
        return habitacionActualIndex;
    }

    public int getCantidadZombiesEnHabitacionActual() {
        return habitaciones[habitacionActualIndex].getZombies().size();
    }
}
