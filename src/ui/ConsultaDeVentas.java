package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.AppSQLException;
import modelo.ClassNotFound;
import modelo.RegistroDeVentas;
import modelo.Venta;

public class ConsultaDeVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7180801671456434213L;
	private JPanel contentPane;
	private RegistroDeVentas miRegistro;
	ArrayList<Venta> misVentas;
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Create the frame.
	 * 
	 * @throws AppSQLException
	 * @throws ClassNotFound
	 */
	public ConsultaDeVentas(RegistroDeVentas registro) {
		this.miRegistro = registro;
		setTitle("Registro de Ventas");
		String[] titulos = { "FECHA", "LITROS CARGADOS", "MONTO TOTAL" };
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		// Obtiene la lista de roles a mostrar
		try {
			this.misVentas = miRegistro.obtenerRegistroDeVentas();
		} catch (ClassNotFound | AppSQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		// Agrega los roles en el model
		for (Venta venta : misVentas) {
			modelo.addRow(new Object[] { venta.obtenerFecha(), venta.obtenerCantidadLitrosCargados(),
					venta.consultarMontoTotal() });
		}

		table.setModel(modelo);
		// Centrar los datos en las columnas
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < titulos.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		scrollPane.setViewportView(table);

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(cerrarButton, BorderLayout.SOUTH);

	}

}
