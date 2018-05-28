package hilos;

import interfaz.VentanaPrincipal;
import modelo.Cronometro;
import modelo.Moneda;

public class HiloCronometro extends Thread {

	VentanaPrincipal ventana;
	Cronometro crono;
	
	public HiloCronometro(VentanaPrincipal ventana, Cronometro crono) {
		this.ventana = ventana;
		this.crono = crono;
	}
	
	public void run() {
		
		
		for(int i=0; ; i++) {
			crono.aumentar();;	
			ventana.refrescar();
					
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	
}
