package interfaz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import hilos.HiloCronometro;
import hilos.HiloMoneda;
import hilos.HiloMusica;
import hilos.HiloVehiculo;
import modelo.IRoadCrossers;
import modelo.Juego;
import modelo.Jugador;
import modelo.Excepciones.JugadorNoEncontradoException;
import modelo.Excepciones.JugadorYaExisteException;
import modelo.Excepciones.NombreMuyLargoException;
import modelo.Moneda;
import modelo.Personaje;
import modelo.Vehiculo;


/**

 * Esta clase representa la ventana principal del juego Road Crossers

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class VentanaPrincipal extends JFrame  implements ActionListener {
	
	private IRoadCrossers juego;
	
	private PanelMenu panelMenu;
	private PanelJuego panelJuego;
	private DialogoPartida dialogoPartida;
	private DialogoPuntajes dialogoPuntajes;
	private DialogoCargar dialogoCargar;
	private HiloVehiculo hiloVehiculo;
	private HiloMoneda hiloMoneda;
	private HiloCronometro hiloCronometro;

	//BARRA DE OPCIONES
	private JMenuBar barra; private JMenu menuOp, menuH, menuJ;
	private JMenuItem instrucciones, guardarJuego, salir, menu, guardarRecord;
	
	
	public VentanaPrincipal() {
		setTitle("ROAD CROSSERS");
		setSize(1000, 700);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		menu();
		
		barra = new JMenuBar();
		menuOp = new JMenu("Opciones");
		menuH = new JMenu("Ayuda");
		menuJ = new JMenu("Jugador");
		barra.add(menuOp); barra.add(menuJ); barra.add(menuH);
		guardarRecord = new JMenuItem("Guardar Record");
		guardarRecord.addActionListener(this);
		guardarRecord.setActionCommand("RECORD");
		menuJ.add(guardarRecord);
		instrucciones = new JMenuItem("Instrucciones"); 
		instrucciones.addActionListener(this);
		instrucciones.setActionCommand("INSTRUCCIONES");
		guardarJuego = new JMenuItem("Guardar Juego");
		guardarJuego.addActionListener(this);
		guardarJuego.setActionCommand("GUARDAR");
		salir = new JMenuItem("Salir del Juego");
		salir.addActionListener(this);
		salir.setActionCommand("SALIR");
		menu = new JMenuItem("Ir a Menu");
		menu.addActionListener(this);
		menu.setActionCommand("MENU");
		menuOp.add(menu); menuOp.add(guardarJuego); menuOp.add(salir);
		menuH.add(instrucciones);
		setJMenuBar(barra);
		juego = new Juego();
		playSound("data/background.wav");
		
	}	
	
	
	/**
	 * Inicia el menu del juego.	
	 */
	public void menu() {
		panelMenu = new PanelMenu(this);
		add(panelMenu);
		panelMenu.setVisible(true);	
		if(panelJuego!=null) {
		panelJuego.setVisible(false);}
	}
	
	/**
	 * Inicia un dialogo de puntajes cargados del archivo plano.
	 */
	public void crearDialogoPuntajes() {
		dialogoPuntajes = new DialogoPuntajes(this);
		dialogoPuntajes.setVisible(true);
		actualizarListaPuntajes();
	}
	
	/*
	 * Inicia un nuevo dialogo para cargar partida por archivos serializados.
	 */
	public void crearDialogCargar() {
		dialogoCargar= new DialogoCargar(this);
		dialogoCargar.setVisible(true);
		actualizarListaPartidas();
	}
	
	/**
	 * Metodo para crear una nueva partida
	 */
	public void nuevaPartida() {
		dialogoPartida = new DialogoPartida(this);
		dialogoPartida.setVisible(true);
	}
	
	
	/**
	 * Metodo para cargar una partida ya creada
	 * @param j el jugador que quiere cargar el usuario.
	 */
	public void cargarPartida(Jugador j) {
		remove(panelMenu);
		dialogoCargar.setVisible(false);
		juego.cargarJuego(j);
		juego.iniciarNivel1(j.getPersonajeElegido().getNombre());
		panelMenu.setVisible(false);
		panelJuego = new PanelJuego(this);
		add(panelJuego);
		panelJuego.setVisible(true);	
		crearHilos();
	}
	
	/**
	 * Metodo para iniciar un nuevo juego.
	 * @param nombre: especifica el nombre del jugador.
	 * @param personaje: especifica el nombre del personaje
	 */
	public void iniciarJuegoNuevo(String nombre, String personaje) {
		remove(panelMenu);
		try {
			juego.iniciarJuego(new Jugador(nombre, 0));
			juego.iniciarNivel1(personaje);
			panelMenu.setVisible(false);
			dialogoPartida.setVisible(false);
			panelJuego = new PanelJuego(this);
			add(panelJuego);
			panelJuego.setVisible(true);	
			crearHilos();
		} catch (NombreMuyLargoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (JugadorYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}
	
	/**
	 * Hace el llamado al mundo para verificar una interseccion con el carro.
	 */
	public void verificarInterseccionVehiculo() {
		if(juego.seIntersecto())
			System.out.println("Choco");
	}
	
	/**
	 * Hace el llamado al mundo para verificar una interseccion con la moneda.
	 */
	public void verificarInterseccionMoneda() {
		if(juego.atrapoMoneda()) {
			System.out.println("Atrapo");
			playSound("data/coin.wav");
		}
		}
	
	
	
	/**
	 * Hace el llamado al modelo para el movimiento de un personaje
	 * @param dir direccion a la cual lo quiere mover.
	 */
	public void moverPersonaje(String dir) {
		Personaje p = juego.getJugador().getPersonajeElegido();
		if(dir.equalsIgnoreCase("Derecha")) {
			p.mover(30, 0);
			p.setImagen(p.getImageRight());
		}else if(dir.equalsIgnoreCase("Izquierda")) {
			p.mover(-30, 0);
			p.setImagen(p.getImageLeft());
		}
		else if(dir.equalsIgnoreCase("Abajo")) {
			p.mover(0, 30);
			p.setImagen(p.getImageDown());
		}else if(dir.equalsIgnoreCase("Arriba")) {
			p.mover(0, -30);
			p.setImagen(p.getImageUp());
		}
		
		if(juego.pasoDeNivel()) {avanzarNivel();}
	}
	
	
	/**
	 * Hace el llamado para avanzar de nivel. Esto se produce cuando el personaje llega 
	 * a la coordenada y igual a 0.
	 */
	
	public void avanzarNivel() {
		int nivelActual = juego.getMapa().getNivel();
		if(nivelActual ==1) {
			juego.iniciarNivel2(juego.getJugador().getPersonajeElegido().getNombre());
			crearHilos();
		}else if(nivelActual ==2) {
			juego.iniciarNivel3(juego.getJugador().getPersonajeElegido().getNombre());
			crearHilos();
		}
		else {
			JOptionPane.showMessageDialog(this, "COMPLETASTE EL JUEGO");
			int i = JOptionPane.showConfirmDialog(this, "¿Desea guardar el puntaje obtenido?");
			if(i==JOptionPane.YES_OPTION) {
				juego.guardarPuntaje();;
			}
			
			i = JOptionPane.showConfirmDialog(this, "¿Desea guardar el estado del juego?");
			if(i==JOptionPane.YES_OPTION) {			
				juego.guardarDatos();
			}
			
			menu();
		
		}
	}
	

	/**
	 * Metodo que crea los hilos para animar y cargar los graficos correspondientes.
	 */
	public void crearHilos() {
		ArrayList<Vehiculo> vehiculos = juego.getMapa().obtenerListaVehiculos();
		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo v= vehiculos.get(i);
			hiloVehiculo = new HiloVehiculo(this, v);
			hiloVehiculo.start();
		}
		
		ArrayList<Moneda> monedas = juego.getMapa().obtenerListaMonedas();
		for (int i = 0; i < monedas.size(); i++) {
			Moneda m= monedas.get(i);
			hiloMoneda = new HiloMoneda(this, m);
			hiloMoneda.start();
		}
		hiloCronometro = new HiloCronometro(this, juego.getJugador().getCrono());
		hiloCronometro.start();
	
	}
	
	
	/**
	 * Busca un jugador del modelo que pertenece a un archivo plano segun el nombre.
	 * @param nombre especifica el nombre del que quiere buscar
	 */
	public void buscarJugadorEnRecordsPorNombre(String nombre) {
		
		Jugador j;
		try {
			ArrayList<Jugador> jugadores = juego.importarPuntajes();
			Jugador[] ordenados = juego.ordenarPorNombre(jugadores);

			j = juego.buscarRecordJugadorPorNombre(ordenados, nombre);
			if(j!=null) {
			JOptionPane.showMessageDialog(this, "El jugador es: \n **" +j.getNombre() +"** \n Puntaje buscado: " +j.getPuntaje());}
			
		} catch (modelo.Excepciones.JugadorNoEncontradoException e) {	
			JOptionPane.showMessageDialog(this, e.getMessage() +" con el nombre " +e.getNombre());
		}
		
		
	}
	
	/**
	 * Busca un jugador del modelo que pertenece a un archivo plano segun el puntaje.
	 * @param nombre especifica el puntaje del que quiere buscar
	 */
	public void buscarJugadorEnRecordsPorPuntaje(int puntaje) {
		
		Jugador j;
		try {
			ArrayList<Jugador> jugadores = juego.importarPuntajes();
			Jugador[] ordenados = juego.ordenarPorPuntaje(jugadores);

			j = juego.buscarRecordJugadorPorPuntaje(ordenados, puntaje);
			if(j!=null) {
			JOptionPane.showMessageDialog(this, "El jugador es: \n **" +j.getNombre() +"** \n Puntaje buscado: " +j.getPuntaje());}
			
		} catch (modelo.Excepciones.JugadorNoEncontradoException e) {	
			JOptionPane.showMessageDialog(this, e.getMessage() +" con el puntaje " +e.getPuntos());
		}
		
		
	}
	
	
	/**
	 * Actualiza la lista del dialogo cargar, esta especifica los jugadores del arbol binario.
	 */
	public void actualizarListaPartidas() {
		
		dialogoCargar.getModelo().removeAllElements();
		dialogoCargar.getModelo().clear();
		dialogoCargar.getListaJugadores().setSelectedIndex(0);
		ArrayList<Jugador> jugadores = juego.obtenerListaJugadores();
		if(jugadores!=null) {
			for (Jugador j : jugadores) {
				dialogoCargar.getModelo().addElement(j);
				
			}
		
		}
	}
	
	
	/**
	 * Actualiza la lista del dialogo puntajes, esta especifica los jugadores del archivo plano.
	 */
	
	public void actualizarListaPuntajes() {
		
		ArrayList<Jugador> jugadores = juego.importarPuntajes();
		Jugador[] ordenados = null;

		if(dialogoPuntajes.getOrdenarNombre().isSelected() && dialogoPuntajes.getOrdenarPuntaje().isSelected()) {
			JOptionPane.showMessageDialog(this, "SELECCIONE SOLO UNA DE LAS RESTRICCIONES PARA ORDENAR");
		ordenados = null;
		}
		else if(dialogoPuntajes.getOrdenarNombre().isSelected() ) {
		ordenados = juego.ordenarPorNombre(jugadores);
		}
		
		else if(dialogoPuntajes.getOrdenarPuntaje().isSelected() ) {
		ordenados = juego.ordenarPorPuntaje(jugadores);}
		
		dialogoPuntajes.getModelo().clear();

		if(ordenados!=null) {
		for (int i = 0; i < ordenados.length; i++) {
			Jugador j = ordenados[i]; 
			 
			dialogoPuntajes.getModelo().addElement(j.getNombre() +" || PTS: " +j.getPuntaje());
			
			}}
		else {
			dialogoPuntajes.getModelo().addElement("");
		}
	}
	
	
	/**
	 * Crea un hilo para reproducir musica.
	 * @param url especifica el url de la cancion a reproducir.
	 */
	public  synchronized void playSound(final String url) {
	       HiloMusica h = new HiloMusica(url);
	       h.start();
	      }
	 
	 
	/**
	 * Invoca al modelo para eliminar un jugador
	 * @param nombre especificando el nombre de quien quiere eliminar.
	 */
	public void eliminarJugador(String nombre) {
		try {
			juego.eliminarJugador(nombre);
			juego.guardarDatos();
		} catch (JugadorNoEncontradoException e) {
			JOptionPane.showMessageDialog(this, "El jugador que quiere eliminar no existe");;
		}
		actualizarListaPartidas();
	}
	
	/**
	 * Metodo que refrescar los graficos del juego.
	 */
	public void refrescar() {panelJuego.repaint();}
	
	/**
	 * Metodo que permite acceder al juego.
	 * @return un objeto juego.
	 */
	public Juego getJuego() {return (Juego)juego;}

	
	/**
	 * Hilo principal
	 */
	public static void main(String[] args) {
		VentanaPrincipal ventana = new VentanaPrincipal(); 
		ventana.setVisible(true);
	}

	
	/**
	 * Metodo que maneja los eventos del JMenu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
			
			if(c.equalsIgnoreCase("MENU")) {
				menu();
			}else if(c.equalsIgnoreCase("GUARDAR")) {
				juego.guardarDatos();
			} else if(c.equalsIgnoreCase("INSTRUCCIONES")) {
				
			} else if(c.equalsIgnoreCase("SALIR")) {
				System.exit(EXIT_ON_CLOSE);
			}
			else if(c.equalsIgnoreCase("RECORD")) {
				juego.guardarPuntaje();
			}
				
		
	} 

}
