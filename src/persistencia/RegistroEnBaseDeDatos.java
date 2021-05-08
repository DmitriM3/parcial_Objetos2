package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.AppSQLException;
import modelo.ClassNotFound;
import modelo.RegistroDeVentas;
import modelo.Venta;

public class RegistroEnBaseDeDatos implements RegistroDeVentas {

	public void registrarVenta(Venta unaVenta) throws AppSQLException, ClassNotFound {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "parcial_objetos2", "root", "");
			String insert = "INSERT INTO registro_venta (fecha, litros_cargados, monto_total) VALUES (?, ?, ?);";
			PreparedStatement prepStm = (PreparedStatement) conn.prepareStatement(insert);
			prepStm.setString(1, unaVenta.obtenerFecha());
			prepStm.setInt(2, unaVenta.obtenerCantidadLitrosCargados());
			prepStm.setDouble(3, unaVenta.consultarMontoTotal());
			int n = prepStm.executeUpdate();
			if (n > 0) {
				System.out.println("Registro guardado...");
			} else {
				System.out.println("Error al insertar...");
			}
			conn.close();
			System.out.println("Desconectado de la base de datos");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFound("NO JDBC driver");
		} catch (SQLException e) {
			throw new AppSQLException("No se pudo conectar a la BD. " + e.getMessage());
		} catch (Exception e) {
			throw new AppSQLException(e, e.getMessage(), "Error al insertar campaña");
		}

	}

	@Override
	public ArrayList<Venta> obtenerRegistroDeVentas() throws ClassNotFound, AppSQLException {
		ArrayList<Venta> misVentas = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "parcial_objetos2", "root", "");
			String query = "Select * FROM registro_venta";
			System.out.println("Conectado a la base de datos");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				misVentas.add(new Venta(rs.getString(3), rs.getDouble(4), rs.getString(2)));
			}
			conn.close();
			System.out.println("Desconectado de la base de datos");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFound("NO JDBC driver");
		} catch (SQLException e) {
			throw new AppSQLException("No se pudo conectar a la BD. " + e.getMessage());
		} catch (Exception e) {
			throw new AppSQLException(e, e.getMessage(), "Error al insertar campaña");
		}
		return misVentas;
	}

}
