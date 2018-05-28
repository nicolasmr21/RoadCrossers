 package modelo;

import java.awt.Rectangle;
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

public class Vehiculo extends ObjetoJuego{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DERECHA = 1;
	public static final int IZQUIERDA = 2;
	
	public static final String[] urls = {"data/car2.png", "data/car3.png", "data/car5.png",
										"data/car6.png" , "data/car7.png"};
	public static final String TREN = "data/train.png"; 
	int direccion;
	private Vehiculo siguiente;
	

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------


	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param posicionX Se encarga de inicializar la posición en x del vehiculo.
	 * @param posicionY Se encarga de inicializar la posición en y del vehiculo.
	 * @param imagen Se encarga de inicializar la representación de una imagen por medio de un Url.
	 * @param direccion Se encarga de inicializar la direccion a la que se dirige el vehiculo
	 */
	
	
	
	public Vehiculo(int posicionX, int posicionY, String imagen, int direccion) {
		super(posicionX, posicionY, imagen);
		this.direccion = direccion;
	}

	/**
	 * Este método se encarga de darle movimiento a la moneda perteneciente a un
	 * ArrayList con objetos de tipo Vehiculo recibiendo por parametro la cantidad
	 * de automotores.
	 * 
	 * @param cantidad de tipo int.
	*/
	
	public void mover(int cantidad) {
		if(direccion == DERECHA) {
			 super.setPosicionX(super.getPosicionX()+cantidad);
		}
		else {
			 super.setPosicionX(super.getPosicionX()-cantidad);;
		} empezar();
		
       	super.setArea(new Rectangle(super.getPosicionX(), super.getPosicionY(), 100, 40));
       	
       	if(getImagen().equals(TREN)) {
    		super.getArea().setSize(270, 50);}
    		else {
    			super.getArea().setSize(80, 30);
    		}

	}
	
	/**
	 * Este método se encarga de inicializar los vehiculos por medio de condicionales 
	 * que verifican la posicion en X para establecer su posicion "final" por
	 * medio de un rango de medidas ya establecidas señalando hacia izquierda o derecha.
	*/
	
	public void empezar() {
		if(super.getPosicionX() > 1000 && direccion == DERECHA) {
			 super.setPosicionX(0);
		}
		else if(super.getPosicionX() == 0 && direccion == IZQUIERDA) {
			 super.setPosicionX(1200);
		}
	}
	
	/**
	 * Este método se encarga de recorrer el ArrayList de los vehiculos
	 * verificando la condición de null en primera instancia.
	 * 
	 * @param acumulado de tipo ArrayList perteneciente a la clase VehiculoS.
	*/
	
	public void recorrer(ArrayList<Vehiculo> acumulado) {
		if(siguiente!=null) {
			siguiente.recorrer(acumulado);
		}
		acumulado.add(this);
	}
	
	/**
	 * Este método se encarga de dar el vehiculo siguiente perteneciente a un
	 * ArrayList con objetos de tipo Vehiculo.
	 * 
	 * @return siguiente de tipo Vehiculo.
	*/
	
	public Vehiculo getSiguiente() {
		return siguiente;
	}

	/**
	 * Este método se encarga de modificar el vehiculo siguiente perteneciente a un
	 * ArrayList con objetos de tipo Vehiculo.
	 * 
	 * @param siguiente de tipo Vehiculo.
	*/
	
	public void setSiguiente(Vehiculo siguiente) {
		this.siguiente = siguiente;
	}

}
