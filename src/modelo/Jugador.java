package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**

 * Esta clase se encarga de la realización busquedas, ordenamiento, inserción, eliminación y comparación 
 * de los jugadores por medio de arboles binarios.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */

public class Jugador implements Serializable, Comparable<Jugador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final int VIDA_INICIAL = 100;
	
	
	private Personaje personajeElegido;
	private Jugador izq;
	private Jugador der;
	
	private Cronometro crono;
	private String nombre;
	private int puntaje;
	private int vida;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param nombre Se encarga de inicializar el nombre del jugador.
	 * @param puntaje Se encarga de inicializar el puntaje del jugador.
	 */
	
	
	public Jugador(String nombre, int puntaje) {
		super();
		this.nombre = nombre;
		this.puntaje = puntaje;
		vida = VIDA_INICIAL;
	}
	
	
	/**
	 * Este método se encarga de realizar la lectura  del árbol obteniendo 
	 * primero la hoja izquierda luego la raíz y por último la hoja derecha.
	 * 
	 * @param acumulado de tipo ArrayList perteneciente a la clase Jugador.
	 */
	
	public void inorden(ArrayList<Jugador> acumulado) {
		if(izq!=null) {
			izq.inorden(acumulado);
		}
		acumulado.add(this);
		if(der!=null) {
			der.inorden(acumulado);
		}

	}
	
	/**
	 * Este método se encarga de realizar la lectura  del árbol comparando 
	 * el jugador buscado con los jugadores pertenecientes al ArrayList.
	 * 
	 * @param n de tipo String que ingresa el usuario.
	 */
	
	
	public Jugador buscarJugador(String n) {
		if(nombre.compareTo(n)==0) {
			return this;
		}
		else if(nombre.compareTo(n)>0) {
			return izq!=null?izq.buscarJugador(n):null;
		}
		else {
			return der!=null?der.buscarJugador(n):null;
		}
	}
	
	/**
	 * Este método se encarga de insertar un jugador en el arbol
	 * revisando en primera instancia su nombre para luego ser
	 * insertado en los nodos.
	 * 
	 * @param j de tipo Jugador que ingresa por parametro para ser
	 * agregado al arbol
	 */
	
	public void insertar(Jugador j) {
		if(nombre.compareTo(j.getNombre())>0) {
			if(izq==null) {izq = j;}
			else { izq.insertar(j);}
		}
		else {
			if(der==null) {der = j;}
			else { der.insertar(j);}
		}
	}
	
	/**
	 * Este método se encarga de eliminar un jugador en el arbol
	 * revisando en primera instancia si es hoja para luego seguir
	 * con los revisiones en los demas nodos.
	 * 
	 * @param n de tipo String.
	 * @return null en caso de que sea hoja.
	 * @return this de tipo Jugador el cual será eliminado.
	 */
	
	public Jugador eliminarJugador(String n) {
		if(esHoja()) {return null;}
		
		if(nombre.compareTo(n)==0) {
			if(der==null) {return izq;}
			if(izq==null) {return der;}
			
			Jugador sucesora = der.darMenor();
			der = der.eliminarJugador(sucesora.nombre);
			sucesora.izq = izq;
			sucesora.der = der;
			return sucesora;
		}
		else if(nombre.compareTo(n)>0) {
			izq = izq.eliminarJugador(n);
		}else {
			der = der.eliminarJugador(n);
		}
		
		return this;
	}
	
	
	/**
	 * Este método se encarga de hacer la lectura del arbol verificando
	 * sus hijos (nodos) tanto en izquierda como en derecha
	 * 
	 * @return true si se cumple la condicion de no tener hijos
	 * o false en caso de que si cuente con hijos.
	 */
	
	public boolean esHoja() {
		return izq==null && der==null;
	}
	
	/**
	 * Este método se encarga de hacer la lectura del arbol verificando
	 * su menor nodo.
	 * 
	 * @return un Jugador el cual es el menor nodo.
	 */
	
	public Jugador darMenor() {
		return izq==null?this:izq.darMenor();
	}
	
	/**
	 * Este método se encarga de hacer la lectura del arbol verificando
	 * su mayor nodo.
	 * 
	 * @return un Jugador el cual es el mayor nodo.
	 */
	
	public Jugador darMayor() {
		return der==null?this:der.darMayor();
	}
	
	
	/**
	 * Este método se encarga de inicializar el cronometro para cada jugador
	 * estableciendo en ceros todos valores.
	 * 
	 */
	
	public void iniciarCronometro() {
		crono = new Cronometro(0, 0, Cronometro.URL);
	}

	/**
	 * Este método se encarga de dar el cronometro para cada jugador
	 *
	 *@return crono de tipo Cronometro. 
	 */
	
	public Cronometro getCrono() {
		return crono;
	}
	
	/**
	 * Este método se encarga de aumentar el puntaje para cada jugador.
	 * 
	 * @param c de tipo int que ingresa como variable del valor de las monedas obtenido.
	 * @return puntaje de tipo int.
	 */
	
	public void aumentarPuntaje (int c) {
		puntaje+=c;
	}

	/**
	 * Este método se encarga de dar el nombre del jugador.
	 * 
	 * @return nombre de tipo String.
	 */
	
	public String getNombre() {
		return nombre;
	}

	/**
	 * Este método se encarga de modificar el nombre del jugador.
	 * 
	 * @param nombre de tipo String.
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Este método se encarga de dar el puntaje del jugador.
	 * 
	 * @return puntaje de tipo int.
	 */
	
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * Este método se encarga de modificar el puntaje del jugador.
	 * 
	 * @param puntaje de tipo int.
	 */
	
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * Este método se encarga de dar la vida del jugador.
	 * 
	 * @return vida de tipo int.
	 */
	
	public int getVida() {
		return vida;
	}

	/**
	 * Este método se encarga de modificar la vida del jugador.
	 * 
	 * @param vida de tipo int.
	 */
	
	public void setVida(int vida) {
		this.vida = vida;
	}

	/**
	 * Este método se encarga de dar el personaje elegido del jugador.
	 * 
	 * @return personajeElegido de tipo Personaje.
	 */
	
	public Personaje getPersonajeElegido() {
		return personajeElegido;
	}

	/**
	 * Este método se encarga de modificar el personaje elegido del jugador.
	 * 
	 * @param personajeElegido de tipo Personaje.
	 */
	
	public void setPersonajeElegido(Personaje personajeElegido) {
		this.personajeElegido = personajeElegido;
	}


	/**
	 * Este método se encarga de comparar los puntajes del jugador para
	 * utilizar este recurso en un futuro y obtener su record.
	 * 
	 * @param j de tipo Jugador.
	 * @return retorno de tipo int.
	 */

	@Override
	public int compareTo(Jugador j) {
		int retorno;
		
		if(puntaje>j.puntaje) {
			retorno=1;
		}else if (puntaje<j.puntaje) {
			retorno = -1;
		}
		else{
			retorno = 0;
		}
		
		return retorno;
	}

	/**
	 * Este método se encarga de convertir en una linea de String toda
	 * la informacion a almacenar en los archivos planos del juego.
	 * 
	 * @return todas las propiedades de la partida de un jugador en tipo String.
	 */

	
	
	@Override
	public String toString() {
		return nombre +" " +"- Pts: " +puntaje +" - Personaje: " +personajeElegido.getNombre()
		+" - Tiempo " +crono.getHora()+ ":" +crono.getMinuto() +":"
		+crono.getSegundo() +":" +crono.getCentesima() +" ";
	}
	
	
	

	
	
	

}
