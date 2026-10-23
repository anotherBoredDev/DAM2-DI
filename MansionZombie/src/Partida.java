public class Partida {
    private final GestorJuego gestorJuego;
    private final LectorConsola lectorConsola;

    public Partida(GestorJuego gestorJuego, LectorConsola lectorConsola) {
        this.gestorJuego = gestorJuego;
        this.lectorConsola = lectorConsola;
    }

    public void iniciar() {
        mostrarMenuPrincipal();
        int dificultad = lectorConsola.leerEnteroEnRango("Opción", 1, 2);
        procesarDificultad(dificultad);

        while (gestorJuego.getEstado() != EstadoJuego.GANADO && gestorJuego.getEstado() != EstadoJuego.PERDIDO) {
            mostrarMenuAcciones();
            int accion = lectorConsola.leerEntero("Acción");
            procesarAccion(accion);
        }

        mostrarMenuFinal();
    }

    private void imprimirSeparador() {
        System.out.println("----------------------------------------");
    }

    private void mostrarMenuPrincipal() {
        imprimirSeparador();
        System.out.println("BIENVENIDO A LA MANSIÓN ZOMBIE");
        System.out.println("Elige dificultad:");
        System.out.println("1. Fácil (5 Habitaciones)");
        System.out.println("2. Difícil (10 Habitaciones)");
        imprimirSeparador();
    }

    private void mostrarMenuFinal() {
        imprimirSeparador();
        if (gestorJuego.getEstado() == EstadoJuego.GANADO) {
            System.out.println("¡¡¡HAS ESCAPADO DE LA MANSIÓN ZOMBIE HAS GANADO!!!");
        } else if (gestorJuego.getEstado() == EstadoJuego.PERDIDO) {
            System.out.println("TUS PUNTOS DE VIDA HAN LLEGADO A 0");
            System.out.println("¡HAS PERDIDO!");
        }
    }

    private void mostrarMenuAcciones() {
        int vidaSuperviviente = gestorJuego.getSuperviviente().getVida();
        int proteccionesSuperviviente = gestorJuego.getSuperviviente().getCantidadProtecciones();
        int armasSuperviviente = gestorJuego.getSuperviviente().getCantidadArmas();

        int cantidadZombies = gestorJuego.getCantidadZombiesEnHabitacionActual();

        int intentosBusquedaRestantes = gestorJuego.getIntentosBusquedaEnHabitacionActual();

        imprimirSeparador();
        System.out.println("PV: " + vidaSuperviviente + " | PROTECCIÓN: " + proteccionesSuperviviente + " | ARMAS: " + armasSuperviviente);
        System.out.println("HABITACIÓN " + (gestorJuego.getHabitacionActualIndex() + 1) + " | ZOMBIES: " + cantidadZombies);
        System.out.println("¿QUÉ ACCIÓN QUIERES REALIZAR?");

        if (gestorJuego.getEstado() == EstadoJuego.COMBATE) {
            System.out.println("1. COMBATIR ZOMBIES");
        } else if (gestorJuego.getEstado() == EstadoJuego.EXPLORACION) {
            System.out.println("2. CURARSE");
            System.out.println("3. BUSCAR LA HABITACIÓN (" + intentosBusquedaRestantes + " INTENTO" + (intentosBusquedaRestantes != 1 ? "s" : "") + ")");
            System.out.println("4. AVANZAR DE HABITACIÓN");
        }
        imprimirSeparador();
    }


    private void mostrarEstadoCombate(int ronda) {
        Zombie enemigoActual = gestorJuego.getZombiesEnHabitacionActual().getFirst();
        String nombreEnemigo = enemigoActual.getNombre();
        int vidaEnemigo = enemigoActual.getVida();

        int vidaSuperviviente = gestorJuego.getSuperviviente().getVida();
        int proteccionesSuperviviente = gestorJuego.getSuperviviente().getCantidadProtecciones();
        int armasSuperviviente = gestorJuego.getSuperviviente().getCantidadArmas();

        imprimirSeparador();
        System.out.println("RONDA " + ronda);
        System.out.println("---");
        System.out.println("PV: " + vidaSuperviviente + " | PROTECCIÓN: " + proteccionesSuperviviente + " | ARMAS: " + armasSuperviviente);
        System.out.println(nombreEnemigo + " | PV: " + vidaEnemigo);
        System.out.println("---");
    }

    private void mostrarResultadoCombate(ResultadoAtaque resultado) {
        System.out.println("El superviviente ataca con " + resultado.danioSuperviviente() + " puntos de ataque.");
        if (resultado.danioEnemigo() != 0 || !resultado.enemigoDerrotado()) {
            System.out.println("El enemigo ataca con " + resultado.danioEnemigo() + " puntos de ataque.");
        } else {
            System.out.println("Has eliminado al enemigo.");
        }
    }

    private void mostrarResultadoCuracion(boolean exito) {
        if (exito) {
            System.out.println("Te has curado exitosamente. Ya no tienes botiquines.");
        } else {
            System.out.println("No tienes botiquines.");
        }
    }

    private void procesarDificultad(int dificultad) {
        switch (dificultad) {
            case 1 -> gestorJuego.iniciarJuego(5);
            case 2 -> gestorJuego.iniciarJuego(10);
            default -> System.out.println("Error: Valor de dificultad incorrecto (1-2)");
        }
    }

    private void procesarAccion(int accion) {
        EstadoJuego estado = gestorJuego.getEstado();

        switch (accion) {
            // REALIZAR TURNO DE COMBATE
            case 1 -> {
                if (estado == EstadoJuego.COMBATE) {
                    int ronda = 1;
                    imprimirSeparador();
                    do {
                        mostrarEstadoCombate(ronda++);
                        ResultadoAtaque resultado = gestorJuego.ejecutarTurnoAtaque();
                        mostrarResultadoCombate(resultado);
                        estado = gestorJuego.getEstado();
                    } while (estado == EstadoJuego.COMBATE);
                } else {
                    System.out.println("Acción prohibida: No hay zombies que atacar.");
                }
            }

            // CURARSE
            case 2 -> {
                if (estado == EstadoJuego.EXPLORACION) {
                    mostrarResultadoCuracion(gestorJuego.intentarCurarSuperviviente());
                } else {
                    System.out.println("Acción prohibida: Estás en combate.");
                }
            }

            // BUSCAR EN LA HABITACIÓN
            case 3 -> {
                if (estado == EstadoJuego.EXPLORACION) {
                    gestorJuego.buscarHabitacion();
                } else {
                    System.out.println("Acción prohibida: Estás en combate.");
                }
            }

            // AVANZAR DE HABITACIÓN
            case 4 -> {
                if (estado == EstadoJuego.EXPLORACION) {
                    gestorJuego.avanzarSiguienteHabitacion();
                } else {
                    System.out.println("Acción prohibida: Quedan zombies en la habitación.");
                }
            }

            default -> System.out.println("Acción no encontrada.");
        }
    }
}