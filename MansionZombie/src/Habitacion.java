import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private final int numero;
    private final int intentosBusqueda = 3;
    private final List<Zombie> zombies;

    public Habitacion(int numero) {
        this.numero = numero;
        this.zombies = new ArrayList<>();
        generarZombie();
    }

    private void generarZombie() {
        String nombre = "Zombie " + (zombies.size() + 1);
        int vida = Dado.tirarDado(0, 1) + 2 + (numero - 1);
        int fuerza = Dado.tirarDado(0, 1) + 2 + (numero - 1);

        Zombie zombie = new Zombie(nombre, vida, fuerza);

        zombies.add(zombie);
    }

    public int getNumero() {
        return numero;
    }

    public int getIntentosBusqueda() {
        return intentosBusqueda;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
}
