package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**

 * Esta clase representa el dialogo de creacion de partidas principal del juego Road Crossers

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class DialogoPartida extends JDialog implements ActionListener{
	
	private VentanaPrincipal ventana;
	private JLabel labBanner;
	private JTextField txtNombre;
	private JLabel labNombre;
	private JCheckBox goku;
	private JCheckBox kakashi;
	private JCheckBox naruto;
	private JButton jugar;
	
	public DialogoPartida(VentanaPrincipal v) {
		ventana = v; 
		setSize(900, 400);
		setVisible(true);
		setLocationRelativeTo(ventana);
		setTitle("Nueva partida");
		setLayout(new BorderLayout());
		
		
		
		JPanel auxiliar = new JPanel();
		labBanner = new JLabel(new ImageIcon("data/personajes.png"));
		auxiliar.setLayout(new BorderLayout());
		auxiliar.setBackground(Color.WHITE);
		auxiliar.add(BorderLayout.CENTER, labBanner);
		JPanel check = new JPanel();
		check.setLayout(new GridLayout(1, 3));
		goku = new JCheckBox("Goku");
		goku.setBackground(Color.WHITE);
		goku.setHorizontalAlignment(JCheckBox.CENTER);
		naruto = new JCheckBox("Naruto");
		naruto.setBackground(Color.WHITE);
		naruto.setHorizontalAlignment(JCheckBox.CENTER);
		kakashi = new JCheckBox("Kakashi");
		kakashi.setBackground(Color.WHITE);
		kakashi.setHorizontalAlignment(JCheckBox.CENTER);
		check.setAlignmentX(CENTER_ALIGNMENT);
		check.add(naruto);
		check.add(goku);
		check.add(kakashi);
		auxiliar.add(BorderLayout.SOUTH, check);
		JPanel informacion = new JPanel();
		informacion.setLayout(new BorderLayout());
		informacion.setBackground(Color.WHITE);
		txtNombre = new JTextField("Nickname");
		txtNombre.setHorizontalAlignment(JTextField.CENTER);
		txtNombre.setSize(20, 20);
		informacion.add(BorderLayout.CENTER, txtNombre);
		jugar = new JButton("CREAR PARTIDA");
		jugar.addActionListener(this);
		jugar.setBackground(Color.BLUE);
		jugar.setForeground(Color.WHITE);
		informacion.add(BorderLayout.SOUTH, jugar);
		
		
		add(BorderLayout.CENTER, auxiliar);
		add(BorderLayout.SOUTH, informacion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	
			String nombre = txtNombre.getText();
			String personaje = null;
			
			if(goku.isSelected()&&!kakashi.isSelected()&&!naruto.isSelected()) {
				personaje = "Goku";
				ventana.iniciarJuegoNuevo(nombre, personaje);
			}else if(!goku.isSelected()&&kakashi.isSelected()&&!naruto.isSelected()) {
				personaje = "Kakashi";
				ventana.iniciarJuegoNuevo(nombre, personaje);

			}else if(!goku.isSelected()&&!kakashi.isSelected()&&naruto.isSelected()) {
				personaje = "Naruto";
				ventana.iniciarJuegoNuevo(nombre, personaje);

			}else {
				JOptionPane.showMessageDialog(this, "Por favor seleccione solo un personaje");

			}
			
		
	}
	
	
	

}
