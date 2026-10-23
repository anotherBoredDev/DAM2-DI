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

    public ReporteAtaque ejecutarTurnoAtaque() {
        Zombie enemigo = getZombiesEnHabitacionActual().getFirst();
        int danioSuperviviente, danioEnemigo;

        danioSuperviviente = superviviente.atacar(enemigo);

        if (enemigo.getVida() <= 0) {
            getZombiesEnHabitacionActual().remove(enemigo);
            actualizarEstado();
            return new ReporteAtaque(danioSuperviviente, 0, true);
        }

        danioEnemigo = enemigo.atacar(superviviente);

        actualizarEstado();
        return new ReporteAtaque(danioSuperviviente, danioEnemigo, false);
    }

    public boolean intentarCurarSuperviviente() {
        return superviviente.usarBotiquin();
    }

    public ResultadoBusqueda buscarHabitacion() {
        if (getIntentosBusquedaEnHabitacionActual() <= 0) {
            return ResultadoBusqueda.SIN_INTENTOS;
        }

        ResultadoBusqueda resultado;

        int dado = Dado.tirarDado(100);

        if (dado <= 75) {
            dado = Dado.tirarDado(100);

            resultado =  ResultadoBusqueda.RUIDO_SIN_CONSECUENCIAS;

            if (dado > 40 && dado <= 80) {
                getHabitacionActual().generarZombies(1);
                resultado = ResultadoBusqueda.RUIDO_UN_ZOMBIE;
            } else if (dado > 80 && dado <= 100) {
                getHabitacionActual().generarZombies(2);
                resultado =  ResultadoBusqueda.RUIDO_DOS_ZOMBIES;
            }
        } else if (dado <= 90) {
            resultado =  superviviente.intentarConseguirBotiquin() ? ResultadoBusqueda.BOTIQUIN_CONSEGUIDO : ResultadoBusqueda.BOTIQUIN_NO_CONSEGUIDO;
        } else if (dado <= 95) {
            superviviente.conseguirProteccion();
            resultado =  ResultadoBusqueda.PROTECCION_CONSEGUIDA;
        } else {
            superviviente.conseguirArma();
            resultado =  ResultadoBusqueda.ARMA_CONSEGUIDA;
        }

        getHabitacionActual().consumirBusqueda();
        actualizarEstado();
        return resultado;
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
