package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;



public class Mapa extends ObjetoJuego implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String MAPA1 = "data/nivel1.png";
	public final static String MAPA2 = "data/nivel2.png";
	public final static String MAPA3 = "data/nivel3.png";
	public final static int NIVEL1= 1;
	public final static int NIVEL2= 2;
	public final static int NIVEL3= 3;

	private Vehiculo primerVehiculo;
	private ArrayList<Integer> coordenadasY;
	private Moneda primerMoneda;
	private int numVehiculos;
	private int numCalles;
	private int numMonedas;
	private int nivel;
	
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param posicionX Se encarga de inicializar la posición en x del mapa.
	 * @param posicionY Se encarga de inicializar la posición en y del mapa.
	 * @param imagen Se encarga de inicializar la representación una imagen por medio de un Url.
	 * @param nivel Se encarga de inicializar el nivel en que se encuentra del mapa.
	 */
	
	public Mapa(int posicionX, int posicionY, String imagen, int nivel) {
		super(posicionX, posicionY, imagen);
		this.nivel = nivel;
		
	}
	
	public void cargarMapa() {
		cargarCoordenadas();
		cargarVehiculos();
		cargarMonedas();
	}
	
	/**
	 * Este método se encarga de cargar los vehiculos dependiendo del nivel en el que
	 * se encuentre el jugador ubicando sus coordenadas de manera aleatoria con su respectiva
	 * imagen.
	 */

	
	public void cargarVehiculos() {
		primerVehiculo=null;
		for(int i=0;i<numCalles;i++){
			int cord = (int)(Math.random()*((700)+1));
			int url = (int)(Math.random()*((5)));
            if((i%2)==0){
                agregarVehiculo(new Vehiculo(cord, coordenadasY.get(i), Vehiculo.urls[url], Vehiculo.IZQUIERDA));
            }
            else{
                agregarVehiculo(new Vehiculo(cord, coordenadasY.get(i), Vehiculo.urls[url], Vehiculo.DERECHA));
            }
        } 
		
		if(nivel == 2) {
            agregarVehiculo(new Vehiculo(50, 200, Vehiculo.TREN, Vehiculo.DERECHA));

		}
		if(nivel == 3) {
            agregarVehiculo(new Vehiculo(50, 115, Vehiculo.TREN, Vehiculo.DERECHA));
            agregarVehiculo(new Vehiculo(50, 315, Vehiculo.TREN, Vehiculo.IZQUIERDA));

		}
	}
	
	/**
	 * Este método se encarga de cargar las monedas dependiendo del nivel en el que
	 * se encuentre el jugador ubicando sus coordenadas de manera aleatoria con su respectiva
	 * imagen.
	 */
	
	public void cargarMonedas() {
		primerMoneda=null;
		 for(int i=0;i<10;i++){
	           	int XCoord = (int)(Math.random()*((1000)+1));
	            int yCoord = (int)(Math.random()*((700)+1));
	            int valor = (int)(Math.random()*((50)+1));
	            agregarMoneda(new Moneda(XCoord+50, yCoord+50, Moneda.URL, valor ));
	        }
	}
	
	/**
	 * Este método se encarga de dar el nivel en el que se encuentra desarrollando la partida.
	 * @return nivel de tipo int.
	 */
	
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Este método se encarga de cargar las coordernadas dependiendo del nivel en el que
	 * se encuentre desarrollandose por medio de casos que representan los niveles a los
	 * que se puede enfrentar el jugador.
	 */

	public void cargarCoordenadas(){
	        coordenadasY = new ArrayList<>();
	        switch(nivel){
	            case 1:
	                numCalles=8;
	                coordenadasY.add(115);
	                coordenadasY.add(170);
	                coordenadasY.add(220);
	                coordenadasY.add(270);
	                coordenadasY.add(425);
	                coordenadasY.add(480);
	                coordenadasY.add(535);
	                coordenadasY.add(685);
	                break;
	            
	            case 2:
	                numCalles = 8;
	                coordenadasY.add(560);
	                coordenadasY.add(515);
	                coordenadasY.add(460);
	                coordenadasY.add(410);
	                coordenadasY.add(370);
	                coordenadasY.add(315);
	                coordenadasY.add(62);
	                coordenadasY.add(10);
	                break;
	            case 3: case 4:
	                numCalles = 8;
	                coordenadasY.add(565);
	                coordenadasY.add(515);
	                coordenadasY.add(465);
	                coordenadasY.add(415);
	                coordenadasY.add(260);
	                coordenadasY.add(210);
	                coordenadasY.add(55);
	                coordenadasY.add(5);
	        }
	    }
	
	
	/**
	 * Este método se encarga de dar la lista de vehiculos en el mapa
	 * para el desarrollo de la partida.
	 * 
	 * @return vehiculos de tipo ArrayList perteneciente a la clase Vehiculo.
	 */

	public ArrayList<Vehiculo> obtenerListaVehiculos() {
		if(numVehiculos == 0) {
			return null;
		}else {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		primerVehiculo.recorrer(vehiculos);		
		return vehiculos;
		}
	}
	
	/**
	 * Este método se encarga de dar la lista de monedas en el mapa
	 * para el desarrollo de la partida.
	 * 
	 * @return monedas de tipo ArrayList perteneciente a la clase Moneda.
	 */
	
	public ArrayList<Moneda> obtenerListaMonedas() {
		if(numMonedas == 0) {
			return null;
		}else {
		ArrayList<Moneda> monedas = new ArrayList<Moneda>();
		primerMoneda.recorrer(monedas);		
		return monedas;
		}
	}

	
	/**
	 * Este método se encarga de agregar un vehiculo a la lista enlazada
	 * la cual revisa en primera instancia las posiciones vacias.
	 * 
	 * @param v tipo Vehiculo que ingresa para ser añadido.
	 */
	
	public void agregarVehiculo(Vehiculo v) {
		if(primerVehiculo!=null) {
			v.setSiguiente(primerVehiculo);
			primerVehiculo = v;
		}
		else { primerVehiculo = v;}
		numVehiculos++;
	}
	
	/**
	 * Este método se encarga de agregar una moneda a la lista enlazada
	 * la cual revisa en primera instancia las posiciones vacias.
	 * 
	 * @param m tipo Moneda que ingresa para ser añadida.
	 */
	
	public void agregarMoneda(Moneda m) {
		if(primerMoneda!=null) {
			primerMoneda.setAnterior(m);
			m.setSiguiente(primerMoneda);
			primerMoneda = m;
		}
		else { primerMoneda = m;}
		numMonedas++;
	}
	
	/**
	 * Este método se encarga de buscar una moneda a la lista enlazada
	 * la cual revisa en primera instancia las posiciones vacias para finalmente
	 * por medio de un boolean avisar que se encontró.
	 * 
	 * @param m tipo Moneda que ingresada a ser buscada.
	 */
	
	public Moneda buscarMoneda(Moneda m) {
		Moneda actual = primerMoneda;
		boolean encontrado = false;
		
		for (; actual!=null && !encontrado; ) {
			if(actual == m) {
				encontrado = true;
			}
			else {
				actual = actual.getSiguiente();
			}
		}
		return actual;
	}
	
	/**
	 * Este método se encarga de obtener el primer vehiculo de
	 * la lista enlazada.
	 * 
	 * @return primerVehiculo de tipo Vehiculo.
	 */
	
	public Vehiculo getPrimerVehiculo() {
		return primerVehiculo;
	}

	/**
	 * Este método se encarga de modificar el primer vehiculo de
	 * la lista enlazada.
	 * 
	 * @param primerVehiculo de tipo Vehiculo.
	 */
	
	public void setPrimerVehiculo(Vehiculo primerVehiculo) {
		this.primerVehiculo = primerVehiculo;
	}

	/**
	 * Este método se encarga de obtener la primera moneda de
	 * la lista enlazada.
	 * 
	 * @return primerMoneda de tipo Moneda.
	 */
	
	public Moneda getPrimerMoneda() {
		return primerMoneda;
	}

	/**
	 * Este método se encarga de modificar la primera moneda de
	 * la lista enlazada.
	 * 
	 * @param primerMoneda de tipo Moneda.
	 */
	
	public void setPrimerMoneda(Moneda primerMoneda) {
		this.primerMoneda = primerMoneda;
	}

	/**
	 * Este método se encarga de obtener el numero de vehiculos en
	 * el mapa.
	 * 
	 * @return numVehiculos de tipo int.
	 */
	
	public int getNumVehiculos() {
		return numVehiculos;
	}

	/**
	 * Este método se encarga de obtener el numero de monedas en
	 * el mapa.
	 * 
	 * @return numMonedas de tipo int.
	 */
	
	public int getNumMonedas() {
		return numMonedas;
	}

	/**
	 * Permite conocer las coordenadas disponibles para los vehiculos
	 * @return un arraylist de enteror con las coordenadas.
	 */
	public ArrayList<Integer> getCoordenadasY() {
		return coordenadasY;
	}
	
	
	

}
