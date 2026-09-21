public class Superviviente extends Personaje {
    private boolean tieneBotiquin = false;
    private int cantidadArmas = 0;
    private int cantidadProtecciones = 0;

    public Superviviente() {
        super(20, 4); // Puntos de vida y fuerza base.
    }

    @Override
    public boolean atacar(Personaje objetivo) {
        int fuerzaFinal = Dado.tirarDado(getFuerza()) + cantidadArmas;
        return objetivo.recibirDanio(fuerzaFinal);
    }

    @Override
    public boolean recibirDanio(int cantidad) {
        return super.recibirDanio(cantidad - cantidadProtecciones);
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
