package main;

import persistencia.RegistroEnDisco;
import ui.VentanaPrincipal;

public class MainDisco {

	public static void main(String[] args) {

		VentanaPrincipal ui = new VentanaPrincipal(new RegistroEnDisco());
		ui.iniciarVentana();
	}

}
