package modelo;

public class Combustible {

	private double precioXLitro;

	public Combustible(double precioXLitro) {
		this.precioXLitro = precioXLitro;
	}

	public double calcularMontoTotalAPagar(String litros) {
		return 0;
	}

	public double obtenerPrecioPorCantidadDeLitros(int litros) {
		return (this.precioXLitro * litros);
	}

}
