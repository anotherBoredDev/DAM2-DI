import java.util.Random;

public class Dado {
    public static final Random random = new Random();

    public static int tirarDado(int minimoCaras, int maximoCaras) {
        return random.nextInt(minimoCaras, maximoCaras + 1); // número maximo de caras incluido
    }

    public static int tirarDado(int caras) {
        return tirarDado(1, caras);
    }

}
