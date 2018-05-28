package interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import modelo.Cronometro;
import modelo.Jugador;
import modelo.Moneda;
import modelo.Personaje;
import modelo.Vehiculo;

/**

 * Esta clase representa el panel de despliegue de graficos principal del juego Road Crossers

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class PanelJuego  extends JPanel implements  KeyEventDispatcher{
	
	private VentanaPrincipal ventana;
	
	public PanelJuego(VentanaPrincipal v)  {
		ventana = v;
		setSize(1000, 700);
		KeyboardFocusManager m = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		m.addKeyEventDispatcher(this);
		

		
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		ImageIcon mapa = new ImageIcon(ventana.getJuego().getMapa().getImagen());
		
		g2.drawImage(mapa.getImage(), 0, 0, 1100, 700, this); 
		
		ArrayList<Moneda> monedas = ventana.getJuego().getMapa().obtenerListaMonedas();
		for (int i = 0; i < monedas.size(); i++) {
			Moneda m = monedas.get(i);
			ImageIcon auto =  new ImageIcon(m.getImagen());
			g2.drawImage(auto.getImage(), m.getPosicionX(), m.getPosicionY(), 20, 20, this); 
		}
		
		
		ArrayList<Vehiculo> vehiculos = ventana.getJuego().getMapa().obtenerListaVehiculos();
		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo c = vehiculos.get(i);
			ImageIcon auto =  new ImageIcon(c.getImagen());
			if(c.getImagen().equals("data/train.png")) {
			g2.drawImage(auto.getImage(), c.getPosicionX(), c.getPosicionY(), 300, 50, this); 
			}else {
			g2.drawImage(auto.getImage(), c.getPosicionX(), c.getPosicionY(), 100, 40, this); }
		}
		
		Cronometro crono = ventana.getJuego().getJugador().getCrono();
		ImageIcon crono1 =  new ImageIcon(crono.getImagen());

		Font f = new Font("Tahoma", Font.BOLD, 28);
		g2.setFont(f);
		g2.setColor(Color.WHITE);
		g2.drawImage(crono1.getImage(), crono.getPosicionX(), crono.getPosicionY(), 50, 50, this);
		g2.drawString(+ crono.getHora()+ ":" +crono.getMinuto() +":"
				+crono.getSegundo() +":" +crono.getCentesima() +" ", 50, 40);
		
		Personaje p = ventana.getJuego().getJugador().getPersonajeElegido();
		ImageIcon personaje =  new ImageIcon(p.getImagen());
		g2.drawImage(personaje.getImage(), p.getPosicionX(), p.getPosicionY(), 60, 60, this); 

		Jugador user = ventana.getJuego().getJugador();
		g2.drawString(user.getNombre(), 300, 40);
		g2.drawImage(new ImageIcon("data/user.png").getImage(), 255, 10, 40, 40, this);
		
		g2.drawImage(new ImageIcon("data/coin.png").getImage(), 850, 10, 40, 40, this);
		g2.drawString(""+user.getPuntaje(), 900, 40);
		
		


		
	}

	
	


	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		
		if(e.getID() == KeyEvent.KEY_RELEASED) {
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    			ventana.moverPersonaje("abajo"); 
    		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
    			ventana.moverPersonaje("arriba"); 
    		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    			ventana.moverPersonaje("Izquierda"); 
    		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    			ventana.moverPersonaje("Derecha"); 
    		}
			ventana.verificarInterseccionMoneda();
			}


	return false;			
		
        	
		
	}



	

}
