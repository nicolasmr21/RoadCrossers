package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**

 * Esta clase representa el dialogo de puntaje obtenido a traves de archivos planos del juego Road Crossers

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class DialogoPuntajes extends JDialog implements ActionListener{

	private JList listaJugadores;
	private JButton buscarJugadorPuntaje;
	private JButton buscarJugadorNombre;
	private DefaultListModel<String> modelo; 
	VentanaPrincipal v;
	private JCheckBox ordenarNombre;
	private JCheckBox ordenarPuntaje;
	
	public DialogoPuntajes(VentanaPrincipal v) {
		this.v= v;
		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("RECORDS");
		 
		 JPanel botones = new JPanel();
		 botones.setLayout(new GridLayout(1, 2));
		 
		 buscarJugadorNombre = new JButton("Buscar nombre");
		 buscarJugadorNombre.addActionListener(this);
		 buscarJugadorNombre.setBackground(Color.BLUE);
		 buscarJugadorNombre.setForeground(Color.WHITE);
		 buscarJugadorNombre.setActionCommand("BUSCARN");
		 botones.add(buscarJugadorNombre);
		 
		 buscarJugadorPuntaje = new JButton("Buscar puntaje");
		 buscarJugadorPuntaje.addActionListener(this);
		 buscarJugadorPuntaje.setBackground(Color.BLUE);
		 buscarJugadorPuntaje.setForeground(Color.WHITE);
		 buscarJugadorPuntaje.setActionCommand("BUSCARP");
		 botones.add(buscarJugadorPuntaje);
		 
		 add(BorderLayout.NORTH, botones);
		 
		 
		 
		 listaJugadores = new JList();
		 modelo = new DefaultListModel<String>();
		 listaJugadores.setFont(new Font("Serif", Font.PLAIN, 20));
		 modelo.addElement("Nombre"  +"   Puntos obtenidos ");
		 listaJugadores.setModel(modelo);		 
		 add(BorderLayout.CENTER, new JScrollPane(listaJugadores));
		 
		 
		 JPanel auxiliar = new JPanel();
		 auxiliar.setLayout(new GridLayout(1, 2));
		 ordenarNombre= new JCheckBox("Ordenadar por nombre");
		 ordenarNombre.setBackground(Color.BLUE);
		 ordenarNombre.setForeground(Color.WHITE);
		 ordenarNombre.setHorizontalAlignment(JCheckBox.CENTER);
		 ordenarPuntaje= new JCheckBox("Ordenadar por puntaje");
		 ordenarPuntaje.setBackground(Color.BLUE);
		 ordenarPuntaje.setForeground(Color.WHITE);
		 ordenarPuntaje.setHorizontalAlignment(JCheckBox.CENTER);
		 ordenarNombre.setSelected(false);
		 ordenarPuntaje.setSelected(true);
		 auxiliar.add(ordenarNombre);
		 auxiliar.add(ordenarPuntaje);
		 add(BorderLayout.SOUTH, auxiliar);
		 ordenarNombre.addActionListener(this);
		 ordenarPuntaje.addActionListener(this);
		 ordenarNombre.setActionCommand("NOMBRE");
		 ordenarPuntaje.setActionCommand("PUNTAJE");
	
	}


	public JList getListaJugadores() {
		return listaJugadores;
	}




	public DefaultListModel<String> getModelo() {
		return modelo;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("BUSCARP")) {
			
			String input = JOptionPane.showInputDialog("Digite el puntaje que desea buscar");
			int puntaje = Integer.parseInt(input);
			v.buscarJugadorEnRecordsPorPuntaje(puntaje);
			
		}
		
		if(e.getActionCommand().equals("BUSCARN")) {
			
			String input = JOptionPane.showInputDialog("Digite el nombre que desea buscar");
			input = input.trim();
			v.buscarJugadorEnRecordsPorNombre(input);
			
		}
		
		if(e.getActionCommand().equals("PUNTAJE") || e.getActionCommand().equals("NOMBRE")) {
			v.actualizarListaPuntajes();
		}

		
	}


	public JCheckBox getOrdenarNombre() {
		return ordenarNombre;
	}


	public JCheckBox getOrdenarPuntaje() {
		return ordenarPuntaje;
	}

}
