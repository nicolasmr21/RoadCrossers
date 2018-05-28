package modelo;

import java.util.ArrayList;

import modelo.Excepciones.JugadorNoEncontradoException;
import modelo.Excepciones.JugadorYaExisteException;
import modelo.Excepciones.NombreMuyLargoException;


/**

 * Esta interface representa el contrato funcional del juego.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public interface IRoadCrossers {


	/**
	 * Este método se encarga de dar inicio al juego teniendo como parametro un jugador a agregar
	 * convirtiendolo en el jugador actual a jugar.
	 * 
	 * @param j de tipo Jugador.
	 * @throws NombreMuyLargoException al poseer un nombre superior a 15 caracteres
	 * @throws JugadorYaExisteException al encontrar un jugador con el mismo nickname.
	 */
	
	public void iniciarJuego(Jugador j) throws NombreMuyLargoException, JugadorYaExisteException;
	
	/**
	 * Este método se encarga de cargar el juego teniendo como parametro un jugador a cargar
	 * convirtiendolo en el jugador actual a jugar.
	 * 
	 * @param j de tipo Jugador que es convertido en el actual.
	 */
	
	public void cargarJuego(Jugador j);
	
	/**
	 * Este método se encarga de iniciar el juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otro método para la realizacion de la busqueda.
	 * 
	 * @param nombre de tipo String que es convertido en el actual.
	 */
	
	public void iniciarJuego(String nombre);

	
	/**
	 * Este método se encarga de iniciar el nivel 1 del juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otros métodos para el inicialización del cronometro y el
	 * personaje elegido.
	 * 
	 * @param nombre de tipo String del cual se guia el método para iniciar el nivel.
	 */
	
	public void iniciarNivel1(String nombre);
	
	/**
	 * Este método se encarga de iniciar el nivel 2 del juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otros métodos para el inicialización del cronometro y el
	 * personaje elegido para finalmente ser ubicado en la posición en que se guardó la partida.
	 * 
	 * @param nombre de tipo String del cual se guia el método para iniciar el nivel.
	 */
	
	public void iniciarNivel2(String nombre);
	
	/**
	 * Este método se encarga de iniciar el nivel 3 del juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otros métodos para el inicialización del cronometro y el
	 * personaje elegido para finalmente ser ubicado en la posición en que se guardó la partida.
	 * 
	 * @param nombre de tipo String del cual se guia el método para iniciar el nivel.
	 */
	
	public void iniciarNivel3(String nombre) ;

	/**
	 * Este método se encarga de realizar la verificación de nivel por medio de la posicion en y
	 * que se encuentra el personaje manejado por el jugador.
	 * 
	 * @return paso de tipo boolean que confirma con 'true' o 'false el paso al siguiente nivel.
	 */
	
	public boolean pasoDeNivel();
	
	/**
	 * Este método se encarga de realizar la verificación de intersección entre un vehiculo
	 * y el personaje elegido por el jugador por medio de las coordenadas tanto en X como en Y
	 * retornandolo a la posición inicial del nivel correspondiente.
	 * 
	 * @return intersecto de tipo boolean que confirma con 'true' o 'false verificando la interseción.
	 */
	
	public boolean seIntersecto();
	
	/**
	 * Este método se encarga de realizar la verificación de intersección entre una moneda
	 * y el personaje elegido por el jugador por medio de las coordenadas tanto en X como en Y
	 * aumentadole su puntuación que es aleatoria en cada moneda.
	 * 
	 * @return intersecto de tipo boolean que confirma con 'true' o 'false verificando la interseción.
	 */
	
	
	public boolean atrapoMoneda();
	
	/**
	 * Este método se encarga de realizar la busqueda del personaje seleccionado
	 * por el jugador por medio del parametro nombre correspondiente al personaje.
	 * 
	 * @param nombre de tipo String correspondiente al nombre del personaje.
	 * @return personajeRaiz de tipo Personaje con base a la busqueda realizada
	 * en el arbol.
	 */
	
	public Personaje buscarPersonaje(String nombre);
	
	/**
	 * Este método se encarga de realizar la adición del personaje al arbol binario
	 * verficando  en primera instancia algún nodo en null para ser depositado.
	 * 
	 * @param p de tipo Personaje para el funcionamiento del método.
	 */
	
	public void agregarPersonaje(Personaje p);
	
	/**
	 * Este método se encarga de realizar la carga de personajes depositados en un
	 * archivo plano (txt) el cual separa el mensaje en palabras que reconoce
	 * el programa para el funcionamiento de este.
	 * 
	 * 
	 * @throws e de tipo FileNotFoundException al no encontrar un archivo plano con los personajes.
	 */
	
	public void cargarPersonajes();
	
	/**
	 * Este método se encarga de realizar la carga de propiedades del juego
	 * depositadas en un archivo plano (txt) el cual reconoce el arbol de jugadores
	 * para el funcionamiento de este.
	 * 
	 * @throws e de tipo FileNotFoundException propia de la clase java.
	 * @throws e de tipo ClassNotFoundException propia de la clase java.
	 * @throws e de tipo IOException propia de la clase java.
	 */
	
	public void cargarDatos();
	
	/**
	 * Este método se encarga de dar el jugadorRaiz perteneciente al arbol de Jugadores
	 *
	 *@return jugadorRaiz de tipo Jugador.
	 */
	
	public Jugador getJugadorRaiz();
	/**
	 * Este método se encarga de realizar el almacenamiento de propiedades del juego
	 * depositadas en un archivo plano (txt).
	 * 
	 * @throws e de tipo FileNotFoundException propia de la clase java.
	 * @throws e de tipo IOException propia de la clase java.
	 */
	public void guardarDatos();
	/**
	 * Este método se encarga de agregar un jugador al arbol teniendo como parametro un jugador a agregar
	 * teniendo en cuenta condiciones para lanzar excepciones y luego de estas insertarlas en el arbol.
	 * 
	 * @param j de tipo Jugador.
	 * @throws NombreMuyLargoException al poseer un nombre superior a 15 caracteres
	 * @throws JugadorYaExisteException al encontrar un jugador con el mismo nickname.
	 */
	
	
	public void agregarJugador(Jugador j) 
		throws NombreMuyLargoException, JugadorYaExisteException;
	
	/**
	 * Este método se encarga de obtener los jugadores del juego verificando en primera
	 * instancia si la raiz del arbol es null. Es decir, el arbol es virgen.
	 * 
	 * @return jugadores de tipo ArrayList pertenicientes a la clase Jugador.
	 * @return null en el caso en que el arbol se encuentre vacio.
	 */
	
	
	public ArrayList<Jugador> obtenerListaJugadores() ;
	
	/**
	 * Este método se encarga de realizar la busqueda del jugador por medio 
	 * del parametro nombre correspondiente al jugador.
	 * 
	 * @param nombre de tipo String correspondiente al nombre del jugador.
	 * @return jugadorRaiz de tipo Jugador con base a la busqueda realizada
	 * en el arbol.
	 */
	
	public Jugador buscarJugador(String nombre);
	
	/**
	 * Este método se encarga de realizar la busqueda del jugador por medio 
	 * del parametro nombre correspondiente al jugador para ser eliminado.
	 * 
	 * @param nombre de tipo String correspondiente al nombre del jugador.
	 * @throws JugadorNoEncontradoException al no encontrar el jugador con el nombre buscado.
	 */
	
	public void eliminarJugador(String nombre) throws JugadorNoEncontradoException;
	
	/**
	 * Este método se encarga de realizar el almacenamiento de puntajes depositados en un
	 * archivo plano (txt) el cual tiene en cuneta el nombre del jugador y su puntaje
	 * correspondiente.
	 * 
	 * 
	 * @throws e de tipo IOException excepción propia de java.
	 */
	
	public void guardarPuntaje();
	
	
	/**
	 * Este método se encarga de realizar la importación de puntajes depositados en un
	 * archivo plano (txt) de records el cual tiene en cuneta el nombre del jugador y su puntaje
	 * correspondiente.
	 * 
	 * 
	 * @throws e de tipo IOException excepción propia de java.
	 * @throws e de tipo FileNotFoundException excepción propia de java.
	 * 
	 * @return jugadores de tipo ArrayList pertenciente a la clase Jugador.
	 */
	
	public ArrayList<Jugador> importarPuntajes();

	/**
	 * Este método se encarga de realizar un ordenamiento del arreglo de jugadores
	 * con base a su puntaje ordenandolos descendientemente partiendo del puntaje mas
	 * alto hasta el mas bajo.
	 *
	 * @return ordenados de tipo Array con los jugadores ordenados por su puntaje
	 */
	
	
	public Jugador[] ordenarPorPuntaje(ArrayList<Jugador> jg);
	
	/**
	 * Este método se encarga de realizar un ordenamiento del arreglo de jugadores
	 * con base a su nombre ordenandolos descendientemente organizandolos de manera
	 * alfanumerica.
	 * 
	 * @return ordenados de tipo Array con los jugadores ordenados por su puntaje
	 */
	
	public Jugador[] ordenarPorNombre(ArrayList<Jugador> jg);
	
	/**
	 * Este método se encarga de realizar la busqueda del jugador por medio del arreglo
	 * con base a su atributo su puntaje.
	 * 
	 * @param jugadores de tipo Array pertenciente a la clase Jugador.
	 * @param puntaje de tipo int perteneciente a Jugador.
	 * @throws e de tipo JugadorNoEncontradoException al no existir un jugador con el nombre
	 * buscado.
	 * @return j de tipo Jugador el cual ha sido buscado. 
	 */
	
	public Jugador buscarRecordJugadorPorPuntaje(Jugador[] jugadores, int puntaje) 
			throws JugadorNoEncontradoException;
	
	/**
	 * Este método se encarga de realizar la busqueda del jugador por medio del arreglo
	 * con base a su atributo nombre.
	 * 
	 * @param jugadores de tipo Array pertenciente a la clase Jugador.
	 * @param nombre de tipo String perteneciente a Jugador.
	 * @throws e de tipo JugadorNoEncontradoException al no existir un jugador con el nombre
	 * buscado.
	 * @return j de tipo Jugador el cual ha sido buscado. 
	 */
	
	public Jugador buscarRecordJugadorPorNombre(Jugador[] jugadores, String nombre) 
			throws JugadorNoEncontradoException;

	/**
	 * Metodo que permite obtener el mapa del juego.
	 * @return un objeto de tipo Mapa.
	 */
	public Mapa getMapa();

	/**
	 * Metodo que permite obtener el jugador actual del juego.
	 * @return un objeto de tipo Jugador
	 */
	public Jugador getJugador();
	
}
