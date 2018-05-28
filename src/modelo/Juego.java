package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import modelo.Excepciones.JugadorNoEncontradoException;
import modelo.Excepciones.JugadorYaExisteException;
import modelo.Excepciones.NombreMuyLargoException;

/**

 * Esta clase se encarga de la realización de la lógica del juego, además de desarrollar
 * busquedas y cargas con base a archivos planos por medio de las listas enlazadas y arboles.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */

public class Juego implements Serializable, IRoadCrossers{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jugador jugadorRaiz;
	private Mapa mapa;
	private Personaje personajeRaiz;
	private Jugador actual;
	
	
	private boolean inicioJuego;
	
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	
	/**
	 * Este método se encarga de dar construcción a un nuevo juego con un jugador raiz.
	 */
	
	public Juego(Jugador j) {
		jugadorRaiz = j;
	}
	
	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * Carga el listado de personajes.
	 * Carga los parametros de todo el juego con sus estadisticas.
	 */
	
	
	public Juego() {
		cargarPersonajes();
		cargarDatos();
	}
	
	/**
	 * Este método se encarga de dar inicio al juego teniendo como parametro un jugador a agregar
	 * convirtiendolo en el jugador actual a jugar.
	 * 
	 * @param j de tipo Jugador.
	 * @throws NombreMuyLargoException al poseer un nombre superior a 15 caracteres
	 * @throws JugadorYaExisteException al encontrar un jugador con el mismo nickname.
	 */
	
	public void iniciarJuego(Jugador j) throws NombreMuyLargoException, JugadorYaExisteException {
		agregarJugador(j);
		actual = j;
	}
	
	/**
	 * Este método se encarga de cargar el juego teniendo como parametro un jugador a cargar
	 * convirtiendolo en el jugador actual a jugar.
	 * 
	 * @param j de tipo Jugador que es convertido en el actual.
	 */
	
	public void cargarJuego(Jugador j) {
		actual = j;
	}
	
	/**
	 * Este método se encarga de iniciar el juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otro método para la realizacion de la busqueda.
	 * 
	 * @param nombre de tipo String que es convertido en el actual.
	 */
	
	public void iniciarJuego(String nombre) {
		actual = buscarJugador(nombre);
	}

	
	/**
	 * Este método se encarga de iniciar el nivel 1 del juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otros métodos para el inicialización del cronometro y el
	 * personaje elegido.
	 * 
	 * @param nombre de tipo String del cual se guia el método para iniciar el nivel.
	 */
	
	public void iniciarNivel1(String nombre) {
		mapa = new Mapa(0, 0, Mapa.MAPA1, Mapa.NIVEL1);
		mapa.cargarMapa();
		actual.iniciarCronometro();
		actual.setPersonajeElegido(buscarPersonaje(nombre));
	}
	
	/**
	 * Este método se encarga de iniciar el nivel 2 del juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otros métodos para el inicialización del cronometro y el
	 * personaje elegido para finalmente ser ubicado en la posición en que se guardó la partida.
	 * 
	 * @param nombre de tipo String del cual se guia el método para iniciar el nivel.
	 */
	
	public void iniciarNivel2(String nombre) {
		mapa = new Mapa(0, 0, Mapa.MAPA2, Mapa.NIVEL2);
		mapa.cargarMapa();
		actual.iniciarCronometro();
		actual.setPersonajeElegido(buscarPersonaje(nombre));
		actual.getPersonajeElegido().setPosicionY(Personaje.Y_INICIAL);
	}
	
	/**
	 * Este método se encarga de iniciar el nivel 3 del juego teniendo como parametro el nickname
	 * o nombre del jugador invocando a otros métodos para el inicialización del cronometro y el
	 * personaje elegido para finalmente ser ubicado en la posición en que se guardó la partida.
	 * 
	 * @param nombre de tipo String del cual se guia el método para iniciar el nivel.
	 */
	
	public void iniciarNivel3(String nombre) {
		mapa = new Mapa(0, 0, Mapa.MAPA3, Mapa.NIVEL3);
		mapa.cargarMapa();
		actual.iniciarCronometro();
		actual.setPersonajeElegido(buscarPersonaje(nombre));
		actual.getPersonajeElegido().setPosicionY(Personaje.Y_INICIAL);
	}

	/**
	 * Este método se encarga de realizar la verificación de nivel por medio de la posicion en y
	 * que se encuentra el personaje manejado por el jugador.
	 * 
	 * @return paso de tipo boolean que confirma con 'true' o 'false el paso al siguiente nivel.
	 */
	
	public boolean pasoDeNivel() {
		boolean paso = false;
		if(actual.getPersonajeElegido().getPosicionY()==0) {
			paso = true;
		}
		return paso;
	}
	
	/**
	 * Este método se encarga de realizar la verificación de intersección entre un vehiculo
	 * y el personaje elegido por el jugador por medio de las coordenadas tanto en X como en Y
	 * retornandolo a la posición inicial del nivel correspondiente.
	 * 
	 * @return intersecto de tipo boolean que confirma con 'true' o 'false verificando la interseción.
	 */
	
	public boolean seIntersecto() {
		ArrayList<Vehiculo> vehiculos = mapa.obtenerListaVehiculos();
		boolean intersecto = false;
		for (int i = 0; i < vehiculos.size() && !intersecto; i++) {
			if(actual.getPersonajeElegido().getArea().intersects(vehiculos.get(i).getArea())) {
				intersecto = true;
				actual.setVida(actual.getVida()-5);
				actual.getPersonajeElegido().setPosicionX(Personaje.X_INICIAL);
				actual.getPersonajeElegido().setPosicionY(Personaje.Y_INICIAL);
			}
		}
		return intersecto;
	}
	
	/**
	 * Este método se encarga de realizar la verificación de intersección entre una moneda
	 * y el personaje elegido por el jugador por medio de las coordenadas tanto en X como en Y
	 * aumentadole su puntuación que es aleatoria en cada moneda.
	 * 
	 * @return intersecto de tipo boolean que confirma con 'true' o 'false verificando la interseción.
	 */
	
	
	public boolean atrapoMoneda() {
		ArrayList<Moneda> monedas = mapa.obtenerListaMonedas();
		boolean intersecto = false;
		for (int i = 0; i < monedas.size() && !intersecto; i++) {
			if(actual.getPersonajeElegido().getArea().intersects(monedas.get(i).getArea())) {
				intersecto = true;
				actual.aumentarPuntaje(monedas.get(i).getValor());
				Moneda m = mapa.buscarMoneda(monedas.get(i));	
				m.setPosicionX(-100);
				m.setPosicionY(-100);	
		}}
		return intersecto;
	}
	
	/**
	 * Este método se encarga de realizar la busqueda del personaje seleccionado
	 * por el jugador por medio del parametro nombre correspondiente al personaje.
	 * 
	 * @param nombre de tipo String correspondiente al nombre del personaje.
	 * @return personajeRaiz de tipo Personaje con base a la busqueda realizada
	 * en el arbol.
	 */
	
	public Personaje buscarPersonaje(String nombre) {
		return personajeRaiz==null?null:personajeRaiz.buscarPersonaje(nombre);
	}
	
	/**
	 * Este método se encarga de realizar la adición del personaje al arbol binario
	 * verficando  en primera instancia algún nodo en null para ser depositado.
	 * 
	 * @param p de tipo Personaje para el funcionamiento del método.
	 */
	
	public void agregarPersonaje(Personaje p) {
		if(personajeRaiz==null) {
			personajeRaiz = p;
		}
		else {
			personajeRaiz.insertar(p);	
		}
	}
	
	/**
	 * Este método se encarga de realizar la carga de personajes depositados en un
	 * archivo plano (txt) el cual separa el mensaje en palabras que reconoce
	 * el programa para el funcionamiento de este.
	 * 
	 * 
	 * @throws e de tipo FileNotFoundException al no encontrar un archivo plano con los personajes.
	 */
	
	public void cargarPersonajes() {
		File file = new File ("archivos/personajes.txt");		
		try {
			FileReader reader = new FileReader(file); 
			BufferedReader br = new BufferedReader(reader); 
			String line = "";
			String[] mensaje;
			while((line = br.readLine()) != null){
				mensaje = line.split(" ");
				agregarPersonaje(new Personaje(mensaje[0], Integer.parseInt(mensaje[1]), 
						Integer.parseInt(mensaje[2]), Integer.parseInt(mensaje[3]), 
						mensaje[4], mensaje[5], mensaje[6], mensaje[7], mensaje[8]));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Este método se encarga de realizar la carga de propiedades del juego
	 * depositadas en un archivo plano (txt) el cual reconoce el arbol de jugadores
	 * para el funcionamiento de este.
	 * 
	 * @throws e de tipo FileNotFoundException propia de la clase java.
	 * @throws e de tipo ClassNotFoundException propia de la clase java.
	 * @throws e de tipo IOException propia de la clase java.
	 */
	
	public void cargarDatos() {
		 FileInputStream fileInStr = null;
	        ObjectInputStream entrada = null;
	        Juego j;

	        try {

	        	fileInStr = new FileInputStream("archivos/properties.txt");
	            entrada = new ObjectInputStream(fileInStr);
	            
	            j= (Juego) entrada.readObject();
	            if(j!=null) {
	            jugadorRaiz = j.getJugadorRaiz();
	            inicioJuego = true;}
	            
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (ClassNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	                if (fileInStr != null) {
	                	fileInStr.close();
	                }
	                if (entrada != null) {
	                    entrada.close();
	                }
	            } catch (IOException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	}
	
	
	/**
	 * Este método se encarga de dar el jugadorRaiz perteneciente al arbol de Jugadores
	 *
	 *@return jugadorRaiz de tipo Jugador.
	 */
	
	public Jugador getJugadorRaiz() {
		return jugadorRaiz;
	}

	/**
	 * Este método se encarga de realizar el almacenamiento de propiedades del juego
	 * depositadas en un archivo plano (txt).
	 * 
	 * @throws e de tipo FileNotFoundException propia de la clase java.
	 * @throws e de tipo IOException propia de la clase java.
	 */
	public void guardarDatos() {
		
		FileOutputStream fileOutS = null;
		ObjectOutputStream salida = null;
		Juego juego = this;
		try
		{
			fileOutS = new FileOutputStream("archivos/properties.txt");
			salida = new ObjectOutputStream(fileOutS);
			salida.writeObject(this);
		
			
		}catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}finally
		{
			try {
				if (juego != null)
					fileOutS.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	/**
	 * Este método se encarga de agregar un jugador al arbol teniendo como parametro un jugador a agregar
	 * teniendo en cuenta condiciones para lanzar excepciones y luego de estas insertarlas en el arbol.
	 * 
	 * @param j de tipo Jugador.
	 * @throws NombreMuyLargoException al poseer un nombre superior a 15 caracteres
	 * @throws JugadorYaExisteException al encontrar un jugador con el mismo nickname.
	 */
	
	
	public void agregarJugador(Jugador j) 
		throws NombreMuyLargoException, JugadorYaExisteException{
		
		if(buscarJugador(j.getNombre())!=null) {throw new JugadorYaExisteException(j.getNombre()); }
		char[] c = j.getNombre().toCharArray();
		if(c.length>15) {throw new NombreMuyLargoException(j.getNombre());}
		
		if(jugadorRaiz==null) {
			jugadorRaiz = j;
		}
		else {
			jugadorRaiz.insertar(j);	
		}
	}
	
	/**
	 * Este método se encarga de obtener los jugadores del juego verificando en primera
	 * instancia si la raiz del arbol es null. Es decir, el arbol es virgen.
	 * 
	 * @return jugadores de tipo ArrayList pertenicientes a la clase Jugador.
	 * @return null en el caso en que el arbol se encuentre vacio.
	 */
	
	
	public ArrayList<Jugador> obtenerListaJugadores() {
		if(jugadorRaiz == null) {
			return null;
		}else {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadorRaiz.inorden(jugadores);		
		return jugadores;
		}
	}
	
	/**
	 * Este método se encarga de realizar la busqueda del jugador por medio 
	 * del parametro nombre correspondiente al jugador.
	 * 
	 * @param nombre de tipo String correspondiente al nombre del jugador.
	 * @return jugadorRaiz de tipo Jugador con base a la busqueda realizada
	 * en el arbol.
	 */
	
	public Jugador buscarJugador(String nombre) {
		return jugadorRaiz==null?null:jugadorRaiz.buscarJugador(nombre);
	}
	
	/**
	 * Este método se encarga de realizar la busqueda del jugador por medio 
	 * del parametro nombre correspondiente al jugador para ser eliminado.
	 * 
	 * @param nombre de tipo String correspondiente al nombre del jugador.
	 * @throws JugadorNoEncontradoException al no encontrar el jugador con el nombre buscado.
	 */
	
	public void eliminarJugador(String nombre) throws JugadorNoEncontradoException {
		jugadorRaiz = jugadorRaiz.eliminarJugador(nombre);
	}
	
	/**
	 * Este método se encarga de realizar el almacenamiento de puntajes depositados en un
	 * archivo plano (txt) el cual tiene en cuneta el nombre del jugador y su puntaje
	 * correspondiente.
	 * 
	 * 
	 * @throws e de tipo IOException excepción propia de java.
	 */
	
	public void guardarPuntaje(){
		
		try {
			FileWriter fw = new FileWriter("archivos/records.txt", true); 
			BufferedWriter bw = new BufferedWriter(fw); 
			PrintWriter p= new PrintWriter(bw);
			p.write(actual.getNombre() + 
					" " +actual.getPuntaje());
			p.println();
			bw.close();
			p.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	
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
	
	public ArrayList<Jugador> importarPuntajes(){
		File file = new File ("archivos/records.txt");		
		ArrayList<Jugador> jugadores= new  ArrayList<Jugador>();
		try {
			FileReader reader = new FileReader(file); 
			BufferedReader br = new BufferedReader(reader); 
			String line = "";
			String[] mensaje;
			while((line = br.readLine()) != null){
				mensaje = line.split(" ");
				jugadores.add(new Jugador(mensaje[0], Integer.parseInt(mensaje[1])));
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			
		}
		
		return jugadores;
	}
	

	/**
	 * Este método se encarga de realizar un ordenamiento del arreglo de jugadores
	 * con base a su puntaje ordenandolos descendientemente partiendo del puntaje mas
	 * alto hasta el mas bajo.
	 *
	 * @return ordenados de tipo Array con los jugadores ordenados por su puntaje
	 */
	
	
	public Jugador[] ordenarPorPuntaje(ArrayList<Jugador> jg){
		
		ArrayList<Jugador> jugadores = jg;
		
		Jugador[] ordenados = new Jugador[jugadores.size()];
		ordenados = jugadores.toArray(ordenados);
	
		
		for (int i = 1; i < ordenados.length; i++) {
			for (int j = i; j>0 && (ordenados[j].compareTo(ordenados[j-1]) > 0); j-- ) {
				Jugador temp = ordenados[j];
				ordenados[j] = ordenados[j-1];
				ordenados[j-1] = temp;
			}
		}
		
		return ordenados;
	}
	
	/**
	 * Este método se encarga de realizar un ordenamiento del arreglo de jugadores
	 * con base a su nombre ordenandolos descendientemente organizandolos de manera
	 * alfanumerica.
	 * 
	 * @return ordenados de tipo Array con los jugadores ordenados por su puntaje
	 */
	
	public Jugador[] ordenarPorNombre(ArrayList<Jugador> jg){
		
		ArrayList<Jugador> jugadores = jg;
		
		Jugador[] ordenados = new Jugador[jugadores.size()];
		ordenados = jugadores.toArray(ordenados);
	
		
		for (int i = 1; i < ordenados.length; i++) {
			for (int j = i; j>0 && (ordenados[j].getNombre().compareToIgnoreCase(ordenados[j-1].getNombre()) < 0); j-- ) {
				Jugador temp = ordenados[j];
				ordenados[j] = ordenados[j-1];
				ordenados[j-1] = temp;
			}
		}
		
		return ordenados;
	}
	
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
			throws JugadorNoEncontradoException{
		Jugador j = null;
		
		boolean encontre = false;
		int inicio = 0;
		int fin = jugadores.length-1;
		
		while(!encontre && inicio<=fin) {
			int medio= (inicio+fin)/2;
			if(jugadores[medio].getPuntaje() == puntaje) {
				encontre = true; return jugadores[medio]; 
			}
			else if(jugadores[medio].getPuntaje() < puntaje) {
				inicio = medio+1; 
			}
			else if(jugadores[medio].getPuntaje() > puntaje) {
				fin = medio-1; 
			} }
		
		if(j==null) {
			throw new JugadorNoEncontradoException(puntaje);
		}
		
		return j;
	}
	
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
			throws JugadorNoEncontradoException{
		Jugador j = null;
		
		boolean encontre = false;
		int inicio = 0;
		int fin = jugadores.length-1;
		
		while(!encontre && inicio<=fin) {
			int medio= (inicio+fin)/2;
			if(jugadores[medio].getNombre().compareToIgnoreCase(nombre) == 0) {
				encontre = true; return jugadores[medio]; 
			}
			else if(jugadores[medio].getNombre().compareToIgnoreCase(nombre) > 0) {
				fin = medio-1; 
			}
			else {
				inicio = medio+1; 
			} }
		
		if(j==null) {
			throw new JugadorNoEncontradoException(nombre);
		}
		
		return j;
	}
	
	/**
	 * Este método se encarga de dar el jugador actual para el funcionamiento del juego..
	 * 
	 * @return actual de tipo Jugador.
	 */
	
	
	public Jugador getJugador() {
		return actual;
	}

	/**
	 * Este método se encarga de dar el mapa para el funcionamiento del juego..
	 * 
	 * @return mapa de tipo Mapa.
	 */
	
	public Mapa getMapa() {
		return mapa;
	}

	/**
	 * Este método se encarga de modificar el mapa ecorrespondiente.
	 * 
	 * @param mapa de tipo Mapa.
	 */

	
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	/**
	 * Este método se encarga de dar el personajeRaiz para el funcionamiento del juego.
	 * 
	 * @return personajeRaiz de tipo Persnonaje.
	 */
	
	
	public Personaje getPersonajeRaiz() {
		return personajeRaiz;
	}

	/**
	 * Este método se encarga de modificar el personaje ecorrespondiente.
	 * 
	 * @param personaje de tipo Personaje.
	 */
	
	public void setPersonaje(Personaje personaje) {
		this.personajeRaiz = personaje;
	}

	/**
	 * Este método se encarga de dar la verificacion de incio del juego para su funcionamiento.
	 * 
	 * @return inicioJuego de tipo boolean.
	 */
	
	public boolean isInicioJuego() {
		return inicioJuego;
	}

	/**
	 * Este método se encarga de modificar la verificacion de incio del juego para su funcionamiento.
	 * 
	 * @param inicioJuego de tipo boolean.
	 */
	
	public void setInicioJuego(boolean inicioJuego) {
		this.inicioJuego = inicioJuego;
	}

	public void setJugadorRaiz(Jugador jugadorRaiz) {
		this.jugadorRaiz = jugadorRaiz;
	}

	public void setActual(Jugador actual) {
		this.actual = actual;
	}

	

	
}
