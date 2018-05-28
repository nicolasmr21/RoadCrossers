package modelo;

import java.awt.Rectangle;
import java.io.Serializable;

/**

 * Esta clase se encarga de las coordenadas, la imagen y el area que 
 * cada objeto del tendrá para su funcionamiento.
 * 

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */

public class ObjetoJuego implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int posicionX;

	private int posicionY;
	private Rectangle area;
	private String imagen;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Este método se encarga de dar construcción a los atributos de esta clase.
	 * 
	 * @param posicionX Se encarga de inicializar la posición en x del vehiculo.
	 * @param posicionY Se encarga de inicializar la posición en y del vehiculo.
	 * @param imagen Se encarga de inicializar la representación de una imagen por medio de un Url.
	 */
	
	
	public ObjetoJuego(int posicionX, int posicionY,  String imagen) {
		super();
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.area = area;
		this.imagen = imagen;
		area = new Rectangle(posicionY, posicionX, 50, 50);
	}
	
	/**
	 * Este método se encarga de dar la posición en x del objeto correspondiente.
	 * 
	 * @return posicionX de tipo int correspondiente al objeto.
	 */

	
	public int getPosicionX() {
		return posicionX;
	}
	
	/**
	 * Este método se encarga de modificar la posición en x del objeto correspondiente.
	 * 
	 * @param posicionX de tipo int.
	 */

	
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	
	/**
	 * Este método se encarga de dar la posición en y del objeto correspondiente.
	 * 
	 * @return posicionY La posición en y correspondiente al objeto.
	 */

	
	public int getPosicionY() {
		return posicionY;
	}
	
	/**
	 * Este método se encarga de modificar la posición en y del objeto correspondiente.
	 * 
	 * @param posicionY de tipo int.
	 */
	
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
	/**
	 * Este método se encarga de dar el area del objeto correspondiente.
	 * 
	 * @return area de tipo Rectangle.
	 */
	
	public Rectangle getArea() {
		return area;
	}
	
	/**
	 * Este método se encarga de modificar el area del objeto correspondiente.
	 * 
	 * @param area de tipo Rectangle.
	 */
	
	
	public void setArea(Rectangle area) {
		this.area = area;
	}
	
	/**
	 * Este método se encarga de dar la imagen del objeto correspondiente.
	 * 
	 * @return imagen de tipo String correspondiente al objeto.
	 */

	
	public String getImagen() {
		return imagen;
	}
	
	/**
	 * Este método se encarga de modificar la imagen del objeto correspondiente.
	 * 
	 * @param imagen de tipo String.
	 */
	
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
