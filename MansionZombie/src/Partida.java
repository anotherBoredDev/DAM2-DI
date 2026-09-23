import java.util.List;

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

        while (gestorJuego.getEstado() == GestorJuego.EstadosJuego.JUGANDO) {
            // mostrarEstadoHabitacion();
            int accion = lectorConsola.leerEnteroEnRango("Acción", 1, 3);
            // procesarAccion(accion);
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
            case 1 -> gestorJuego.prepararJuego(5);
            case 2 -> gestorJuego.prepararJuego(10);
            default -> System.out.println("Error: Valor de dificultad incorrecto (1-2)");
        }
    }

    /*
    private void mostrarEstadoHabitacion() {
        List<Zombie> zombies = gestorJuego.getZombiesEnHabitacion();
        String estadoHabitacion = String.format("Habitación: %d | Contiene %d zombies", gestorJuego.getHabitacionActual(), zombies.size());

        imprimirSeparador();
        mostrarEstadoSuperviviente();
        System.out.println(estadoHabitacion);
        if (game) {
            System.out.println("1. Combatir un zombie");
        } else {
            System.out.println("2. Buscar habitación");
            System.out.println("3. Avanzar a la siguiente habitación");
        }
        imprimirSeparador();
    }



    private void procesarAccion(int accion) {
        List<Zombie> zombies = gestorJuego.getZombiesEnHabitacion();
        boolean enCombate = !zombies.isEmpty();

        switch (accion) {
            case 1 -> {
                if (!enCombate) {
                    System.out.println("Error: Acción no valida en el contexto actual");
                } else {
                    for (int i = 0; i < zombies.size(); i++) {
                        gestorJuego.procesarTurnoCombate();
                    }
                }
            }
            case 2 -> {
                if (enCombate) {
                    System.out.println("Error: Acción no valida en el contexto actual");
                }
            }
            case 3 -> {
                if (enCombate) {
                    System.out.println("Error: Acción no valida en el contexto actual");
                }
            }
            default -> System.out.println("Error: Acción no reconocida");
        }
    }

    private void mostrarEstadoSuperviviente() {
        Superviviente superviviente = gestorJuego.getSuperviviente();
        String estadoSuperviviente = String.format("PV: %d | Armas: %d | Protecciones: %d",
                superviviente.getVidaActual(),
                superviviente.getCantidadArmas(),
                superviviente.getCantidadProtecciones());
        System.out.println(estadoSuperviviente);
    }

    private void mostrarEstadoZombie(Zombie zombie) {
        String estadoZombie = String.format("PV: %d | Fuerza: %d", zombie.getVidaActual(), zombie.getFuerza());
        System.out.println(estadoZombie);
    }
     */
}