package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import modelo.Comun;
import modelo.DatoInvalidoException;
import modelo.Super;
import modelo.Venta;

class modeloTest {

	private Super naftaS = new Super();
	private Comun naftaC = new Comun();
	private Venta venta;

	@Test
	void calcularCombustibleSuperTest() {
		// setup
		String litrosACargar = "20";
		double valorEsperado = 1800.0;
		double monto = naftaS.calcularMontoTotalAPagar(litrosACargar);
		// verificacion
		Assert.assertEquals(valorEsperado, monto, 0.001);
	}

	@Test
	void calcularCombustibleComunTest() {
		// setup
		String litrosACargar = "30";
		double valorEsperado = 2100.0;
		double monto = naftaC.calcularMontoTotalAPagar(litrosACargar);
		// verificacion
		Assert.assertEquals(valorEsperado, monto, 0.001);
	}

	@Test
	void ventaCombustibleComunTest() {
		// setup
		String litrosACargar = "5";
		double monto = naftaC.calcularMontoTotalAPagar(litrosACargar);
		try {
			venta = new Venta(litrosACargar, monto);
		} catch (DatoInvalidoException e) {
			throw new RuntimeException();
		}
		// ejercitacion
		String fecha = venta.obtenerFecha();
		String fechaEsperado = "5/8/21";
		double montoVenta = venta.consultarMontoTotal();
		double valorDeVentaEsperado = 350.0;
		// verificacion
		Assert.assertEquals(fechaEsperado, fecha);
		Assert.assertEquals(valorDeVentaEsperado, montoVenta, 0.001);

	}

}
