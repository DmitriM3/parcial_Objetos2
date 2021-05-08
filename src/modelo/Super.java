package modelo;

import java.time.LocalDate;

public class Super extends Combustible {

	private final double descuento10 = 0.90; // 10% de descuento
	private final double descuento12 = 0.88; // 12% de descuento

	public Super() {
		super(90.0);
	}

	public double calcularMontoTotalAPagar(String litros) {
		int litrosCargados = Integer.parseInt(litros);
		if (esDomingo()) {
			return (obtenerPrecioPorCantidadDeLitros(litrosCargados) * descuento10);
		}
		if (esSabadoYCantidadLitrosMayorA20(litrosCargados)) {
			return (obtenerPrecioPorCantidadDeLitros(litrosCargados) * descuento12);
		} else
			return obtenerPrecioPorCantidadDeLitros(litrosCargados);

	}

	private boolean esDomingo() {
		if (LocalDate.now().getDayOfWeek().getValue() == 7) {
			return true;
		} else
			return false;
	}

	private boolean esSabadoYCantidadLitrosMayorA20(int litros) {
		if (LocalDate.now().getDayOfWeek().getValue() == 6 && litros > 20) {
			return true;
		} else
			return false;
	}

}
