package main;

import persistencia.RegistroEnBaseDeDatos;
import ui.VentanaPrincipal;

public class MainBaseDeDatos {

	public static void main(String[] args) {

		VentanaPrincipal ui = new VentanaPrincipal(new RegistroEnBaseDeDatos());
		ui.iniciarVentana();
	}

}
