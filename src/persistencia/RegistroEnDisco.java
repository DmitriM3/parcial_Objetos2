package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Properties;

import modelo.RegistroDeVentas;
import modelo.Venta;

public class RegistroEnDisco implements RegistroDeVentas {

	Properties props = new Properties();

	@Override
	public void registrarVenta(Venta unaVenta) {
		try {
			String registro = unaVenta.obtenerDetalleVenta();
			System.out.println(registro);
			Files.write(Paths.get(props.getProperty("ruta")), registro.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo persistir", e);
		}

	}

	@Override
	public ArrayList<Venta> obtenerRegistroDeVentas() {
		ArrayList<Venta> misVentas = new ArrayList<>();
		BufferedReader br = null;
		try {
			props.load(new FileInputStream("demo.properties"));
			File archivo = new File(props.getProperty("ruta"));
			br = new BufferedReader(new FileReader(archivo));
			// Leer la primera línea, guardando en un String por palabras separadas en ','
			String split = ", ";
			String texto = br.readLine();
			while (texto != null) {
				String[] texto1 = texto.split(split);
				misVentas.add(new Venta(texto1[1], Double.parseDouble(texto1[2]), texto1[0]));
				// Leer la siguiente línea
				texto = br.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("No se encuentra el archivo");
		} catch (Exception e) {
			throw new RuntimeException("No se puede conectar");
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {
				throw new RuntimeException("Error al finalizar");
			}
		}

		return misVentas;
	}

}
