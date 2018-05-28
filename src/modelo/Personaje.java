package modelo;

import java.awt.Rectangle;
import java.io.Serializable;

/**

 * Esta clase se encarga de modelar los atributos de los objetos "Personaje" 
 * dandoles su lógica, incluyendo sus coordenadas, movimientos, busqueda y adición
 * de un personaje correspondiente al jugador que lo haya seleccionado.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */

public class Personaje extends ObjetoJuego implements Comparable<Personaje>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int X_INICIAL = 500;
	public static final int Y_INICIAL = 600;
	
	
	private Personaje izq;
	private Personaje der;
	
	private String nombre;
	private int velocidad;
	private String imageDown;
	private String imageLeft;
	private String imageRight;
	private String imageUp;

	
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	
	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param posicionX Se encarga de inicializar la posición en x del cronometro.
	 * @param posicionY Se encarga de inicializar la posición en x del cronometro.
	 * @param imagen Se encarga de inicializar la representación de una imagen por medio de un Url.
	 */
	
	
	public Personaje(String nombre, int posicionX, int posicionY, String imagen) {
		super(posicionX, posicionY, imagen);
		this.nombre = nombre;
		izq = null;
		der = null;
		super.getArea().setSize(80, 80);
	}
	
	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param nombre Se encarga de inicializar el nombre del personaje.
	 * @param velocidad 
	 * @param posicionX Se encarga de inicializar la posición en x del personaje.
	 * @param posicionY Se encarga de inicializar la posición en y del personaje.
	 * @param imagen Se encarga de inicializar la representación una imagen por medio de un Url.
	 * @param imageDown Se encarga de inicializar la representación de la imagen hacia abajo por medio de un Url.
	 * @param imageLeft Se encarga de inicializar la representación de la imagen hacia la izquierda por medio de un Url.
	 * @param imageRight Se encarga de inicializar la representación de la imagen hacia la derecha por medio de un Url.
	 * @param imageUp Se encarga de inicializar la representación de la imagen hacia arriba por medio de un Url.
	 * 
	 */
	
	
	
	public Personaje(String nombre, int velocidad, int posicionX, int posicionY, String imagen, String imageDown, String imageLeft, String imageRight,
			String imageUp) {
		super(posicionX, posicionY, imagen);
		this.nombre = nombre;
		this.velocidad = velocidad;
		this.imageDown = imageDown;
		this.imageLeft = imageLeft;
		this.imageRight = imageRight;
		this.imageUp = imageUp;
	}



	/**
	 * Este método se encarga de darle movimiento a la moneda perteneciente a un
	 * ArrayList con objetos de tipo Personaje recibiendo por parametro la coordenanda X y
	 *  Y.
	 * 
	 * @param x de tipo int.
	 * @param y de tipo int.
	*/

	public void mover(int x, int y) {
		if(!saleDelLimite(x, y)) {
		super.setPosicionX(super.getPosicionX()+x);
		super.setPosicionY(super.getPosicionY()+y);}
       	super.setArea(new Rectangle(super.getPosicionX(), super.getPosicionY(), 60, 60));

	}
	
	
	/**
	 * Este método se encarga de establecer los limites de movimiento del jugador de acuerdo
	 * al tamaño de la ventana para el desarrollo de la partida.
	 * 
	 * @param x de tipo int.
	 * @param y de tipo int.
	*/
	
	public boolean saleDelLimite(int x, int y) {
		int sumax = super.getPosicionX()+x;
		int sumay = super.getPosicionY()+y;
		
		return sumax>=920 || sumax<=0 || sumay>=650 ;
	}
	
	/**
	 * Este método se encarga de insertar un personaje en el arbol
	 * revisando en primera instancia su nombre para luego ser
	 * insertado en los nodos.
	 * 
	 * @param p de tipo Personaje que ingresa por parametro para ser
	 * agregado al arbol
	 */
	
	public void insertar(Personaje p) {
		if(nombre.compareToIgnoreCase(p.getNombre())>0) {
			if(izq==null) {izq = p;}
			else { izq.insertar(p);}
		}
		else {
			if(der==null) {der = p;}
			else { der.insertar(p);}
		}
	}

	
	/**
	 * Este método se encarga de realizar la lectura  del árbol comparando 
	 * el personaje buscado con los jugadores pertenecientes al ArrayList.
	 * 
	 * @param n de tipo String que ingresa el usuario.
	 */
	
	public Personaje buscarPersonaje(String n) {
		if(nombre.compareToIgnoreCase(n)==0) {
			return this;
		}
		else if(nombre.compareTo(n)>0) {
			return izq!=null?izq.buscarPersonaje(n):null;
		}
		else {
			return der!=null?der.buscarPersonaje(n):null;
		}
	}
	
	/**
	 * Este método se encarga de por medio un url de tipo String
	 * se enlace la imagen hacia abajo del personaje
	 * 
	 * @return imageDown de tipo String.
	 */

	public String getImageDown() {
		return imageDown;
	}

	/**
	 * Este método se encarga de por medio un url de tipo String
	 * se enlace la imagen hacia la izquierda del personaje
	 * 
	 * @return imageLeft de tipo String.
	 */
	
	public String getImageLeft() {
		return imageLeft;
	}

	/**
	 * Este método se encarga de por medio un url de tipo String
	 * se enlace la imagen hacia la derecha del personaje
	 * 
	 * @return imageRight de tipo String.
	 */

	public String getImageRight() {
		return imageRight;
	}

	/**
	 * Este método se encarga de por medio un url de tipo String
	 * se enlace la imagen hacia  arriba del personaje
	 * 
	 * @return imageUp de tipo String.
	 */
	
	public String getImageUp() {
		return imageUp;
	}

	/**
	 * Este método se encarga de dar la izquierda del arbol binario
	 * de personajes.
	 * 
	 * @return izq de tipo Personaje.
	 */
	
	public Personaje getIzq() {
		return izq;
	}

	/**
	 * Este método se encarga de modificar la izquierda del arbol binario
	 * de personajes.
	 * 
	 * @param izq de tipo Personaje.
	 */

	public void setIzq(Personaje izq) {
		this.izq = izq;
	}

	/**
	 * Este método se encarga de dar la derecha del arbol binario
	 * de personajes.
	 * 
	 * @return der de tipo Personaje.
	 */

	public Personaje getDer() {
		return der;
	}

	/**
	 * Este método se encarga de modificar la derecha del arbol binario
	 * de personajes.
	 * 
	 * @param der de tipo Personaje.
	 */
	
	public void setDer(Personaje der) {
		this.der = der;
	}

	/**
	 * Este método se encarga de dar el nombre del personaje.
	 * 
	 * @return nombre de tipo String.
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Este método se encarga de dar la velocidad del personaje.
	 * 
	 * @return velocidad de tipo int.
	 */
	
	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * Este método se encarga de comparar la velocidad del jugador.
	 * 
	 * @param o de tipo Jugador.
	 * @return valor de las comparaciones realizadas.
	 */
	

	@Override
	public int compareTo(Personaje o) {
		if(velocidad> o.velocidad) {return 1;}
		else if(velocidad<o.velocidad) {return -1;}
		else{return 0;}
	}
	
	
	
	
}
