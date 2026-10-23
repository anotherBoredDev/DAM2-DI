import java.util.List;

public class GestorJuego {
    private EstadoJuego estado;
    private Superviviente superviviente;
    private Habitacion[] habitaciones;
    private int habitacionActualIndex;

    public GestorJuego() {
        this.superviviente = new Superviviente();
        this.habitaciones = new Habitacion[0];
    }

    public void actualizarEstado() {
        if (superviviente.getVida() <= 0) {
            estado = EstadoJuego.PERDIDO;
        } else if (habitacionActualIndex >= habitaciones.length) {
            estado = EstadoJuego.GANADO;
        } else if (!getZombiesEnHabitacionActual().isEmpty()) {
            estado = EstadoJuego.COMBATE;
        } else {
            estado = EstadoJuego.EXPLORACION;
        }
    }

    public void iniciarJuego(int numeroHabitaciones) {
        habitaciones = new Habitacion[numeroHabitaciones];

        for (int i = 0; i < numeroHabitaciones; i++) {
            habitaciones[i] = new Habitacion(i + 1);
        }

        habitacionActualIndex = 0;

        actualizarEstado();
    }

    public ResultadoAtaque ejecutarTurnoAtaque() {
        Zombie enemigo = getZombiesEnHabitacionActual().getFirst();
        int danioSuperviviente, danioEnemigo;

        danioSuperviviente = superviviente.atacar(enemigo);

        if (enemigo.getVida() <= 0) {
            getZombiesEnHabitacionActual().remove(enemigo);
            actualizarEstado();
            return new ResultadoAtaque(danioSuperviviente, 0, true);
        }

        danioEnemigo = enemigo.atacar(superviviente);

        actualizarEstado();
        return new ResultadoAtaque(danioSuperviviente, danioEnemigo, false);
    }

    public boolean intentarCurarSuperviviente() {
        return superviviente.usarBotiquin();
    }

    public void buscarHabitacion() {
        if (getIntentosBusquedaEnHabitacionActual() <= 0) {
            return;
        }

        int dado = Dado.tirarDado(100);
        if (dado <= 75) {
            dado = Dado.tirarDado(100);
            if (dado > 40 && dado <= 80) {
                getHabitacionActual().generarZombies(1);
            } else if (dado > 80 && dado <= 100) {
                getHabitacionActual().generarZombies(2);
            }
        } else if (dado <= 90) {
            superviviente.intentarConseguirBotiquin();
        } else if (dado <= 95) {
            superviviente.conseguirProteccion();
        } else {
            superviviente.conseguirArma();
        }

        getHabitacionActual().consumirBusqueda();
        actualizarEstado();
    }

    public void avanzarSiguienteHabitacion() {
        habitacionActualIndex++;
        actualizarEstado();
    }

    public EstadoJuego getEstado() {
        return estado;
    }

    public Superviviente getSuperviviente() {
        return superviviente;
    }

    private Habitacion getHabitacionActual() {
        return habitaciones[habitacionActualIndex];
    }

    public int getHabitacionActualIndex() {
        return habitacionActualIndex;
    }

    public List<Zombie> getZombiesEnHabitacionActual() {
        return getHabitacionActual().getZombies();
    }

    public int getCantidadZombiesEnHabitacionActual() {
        return getZombiesEnHabitacionActual().size();
    }

    public int getIntentosBusquedaEnHabitacionActual() {
        return getHabitacionActual().getIntentosBusqueda();
    }
}
