package modelo;

import java.io.Serializable;

/**

 * Esta clase define las variables que posee el cronometro para su funcionamiento.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar � Nicol�s M�rtinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar � Juan Manuel Castillo </a>

 */

 

public class Cronometro  extends ObjetoJuego {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String URL = "data/crono.png";
	
	private int centesima;
	private int segundo ;
	private int minuto;
	private int hora;
	
	// -----------------------------------------------------------------
		// Constructores
		// -----------------------------------------------------------------

	/**
	 * Este m�todo se encarga de dar construcci�n a los atributos de esta clase.
	 * 
	 * @param posicionX Se encarga de inicializar la posici�n en x del cronometro.
	 * @param posicionY Se encarga de inicializar la posici�n en y del cronometro.
	 * @param imagen Se encarga de inicializar la representaci�n una imagen por medio de un Url.
	 */
	
	public Cronometro(int posicionX, int posicionY, String imagen) {
		super(posicionX, posicionY, imagen);
		
		centesima = 0;
		segundo = 0;
		minuto = 0;
		hora = 0;
	}
	
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Este m�todo se encarga de aumentar de uno en uno dependiendo
	 *  de la escala en la que se encuentre el cronometro
	 * 
	 */
	
	
	public void aumentar() {
		centesima++;
        if (centesima>=99){
            centesima = 0;
            segundo++;
        }
        if (segundo>=60){
            segundo = 0;
            minuto++;
        }

        if (minuto>=60){
            minuto = 0;
            hora++;
        }
        if (hora>=24){
           centesima =0;
           segundo = 0;
           minuto =0;
           hora =0;
        }
	}
	
	/**
	 * Este m�todo se encarga de dar el Url de las imagenes a utilizar en la interfaz.
	 * 
	 * @return El Url correspondiente a la imagen necesitada.
	 */
	

	public static String getUrl() {
		return URL;
	}

	/**
	 * Este m�todo se encarga de dar las centesimas para la utilizaci�n del cronometro.
	 * 
	 * @return Las centesimas correspondientes al cronometro
	 */
	
	
	public int getCentesima() {
		return centesima;
	}

	/**
	 * Este m�todo se encarga de dar los segundos para la utilizaci�n del cronometro.
	 * 
	 * @return Los segundos correspondientes al cronometro
	 */
	
	public int getSegundo() {
		return segundo;
	}

	/**
	 * Este m�todo se encarga de dar los minutos para la utilizaci�n del cronometro.
	 * 
	 * @return Los minutos correspondientes al cronometro
	 */
	
	public int getMinuto() {
		return minuto;
	}

	/**
	 * Este m�todo se encarga de dar las horas para la utilizaci�n del cronometro.
	 * 
	 * @return Las horas correspondientes al cronometro
	 */

	public int getHora() {
		return hora;
	}
	
	
}