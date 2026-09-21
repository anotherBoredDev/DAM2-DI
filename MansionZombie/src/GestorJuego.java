import java.util.List;

public class GestorJuego {
    private Superviviente superviviente;
    private Habitacion[] habitaciones;
    private int habitacionActual;

    public GestorJuego() {
        this.superviviente = new Superviviente();
        this.habitaciones = new Habitacion[0];
    }

    public void prepararJuego(int numeroHabitaciones) {
        habitaciones = new Habitacion[numeroHabitaciones];
        for (int i = 0; i < numeroHabitaciones; i++) {
            habitaciones[i] = new Habitacion(i + 1);
        }
        habitacionActual = 0;
    }

    public boolean intentarAvanzarHabitacion() {
        habitacionActual += 1;
        return habitaciones.length > habitacionActual;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public List<Zombie> getZombiesEnHabitacion() {
        return habitaciones[habitacionActual].getZombies();
    }

    public boolean tieneHabitacionZombies() {
        return getZombiesEnHabitacion().isEmpty();
    }

    public Superviviente getSuperviviente() {
        return superviviente;
    }

    public boolean estaSupervivienteVivo() {
        return superviviente.estaVivo();
    }

    // Devuelve true si el combate ha sido exitoso y el superviviente ha sobrevivido, false si el superviviente ha muerto
    public boolean procesarTurnoCombate() {
        Zombie zombieObjetivo = getZombiesEnHabitacion().getFirst();
        boolean zombieDerrotado = superviviente.atacar(zombieObjetivo);
        if (zombieDerrotado) {
            getZombiesEnHabitacion().remove(zombieObjetivo);
            return true;
        }
        return !zombieObjetivo.atacar(superviviente);
    }
}
