import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private final int numero;
    private final int intentosBusqueda = 3;
    private final List<Zombie> zombies;

    public Habitacion(int numero) {
        this.numero = numero;
        this.zombies = new ArrayList<>();
        this.zombies.add(generarZombie(this.numero));
    }

    private Zombie generarZombie(int numeroHabitacion) {
        String nombre = "Zombie " + (zombies.size() + 1);
        int vida = Dado.tirarDado(0, 1) + 2 + (numeroHabitacion - 1);
        int fuerza = Dado.tirarDado(0, 1) + 2 + (numeroHabitacion - 1);

        return new Zombie(nombre, vida, fuerza);
    }

    public int getNumero() {
        return numero;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
}
