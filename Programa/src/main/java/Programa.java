import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Programa {

    //public static String jugador1;
    //public static String jugador2;
    private static String nombreTorneo;

    public static void main(String[] args) {

        System.out.println("BIENVENIDO AL PARTIDO FINAL DE TENIS");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Antes de comenzar ingrese los siguientes datos por favor: ");

        System.out.print("Nombre del Torneo: ");
        nombreTorneo = scanner.nextLine();
        System.out.println();

        System.out.print("Nombre del Tenista 1: ");
        String jugador1 = scanner.nextLine();
        System.out.print("Nombre del Tenista 2: ");
        String jugador2= scanner.nextLine();

        System.out.println();
        System.out.print("Ingresa la cantidad de set a disputar entre los jugadores: ");

        int nroSets;
        do {
            try{
                nroSets = scanner.nextInt();
                if (nroSets != 3 && nroSets != 5)
                    System.out.print("Error! El valor debe ser 3 o 5 Sets, segun las reglas oficiales del Tenis. Vuelva a Ingresar: ");
            } catch (InputMismatchException ex) {
                System.out.print("Debe ingresar obligatoriamente un número entero. Vuelva a ingresar: ");
                scanner.next();
                nroSets = 0;
            }
        } while (nroSets != 3 && nroSets != 5);

        System.out.println("¡Excelente, el partido se disputara al mejor de " + nroSets + " sets!");
        System.out.println();
        System.out.print("Ahora Ingrese la probabilidad de ganar del jugador 1 (" + jugador1 + ") que tendra que ser un valor entre 1% a 100%: ");
        float porcentajeVictoriaJ1;
        float porcentajeVictoriaJ2;
        do {
            try {
                porcentajeVictoriaJ1 = scanner.nextFloat();
                if (porcentajeVictoriaJ1 < 1 || porcentajeVictoriaJ1 > 100){
                    System.out.print("Error! El valor debe estar entre 1% a 100%. Vuelva a ingresar: ");
                }
            } catch (InputMismatchException ex){
                System.out.print("Debe ingresar obligatoriamente un número. Vuelva a ingresar: ");
                scanner.next();
                porcentajeVictoriaJ1 = -1;
            }

        } while (porcentajeVictoriaJ1 < 1 || porcentajeVictoriaJ1 > 100);

        porcentajeVictoriaJ2 = 100 - porcentajeVictoriaJ1;

        System.out.println("Porcentaje de victoria de " + jugador1 + " es: " + porcentajeVictoriaJ1 +"%");
        System.out.println("Porcentaje de victoria de " + jugador2 + " es: " + porcentajeVictoriaJ2 +"%");

        System.out.print("-----------DEMOS COMIENZO AL PARTIDO!-----------");


        
        iniciarPartido(nroSets,porcentajeVictoriaJ1,porcentajeVictoriaJ2, jugador1, jugador2);

        System.out.println("Desea hacer una revancha? Ingrese 'Si', o cualquier otro boton para terminar: ");
        scanner.nextLine();
        String res = scanner.nextLine();
        while (res.equals("Si")){
            iniciarPartido(nroSets,porcentajeVictoriaJ1,porcentajeVictoriaJ2, jugador1, jugador2);
            System.out.println("Desea hacer una revancha? Ingrese 'Si', o cualquier otro boton para terminar: ");
            res = scanner.nextLine();
        }
        System.out.println();
        System.out.println("FIN DEL PROGRAMA");
    }

    private static void iniciarPartido(int nroSets, float porcentajeVictoriaJ1, float porcentajeVictoriaJ2, String jugador1, String jugador2) {
        //Creamos arreglo de acumulacion, de tamaño de cantidad de sets para contar los games ganados en cada set
        int[] contadorGamesJ1 = new int[nroSets];
        int[] contadorGamesJ2 = new int[nroSets];

        int ventaja;
        if (nroSets == 3) ventaja = 1;
        else ventaja = 2;

        int setsJ1 = 0, setsJ2 = 0;

        String jugadorConSaque = jugador1;
        //Este ciclo for se va a realizar la cantidad de sets ingresado por teclado
        for (int i = 0; i < nroSets; i++) {
            //con este if cortamos en caso de que un jugador saque una ventaja inalcanzable para el otro jugador
            if ((setsJ1 < (nroSets - ventaja) && setsJ2 < (nroSets - ventaja)) || (Math.abs(setsJ1 - setsJ2) < ventaja)) {
                contadorGamesJ1[i] = 0;
                contadorGamesJ2[i] = 0;
                System.out.println("\nComienzo del Set " + (i+1));
                //aca logramos jugar un set completo
                while ((contadorGamesJ1[i] < 6 && contadorGamesJ2[i] < 6) || (Math.abs(contadorGamesJ1[i] - contadorGamesJ2[i]) < 2)){
                    System.out.println("\tComienzo del Game: " + ((contadorGamesJ1[i] + contadorGamesJ2[i]) + 1) );
                    System.out.println("\tSaca el jugador: " + jugadorConSaque);
                    int puntosGameJugador1 = 0;
                    int puntosGameJugador2 = 0;
                    //Aca simulamos un game completo
                    while ((puntosGameJugador1 < 4 && puntosGameJugador2 < 4) || (Math.abs(puntosGameJugador1 - puntosGameJugador2) < 2)){
                        String ganadorDelPunto = simularPunto(porcentajeVictoriaJ1,jugador1,jugador2);
                        if (ganadorDelPunto == jugador1){
                            puntosGameJugador1++;
                        }
                        else {
                            puntosGameJugador2++;
                        }
                        System.out.println("\t\tGanador del Punto: " + ganadorDelPunto);

                        if((puntosGameJugador1 < 4 && puntosGameJugador2 < 4) || (Math.abs(puntosGameJugador1 - puntosGameJugador2) < 2)){
                            System.out.println("\t\t\tResultado Parcial del Game: ");
                            System.out.println("\t\t\t" + jugador1 + ": " + puntajeDeGame(puntosGameJugador1,puntosGameJugador2) + " - " +
                                    jugador2 + ": " +puntajeDeGame(puntosGameJugador2,puntosGameJugador1) );
                        }

                    }
                    jugadorConSaque = cambiarSaque(jugadorConSaque, jugador1, jugador2); //termina el game y cambio el saque
                    if (puntosGameJugador1 > puntosGameJugador2) {
                        System.out.println("Ganador del Game, " + jugador1);
                        contadorGamesJ1[i]++;
                    }
                    else {
                        System.out.println("Ganador del Game, " + jugador2);
                        contadorGamesJ2[i]++;
                    }
                    System.out.println("\nResultado Parcial del SET " + (i+1));
                    System.out.print(jugador1 + ": " + contadorGamesJ1[i] + " - ");
                    System.out.println(jugador2 + ": " + contadorGamesJ2[i]);
                    //cortamos el ciclo en caso de que algun jugador llego a 7
                    if ((contadorGamesJ1[i] == 7 || contadorGamesJ2[i] == 7))
                        break;
                }
                if (contadorGamesJ1[i] > contadorGamesJ2[i]) {
                    System.out.println("Ganador del Set, " + jugador1);
                    setsJ1++;
                }
                else {
                    System.out.println("Ganador del Set, " + jugador2);
                    setsJ2++;
                }

                System.out.println("\n¡Asi està el Partido!");
                System.out.println(jugador1 + ": " + Arrays.toString(contadorGamesJ1));
                System.out.println(jugador2 + ": " + Arrays.toString(contadorGamesJ2));
            }
            //else {break;}


        }
        System.out.println("\nTenemos al Gran Campeon del Torneo " + nombreTorneo + "!!!");
        System.out.println("Felicitaciones al jugador: " + ganador(setsJ1, setsJ2, jugador1, jugador2) );
        System.out.println("Resultado Final : ");
        System.out.println(jugador1 + ": " + Arrays.toString(contadorGamesJ1));
        System.out.println(jugador2 + ": " + Arrays.toString(contadorGamesJ2));
    }

    public static String puntajeDeGame(int puntosGameJugador, int puntosGameJugador2) {
        if (puntosGameJugador == 0) return "0";
        if (puntosGameJugador == 1) return "15";
        if (puntosGameJugador == 2) return "30";
        if (puntosGameJugador >= 3 && puntosGameJugador2 > puntosGameJugador) return "_";
        if (puntosGameJugador == 3) return "40";
        if (puntosGameJugador >= 3 && puntosGameJugador2 < puntosGameJugador) return "AD";
        if (puntosGameJugador >= 3 && puntosGameJugador2 == puntosGameJugador) return "40";

        else return " ";
    }

    public static String ganador(int setsJ1, int setsJ2, String jugador1, String jugador2) {
        if (setsJ1 > setsJ2) return jugador1;
        else  return jugador2;
    }

    //este metodo calcula un numero random entre 0 y 100 y se fija si ese valor es menor al porcentaje de vicotria de jugador 1, que seria la cota superior
    // del numero random.
    public static String simularPunto(float porcentajeVictoriaJ1, String jugador1, String jugador2) {
        Random ran = new Random();
        float numAleatorio = ran.nextFloat() * 100;

        if (numAleatorio <= porcentajeVictoriaJ1){
            return jugador1;
        }
        else return jugador2;
    }

    public static String cambiarSaque(String jugadorConSaque, String jugador1, String jugador2) {
        if (jugadorConSaque == jugador1){
            return jugador2;
        }
        else {return jugador1;
        }
    }


}
