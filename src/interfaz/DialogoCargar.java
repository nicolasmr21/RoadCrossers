package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modelo.Jugador;

/**

 * Esta clase representa el dialogo de partidas cargadas por archivos serializados del juego Road Crossers

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class DialogoCargar extends JDialog implements ActionListener{

	private JList listaJugadores;
	private JButton cargarPartida;
	private DefaultListModel modelo; 
	VentanaPrincipal v;
	
	private JButton eliminarJugador;


	
	public DialogoCargar(VentanaPrincipal v) {
		this.v= v;
		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("CARGAR PARTIDA");
		
		 cargarPartida = new JButton("Cargar Partida");
		 cargarPartida.addActionListener(this);
		 cargarPartida.setBackground(Color.BLUE);
		 cargarPartida.setForeground(Color.WHITE);
		 cargarPartida.setActionCommand("CARGAR");
		
		 
		 add(BorderLayout.NORTH, cargarPartida);
		 
		 
		 eliminarJugador = new JButton("Eliminar Jugador");
		 eliminarJugador.addActionListener(this);
		 eliminarJugador.setBackground(Color.BLUE);
		 eliminarJugador.setForeground(Color.WHITE);
		 eliminarJugador.setActionCommand("ELIMINAR");

		 add(BorderLayout.SOUTH, eliminarJugador);

		 listaJugadores = new JList();
		 modelo = new DefaultListModel();
		 listaJugadores.setModel(modelo);	
		 listaJugadores.setFont(new Font("Serif", Font.PLAIN, 20));
		 add(BorderLayout.CENTER, new JScrollPane(listaJugadores));
		 
		 
		
	
	}


	public JList getListaJugadores() {
		return listaJugadores;
	}




	public DefaultListModel getModelo() {
		return modelo;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("CARGAR")) {
			
			v.cargarPartida((Jugador)modelo.getElementAt(listaJugadores.getSelectedIndex()));
		}
		if(e.getActionCommand().equals("ELIMINAR")) {
			String nombre = JOptionPane.showInputDialog(this, "Digite el nombre del jugador que quiere eliminar");
			v.eliminarJugador(nombre);
		}
	
		
	}
	
}
