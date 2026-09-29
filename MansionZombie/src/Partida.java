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

    private void procesarDificultad(int dificultad) {
        switch (dificultad) {
            case 1 -> gestorJuego.iniciarJuego(5);
            case 2 -> gestorJuego.iniciarJuego(10);
            default -> System.out.println("Error: Valor de dificultad incorrecto (1-2)");
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
        } else if (gestorJuego.getEstado() == EstadoJuego.EXPLORACION){
            System.out.println("2. CURARSE");
            System.out.println("3. BUSCAR LA HABITACIÓN (" + intentosBusquedaRestantes + " INTENTO" + (intentosBusquedaRestantes != 1 ? "s" : "") + ")");
            System.out.println("4. AVANZAR DE HABITACIÓN");
        }
        imprimirSeparador();
    }

    private void procesarAccion(int accion) {
        EstadoJuego estado = gestorJuego.getEstado();

        switch (accion) {
            case 1 -> {
                if (estado == EstadoJuego.COMBATE ) {

                    gestorJuego.ejecutarTurnoAtaque();
                } else {
                    System.out.println("Acción prohibida: No hay zombies que atacar.");
                }
            }

            case 2 -> {
                if (estado == EstadoJuego.EXPLORACION) {
                    gestorJuego.intentarCurarSuperviviente();
                } else {
                    System.out.println("Acción prohibida: Estás en combate.");
                }
            }

            case 3 -> {
                if (estado == EstadoJuego.EXPLORACION) {
                    gestorJuego.buscarHabitacion();
                } else {
                    System.out.println("Acción prohibida: Estás en combate.");
                }
            }

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