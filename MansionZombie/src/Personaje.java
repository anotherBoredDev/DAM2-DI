public abstract class Personaje {
    private final int vidaMax;
    private final int fuerza;
    private int vidaActual;

    public Personaje(int vidaMax, int fuerza) {
        this.vidaMax = vidaMax;
        this.fuerza = fuerza;
        this.vidaActual = vidaMax;
    }

    public boolean atacar(Personaje objetivo) {
        int fuerzaFinal = Dado.tirarDado(fuerza);
        return objetivo.recibirDanio(fuerzaFinal);
    }

    public boolean recibirDanio(int cantidad) {
        int cantidadFinal = Math.max(0, cantidad);
        vidaActual = Math.max(vidaActual - cantidadFinal, 0);
        return vidaActual == 0;
    }

    public void curar(int cantidad) {
        vidaActual = Math.min(vidaActual + cantidad, vidaMax);
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getFuerza() {
        return fuerza;
    }

    public boolean estaVivo() {
        return vidaActual != 0;
    }
}
