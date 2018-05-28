package interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**

 * Esta clase representa el menu del juego Road Crossers

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class PanelMenu extends JPanel implements ActionListener{
	
	private VentanaPrincipal ventana;
	private JLabel labFondo;
	private JLabel labBanner;
	private JButton botJugar;
	private JButton botCargar;
	private JButton botPuntajes;
	private JButton botSalir;
	
	public PanelMenu(VentanaPrincipal v) {
		
		ventana = v;
		setLayout(new FlowLayout());
		setAlignmentX(CENTER_ALIGNMENT);
		
		labFondo = new JLabel(new ImageIcon("data/fondo2.png"));
		labFondo.setAlignmentX(CENTER_ALIGNMENT);
		add(labFondo);
		
		labFondo.setLayout(new BoxLayout(labFondo, BoxLayout.PAGE_AXIS));
		labFondo.add(Box.createVerticalStrut(250));
		botJugar = new JButton(new ImageIcon("data/jugar.png"));
		botJugar.addActionListener(this);
		botJugar.setActionCommand("JUGAR");
		botJugar.setBorder(null);
		botJugar.setContentAreaFilled(false);
		botJugar.setAlignmentX(CENTER_ALIGNMENT);
		labFondo.add(botJugar);
		
		labFondo.add(Box.createVerticalStrut(40));
		botCargar = new JButton (new ImageIcon("data/cargar.png"));
		botCargar.addActionListener(this);
		botCargar.setActionCommand("CARGAR");
		botCargar.setBorder(null);
		botCargar.setContentAreaFilled(false);
		botCargar.setAlignmentX(CENTER_ALIGNMENT);
		labFondo.add(botCargar);
		
		labFondo.add(Box.createVerticalStrut(40));
		botPuntajes = new JButton (new ImageIcon("data/puntajes.png"));
		botPuntajes.addActionListener(this);
		botPuntajes.setActionCommand("PUNTAJES");
		botPuntajes.setBorder(null);
		botPuntajes.setContentAreaFilled(false);
		botPuntajes.setAlignmentX(CENTER_ALIGNMENT);
		labFondo.add(botPuntajes);
		
		labFondo.add(Box.createVerticalStrut(40));
		botSalir = new JButton(new ImageIcon("data/salir.png"));
		botSalir.addActionListener(this);
		botSalir.setActionCommand("SALIR");
		botSalir.setBorder(null);
		botSalir.setContentAreaFilled(false);
		botSalir.setAlignmentX(CENTER_ALIGNMENT);
		labFondo.add(botSalir);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String c = e.getActionCommand();
		if(c.equalsIgnoreCase("SALIR")) {
			System.exit(0);
		}
		
		if(c.equalsIgnoreCase("JUGAR")) {
			ventana.nuevaPartida();
		}
		
		if(c.equalsIgnoreCase("PUNTAJES")) {
			ventana.crearDialogoPuntajes();;
		}
		
		if(c.equalsIgnoreCase("CARGAR")) {
			ventana.crearDialogCargar();
		}
		
	}

	
}
