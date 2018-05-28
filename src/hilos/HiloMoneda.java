package hilos;

import interfaz.VentanaPrincipal;
import modelo.Moneda;
import modelo.Vehiculo;

public class HiloMoneda extends Thread{
	
	VentanaPrincipal ventana;
	Moneda moneda;
	
	public HiloMoneda(VentanaPrincipal ventana, Moneda moneda) {
		this.ventana = ventana;
		this.moneda = moneda;
	}
	
	public void run() {
		for(int i=0; ; i++) {
			moneda.mover(1);	
			ventana.refrescar();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
}
