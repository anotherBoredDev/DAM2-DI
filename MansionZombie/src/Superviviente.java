public class Superviviente extends Personaje {
    private boolean tieneBotiquin = false;
    private int cantidadArmas = 0;
    private int cantidadProtecciones = 0;

    public Superviviente(String nombre, int vidaMax, int ataque) {
        super(nombre, vidaMax, ataque);
    }

    @Override
    public void atacar(Personaje objetivo) {
        int ataqueFinal = getAtaque() + cantidadArmas;
        super.atacar(objetivo);
    }

    @Override
    public void recibirAtaque(int ataque) {

        super.recibirAtaque(ataque);
    }
}
