package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Venta {

	private double montoFacturado;
	private int litrosCargados;
	private LocalDate fecha;

	public Venta(String litrosCargados, double monto) throws DatoInvalidoException {
		if (!litrosMayorACero(litrosCargados)) {
			throw new DatoInvalidoException("La cantidad de litros debe ser mayor a 0");
		}
		this.litrosCargados = Integer.parseInt(litrosCargados);
		this.montoFacturado = monto;
		this.fecha = LocalDate.now();

	}

	public Venta(String litrosCargados, double monto, String fecha) throws DatoInvalidoException {
		if (!litrosMayorACero(litrosCargados)) {
			throw new DatoInvalidoException("La cantidad de litros debe ser mayor a 0");
		}
		this.litrosCargados = Integer.parseInt(litrosCargados);
		this.montoFacturado = monto;
		this.fecha = generarFechaLocalDate(fecha);
	}

	private boolean litrosMayorACero(String litrosCargados) {
		if (Integer.parseInt(litrosCargados) > 0) {
			return true;
		} else
			return false;
	}

	public String obtenerDetalleVenta() {
		String fechaLitrosMontoFacturado = this.fecha.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
				+ ", " + this.litrosCargados + ", " + this.montoFacturado + "\n";
		return fechaLitrosMontoFacturado;
	}

	public double consultarMontoTotal() {
		return this.montoFacturado;
	}

	public String obtenerFecha() {
		return this.fecha.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
	}

	public int obtenerCantidadLitrosCargados() {
		return this.litrosCargados;
	}

	private LocalDate generarFechaLocalDate(String fecha) {
		String split = "/";
		String[] fechaSplit = fecha.split(split);
		int dia = Integer.parseInt(fechaSplit[1]);
		int mes = Integer.parseInt(fechaSplit[0]);
		int año = Integer.parseInt(fechaSplit[2]);
		LocalDate date = LocalDate.of(año, mes, dia);
		return date;

	}

}
