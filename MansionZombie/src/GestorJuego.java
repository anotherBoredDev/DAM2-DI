import java.util.List;

public class GestorJuego {
    public enum EstadosJuego {
        PREPARANDO,
        JUGANDO,
        GANADO,
        PERDIDO,
    }

    private EstadosJuego estado;
    private Superviviente superviviente;
    private Habitacion[] habitaciones;
    private int habitacionActualIndex;

    public GestorJuego() {
        this.superviviente = new Superviviente();
        this.habitaciones = new Habitacion[0];
        this.estado = EstadosJuego.PREPARANDO;
    }

    public void prepararJuego(int numeroHabitaciones) {
        habitaciones = new Habitacion[numeroHabitaciones];
        for (int i = 0; i < numeroHabitaciones; i++) {
            habitaciones[i] = new Habitacion(i + 1);
        }
        habitacionActualIndex = 0;
        estado = EstadosJuego.JUGANDO;
    }

    // Devuelve true si el combate ha sido exitoso y el superviviente ha sobrevivido, false si el superviviente ha muerto
    public ResultadoTurno procesarTurnoCombate() {
        if (habitacionActualVacia()) {
            return new ResultadoTurno(0, "No hay zombies en esta habitación");
        }

        Zombie zombie = getZombiesEnHabitacion().getFirst();
        int danioRealizado = superviviente.atacar(zombie);
        return new ResultadoTurno(danioRealizado, String.format("Realizas %d de daño a %s", danioRealizado, zombie.getNombre()));
    }

    public void buscarHabitacion() {

    }

    public void intentarAvanzarHabitacion() {
        habitacionActualIndex += 1;
        if (habitacionActualIndex > habitaciones.length) {
            estado = EstadosJuego.GANADO;
        }
    }

    public boolean habitacionActualVacia() {
        return habitaciones[habitacionActualIndex].getZombies().isEmpty();
    }

    public EstadosJuego getEstado() {
        return estado;
    }

    public Superviviente getSuperviviente() {
        return superviviente;
    }

    public int getHabitacionActualIndex() {
        return habitacionActualIndex;
    }

    private List<Zombie> getZombiesEnHabitacion() {
        return habitaciones[habitacionActualIndex].getZombies();
    }

}
