import java.util.Scanner;
import java.util.Random;

public class TarreaIntegradora1MPS { 
public final static int MAXD=10;
    public static void main(String args[]) {
        Scanner lector = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = lector.nextLine();
        System.out.println("Ingrese sus estadisticas:");
        System.out.println("Ingrese su estadistica de Ataque:");
        double ataque = lector.nextDouble();
		System.out.println("Ingrese su nivel actual:");
        double level = lector.nextDouble();
        System.out.println("Ingrese su porcentaje de Danio Critico:");
        double pontDamagCritico = lector.nextDouble();
        System.out.println("Ingrese su porcentaje de Danio Critico Adicional:");
        double damagCritico = lector.nextDouble();
		System.out.println("Ingrese su estadistica de maestria elemental:");
        double maestriaElemental = lector.nextDouble();
		System.out.println("seleccione su tipo de Multiplicadores Transformativos :");
		System.out.println("quemadura 1");
		System.out.println("superconductor 2");
		System.out.println("torbellino 3");
		System.out.println("electro cargado 4");
		System.out.println("cristalizacion 5");
		System.out.println("sobrecargado 6");
		System.out.println("florecimiento 7");
		System.out.println("sobre florecimiento 8");
		System.out.println("super quemadura 9");
		System.out.println("ingrese el numero de eleccion :");
		int mtElection = lector.nextInt();
		lector.nextLine();
		
		
		double danioRaw = damageRaw(ataque, pontDamagCritico, damagCritico);
        System.out.println("Danio base: " + danioRaw);
		double bonoME = bunusEM(maestriaElemental);
		System.out.println("bono de maestria elemental "+bonoME+"%");
		
		double [] multResist= new double[MAXD];
		for (int i = 0; i < MAXD; i++) {
			multResist[i] = endurance();
			System.out.println("Valor de multiplicador de resistencia " + (i + 1) + ": " + multResist[i]);
		}
		double[] specialDamage = new double[MAXD];
		for (int i = 0; i < MAXD; i++) {
		double tranformativedamage = identifiTranDamage(mtElection);
		double resistenciaAleatoria = multResist[i]; 
		double porcenEM = bunusEM(maestriaElemental);
		specialDamage[i] = calTraformDamage(tranformativedamage, resistenciaAleatoria, porcenEM, level);
		System.out.println("el danio tranformativo que se realizara " + (i + 1) + ": " + specialDamage[i]);
				}
				
		
		double hightDamage =  maxDamage(specialDamage);
		System.out.println("el danio maximo es :"+hightDamage);
	
		}
		
		/**
	 * Register a new user
	 * Within this main, the registration is found, which asks the user for their statistics and stores them.
	 * <b>pre:</b> Have a character.
	 * <b>pre:</b> Have statistics.
	 * @param numbers are real numbers.
	 * <b>pre:</b> Have space for storage.
	 * <b>post:</b> The character's information will be stored.
	 */
		
	 
	 
	 /**
     * Raw damage calculation
     * This subroutine calculates the base damage inflicted by the character.
     * <b>pre:</b> None.
     * <b>post:</b> The base damage will be calculated and returned.
     */

    public static double damageRaw(double ataque, double pontDamagCritico, double damagCritico) {
        double cpontDamagCritico = pontDamagCritico / 100;
        double cdamagCritico = damagCritico / 100;
        double atakeBase = ataque * (1 + cdamagCritico + cpontDamagCritico);

        return atakeBase;
    }
	
	 
	 /**
	* Calcula el bono de maestría elemental basado en la maestría elemental dada.
	*
	* @param maestriaElemental La maestría elemental del jugador.
	* @return El porcentaje de bono de maestría elemental calculado.
	*/
	 public static double bunusEM (double maestriaElemental){
		double porcenEM = 16 * (maestriaElemental/(maestriaElemental+2000));
		return porcenEM;
	 }
	 
	/**
	* Genera un valor de resistencia aleatorio dentro de un rango específico.
	* @return Un valor de resistencia aleatorio en el rango [0.5, 2.0].
	*/
	public static double endurance() {
    double a = 0.5;  // Valor mínimo del rango
    double b = 2.0;  // Valor máximo del rango
    double resistenciaAleatoria = a + (b - a) * Math.random();
	
    return resistenciaAleatoria;
	
	}
	
	
	/**
	* Identifica el valor del daño transformador basado en la elección del usuario.
	*
	* @param mtElection La elección del usuario que determina el tipo de daño transformador.
	* @return El valor del daño transformador calculado de acuerdo a la elección del usuario.
	* pre mtElection debe ser un valor entre 1 y 9, inclusive.
	* post Si mtElection no se encuentra en el rango válido (1-9), se asumirá un valor de daño transformador de 0.
	*/
	public static double identifiTranDamage(int mtElection){
		double tranformativedamage = 0;
		switch (mtElection) {
        case 1:
            tranformativedamage = 0.25;
            break;
        case 2:
            tranformativedamage = 0.50;
            break;
        case 3:
            tranformativedamage = 0.60;
            break;
        case 4:
            tranformativedamage = 1.2;
            break;
        case 5:
            tranformativedamage = 1.5;
            break;
        case 6:
        case 7:
            tranformativedamage = 2.0;
            break;
        case 8:
        case 9:
            tranformativedamage = 3.0;
            break;
        default:
		System.out.println("Error con el daño transformativo, se tomara como 0.");}
		return tranformativedamage;	
    }
    
	/**
	* Calcula el daño transformador basado en varios factores.
	*
	* @param tranformativedamage El valor del daño transformador.
	* @param resistenciaAleatoria El valor de resistencia aleatoria.
	* @param porcenEM El porcentaje de bono de maestría elemental.
	* @param level El nivel del jugador.
	* @return El daño transformador calculado.
	*/
	
	public static double calTraformDamage(double tranformativedamage,double resistenciaAleatoria,double porcenEM,double level){
		double sTD= tranformativedamage*level*porcenEM*resistenciaAleatoria; // sTD estadistica de daño tranformativo
		return sTD;
	}
	
	/**
	* Encuentra el valor máximo en un arreglo de daños especiales.
	*
	* @param specialDamage Un arreglo de números que representa los daños especiales.
	* @return El valor máximo en el arreglo de daños especiales.
	*/
	public static double maxDamage(double[] specialDamage){
	double idMaxDamage=0;
	for (int i = 0; i < specialDamage.length ; i++){
		if(specialDamage[i]>idMaxDamage){
			idMaxDamage=specialDamage[i];
			}
		}
		return idMaxDamage;
	}
    
	}
	


