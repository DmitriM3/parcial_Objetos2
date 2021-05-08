package modelo;

import java.util.ArrayList;

public interface RegistroDeVentas {

	void registrarVenta(Venta unaVenta) throws AppSQLException, ClassNotFound;

	ArrayList<Venta> obtenerRegistroDeVentas() throws ClassNotFound, AppSQLException;

}
