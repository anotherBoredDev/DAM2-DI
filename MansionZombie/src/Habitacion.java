import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private final int numero;
    private final int intentosBusqueda = 3;
    private final List<Zombie> zombies = new ArrayList<>();

    public Habitacion(int numero) {
        this.numero = numero;
        zombies.add(generarZombie(numero));
    }

    private Zombie generarZombie(int numeroHabitacion) {
        int vida = Dado.tirarDado(0, 1) + 2 + (numeroHabitacion - 1);
        int fuerza = Dado.tirarDado(0, 1) + 2 + (numeroHabitacion - 1);

        return new Zombie(vida, fuerza);
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
}
