import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ProgramaTest {

    //este es el caso donde habia un empate en 40 - 40 y el jugador uno vence por lo que el valor retornado debe ser AD de Advantage
    @Test
    void puntajeDeGame() {

        int puntosJugador1 = 4;
        int puntosJugador2 = 3;
        String puntajeSegunReglasJ1 = Programa.puntajeDeGame(puntosJugador1,puntosJugador2);
        Assert.assertTrue(puntajeSegunReglasJ1.equals("AD"));
        Assert.assertFalse(puntajeSegunReglasJ1.equals("45"));
    }
    //se Fija que cuando se quiere definir el ganador devuelva realmente el jugador que mas sets gano y no el otro
    @Test
    void ganador() {
        int setsGanadosJ1 = 2;
        int setsGanadosJ2 = 1;
        String J1 = "Feder";
        String J2 = "Tsonga";
        String ganador = Programa.ganador(setsGanadosJ1,setsGanadosJ2, J1, J2);
        Assert.assertTrue(ganador == J1);
        Assert.assertFalse(ganador == J2);
    }

    //Corrobora que el cambio de Saque sea efectivo, Nadal Deja de Sacar, y ahora lo hace Nalbandian
    @Test
    void cambiarSaque() {
        String jugador1 = "Nadal";
        String jugador2 = "Nalbandian";
        String sacador = jugador1;
        Assert.assertTrue(Programa.cambiarSaque(sacador,jugador1,jugador2) == "Nalbandian");
    }
}