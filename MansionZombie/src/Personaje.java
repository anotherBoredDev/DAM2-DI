public abstract class Personaje {
    private final String nombre;
    private final int vidaMax;
    private int vida;
    private final int fuerza;

    public Personaje(String nombre, int vidaMax, int fuerza) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.fuerza = fuerza;
        this.vida = vidaMax;
    }

    public int atacar(Personaje objetivo) {
        int fuerzaFinal = Dado.tirarDado(fuerza);
        objetivo.recibirDanio(fuerzaFinal);
        return fuerzaFinal;
    }

    public boolean recibirDanio(int cantidad) {
        int cantidadFinal = Math.max(0, cantidad);
        vida = Math.max(vida - cantidadFinal, 0);
        return vida == 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.min(vida, vidaMax);
    }

    public int getFuerza() {
        return fuerza;
    }

}
