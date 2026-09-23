public abstract class Personaje {
    private final String nombre;
    private final int vidaMax;
    private final int fuerza;
    private int vidaActual;

    public Personaje(String nombre, int vidaMax, int fuerza) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.fuerza = fuerza;
        this.vidaActual = vidaMax;
    }

    public int atacar(Personaje objetivo) {
        int fuerzaFinal = Dado.tirarDado(fuerza);
        objetivo.recibirDanio(fuerzaFinal);
        return fuerzaFinal;
    }

    public boolean recibirDanio(int cantidad) {
        int cantidadFinal = Math.max(0, cantidad);
        vidaActual = Math.max(vidaActual - cantidadFinal, 0);
        return vidaActual == 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = Math.min(vidaActual, vidaMax);
    }

    public int getFuerza() {
        return fuerza;
    }

}
