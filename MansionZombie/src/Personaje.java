import java.util.Random;

public abstract class Personaje {
    private final Random random = new Random();
    private final String nombre;
    private final int vidaMax;
    private int vida;
    private int ataqueMax;

    public Personaje(String nombre, int vidaMax, int ataqueMax) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = this.vidaMax;
        this.ataqueMax = ataqueMax;
    }

    public void atacar(Personaje objetivo) {
        int ataque = random.nextInt(ataqueMax);
        String mensajeAtaque = String.format("%s ataca a %s.", nombre, objetivo.nombre);
        objetivo.recibirAtaque(ataque);
        System.out.println(mensajeAtaque);
    }

    public void recibirAtaque(int ataque) {
        String mensajeAtaqueRecibido = String.format("%s ha recibido %d de daño.", nombre, ataque);
        vida -= ataque;
        System.out.println(mensajeAtaqueRecibido);
    }

    public int getAtaque() {
        return ataqueMax;
    }
}
