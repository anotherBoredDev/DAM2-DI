public class Superviviente extends Personaje {
    private boolean tieneBotiquin = false;
    private int cantidadArmas = 0;
    private int cantidadProtecciones = 0;

    public Superviviente() {
        super("Superviviente", 20, 4); // Puntos de vida y fuerza base.
    }

    @Override
    public int atacar(Personaje objetivo) {
        int fuerzaFinal = Dado.tirarDado(getFuerza()) + cantidadArmas;
        objetivo.recibirDanio(fuerzaFinal);
        return fuerzaFinal;
    }

    @Override
    public boolean recibirDanio(int cantidad) {
        return super.recibirDanio(cantidad - cantidadProtecciones);
    }

    public void curar(int cantidad) {
        setVidaActual(getVidaActual() + cantidad);
    }

    public boolean usarBotiquin() {
        if (!tieneBotiquin) {
            return false;
        }
        tieneBotiquin = false;
        curar(4);
        return true;
    }

    public boolean intentarConseguirBotiquin() {
        if (tieneBotiquin) {
            return false;
        }
        tieneBotiquin = true;
        return true;
    }

    public void conseguirArma() {
        cantidadArmas += 1;
    }

    public void conseguirProteccion() {
        cantidadProtecciones += 1;
    }

    public int getCantidadArmas() {
        return cantidadArmas;
    }

    public int getCantidadProtecciones() {
        return cantidadProtecciones;
    }
}
