package modelo;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

/**

 * Esta clase se encarga de la realización del valor, el movimiento y la inicializacion aleatoria
 * de las monedas por medio de las listas enlazadas

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */

public class Moneda extends ObjetoJuego  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int VALOR_MAXIMO = 50;
	public static final String URL = "data/coin.png";
	
	
	private int valor;
	private Moneda siguiente;
	private Moneda anterior;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param posicionX Se encarga de inicializar la posición en x del vehiculo.
	 * @param posicionY Se encarga de inicializar la posición en y del vehiculo.
	 * @param imagen Se encarga de inicializar la representación de una imagen por medio de un Url.
	 * @param valor Se encarga de inicializar el valor que poseera la moneda de tipo int.
	 */
	
	
	public Moneda(int posicionX, int posicionY, String imagen, int valor) {
		super(posicionX, posicionY, imagen);
		this.valor = valor;
		super.getArea().setSize(20, 20);
	}
	
	/**
	 * Este método se encarga de recorrer el ArrayList de las monedas
	 * verificando la condición de null en primera instancia.
	 * 
	 * @param acumulado de tipo ArrayList perteneciente a la clase moneda.
	*/
	
	
	public void recorrer(ArrayList<Moneda> acumulado) {
		if(siguiente!=null) {
			siguiente.recorrer(acumulado);
		}
		acumulado.add(this);
	}

	/**
	 * Este método se encarga de dar la moneda siguiente perteneciente a un
	 * ArrayList con objetos de tipo Moneda.
	 * 
	 * @return siguiente de tipo Moneda.
	*/
	
	public Moneda getSiguiente() {
		return siguiente;
	}

	/**
	 * Este método se encarga de modificar la moneda siguiente perteneciente a un
	 * ArrayList con objetos de tipo Moneda.
	 * 
	 * @param siguiente de tipo Moneda.
	*/
	
	
	public void setSiguiente(Moneda siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * Este método se encarga de darle movimiento a la moneda perteneciente a un
	 * ArrayList con objetos de tipo Moneda recibiendo por parametro la coordenanda x
	 * ya establecida.
	 * 
	 * @param x de tipo int.
	*/
	
	public void mover(int x) {
       	int y = (int)(Math.random()*((4)+1));
       	
       	if(y == 1) {
       		super.setPosicionX(super.getPosicionX()+1);
       		super.setPosicionY(super.getPosicionY()+1);

       	}else if(y == 2) {
       		super.setPosicionX(super.getPosicionX()+1);
       		super.setPosicionY(super.getPosicionY()-1);
       	}
       	else if(y == 3) {
       		super.setPosicionX(super.getPosicionX()-1);
       		super.setPosicionY(super.getPosicionY()+1);
       	}else {
       		super.setPosicionX(super.getPosicionX()-1);
       		super.setPosicionY(super.getPosicionY()-1);
       	}
       	super.setArea(new Rectangle(super.getPosicionX(), super.getPosicionY(), 20, 20));
       	
       	empezar();
	}
	
	/**
	 * Este método se encarga de inicializar las monedas por medio de condicionales 
	 * que verifican la posicion en X y en Y para establecer su posicion "final" por
	 * medio de un rango de medidas ya establecidas.
	*/
	
	public void empezar() {
		if(super.getPosicionX() > 950 ) {
			 super.setPosicionX(0);}
		if(super.getPosicionX() <= 0 ) {
			 super.setPosicionX(950);}
		if(super.getPosicionY() > 680 ) {
			 super.setPosicionX(0);}
		if(super.getPosicionY() <= 0 ) {
			 super.setPosicionX(680);}
		
	}

	/**
	 * Este método se encarga de dar la moneda anterior perteneciente a un
	 * ArrayList con objetos de tipo Moneda.
	 * 
	 * @return anterior de tipo Moneda.
	*/
	
	public Moneda getAnterior() {
		return anterior;
	}

	/**
	 * Este método se encarga de modificar la moneda anterior perteneciente a un
	 * ArrayList con objetos de tipo Moneda.
	 * 
	 * @param anterior de tipo Moneda.
	*/
	
	public void setAnterior(Moneda anterior) {
		this.anterior = anterior;
	}

	/**
	 * Este método se encarga de obtener el valor de la moneda perteneciente a un
	 * ArrayList con objetos de tipo Moneda.
	 * 
	 * @return valor de tipo int.
	*/
	
	public int getValor() {
		return valor;
	}

	/**
	 * Este método se encarga de modificar el valor de la moneda perteneciente a un
	 * ArrayList con objetos de tipo Moneda.
	 * 
	 * @param valor de tipo int.
	*/
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	

	
	

}
