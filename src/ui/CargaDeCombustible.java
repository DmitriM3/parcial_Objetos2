package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.AppSQLException;
import modelo.ClassNotFound;
import modelo.Comun;
import modelo.DatoInvalidoException;
import modelo.RegistroDeVentas;
import modelo.Super;
import modelo.Venta;

public class CargaDeCombustible extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -279310512763808614L;
	private JPanel contentPane;
	private RegistroDeVentas miRegistro;
	private JTextField litros;
	private double monto;
	private Comun naftaComun = new Comun();
	private Super naftaSuper = new Super();

	public CargaDeCombustible(RegistroDeVentas registro) {
		this.miRegistro = registro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_descripcion = new JPanel();
		panel_descripcion.setBounds(10, 11, 414, 39);
		contentPane.add(panel_descripcion);
		panel_descripcion.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Carga de Combustible");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_descripcion.add(lblNewLabel);

		JPanel panel_datos = new JPanel();
		panel_datos.setBounds(10, 61, 414, 119);
		contentPane.add(panel_datos);
		panel_datos.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Litros Cargados :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(25, 10, 120, 25);
		panel_datos.add(lblNewLabel_1);

		litros = new JTextField();
		litros.setBounds(160, 10, 50, 25);
		panel_datos.add(litros);
		litros.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tipo de Nafta : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(25, 50, 120, 25);
		panel_datos.add(lblNewLabel_2);

		JRadioButton rdbtnComun = new JRadioButton("Com\u00FAn");
		rdbtnComun.setBounds(160, 50, 80, 25);
		panel_datos.add(rdbtnComun);

		JRadioButton rdbtnSuper = new JRadioButton("Super");
		rdbtnSuper.setBounds(250, 50, 80, 25);
		panel_datos.add(rdbtnSuper);

		JPanel panel = new JPanel();
		panel.setBounds(10, 191, 414, 39);
		contentPane.add(panel);

		JButton btnConsultarMonto = new JButton("Consultar Monto");
		btnConsultarMonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnComun.isSelected()) {
					monto = naftaComun.calcularMontoTotalAPagar(litros.getText());
					JOptionPane.showMessageDialog(null, "Monto a cobrar : $ " + monto, "Consulta de Monto",
							JOptionPane.INFORMATION_MESSAGE);
				}
				if (rdbtnSuper.isSelected()) {
					monto = naftaSuper.calcularMontoTotalAPagar(litros.getText());
					JOptionPane.showMessageDialog(null, "Monto a cobrar : $ " + monto, "Consulta de Monto",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel.add(btnConsultarMonto);

		JButton btnConfirmar = new JButton("Confirmar Pago");
		btnConfirmar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (rdbtnComun.isSelected()) {
					try {
						monto = naftaComun.calcularMontoTotalAPagar(litros.getText());
						miRegistro.registrarVenta(new Venta(litros.getText(), monto));
						JOptionPane.showMessageDialog(null, "La venta se registro con éxito ", "CONFIRMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (DatoInvalidoException | AppSQLException | ClassNotFound e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
				if (rdbtnSuper.isSelected()) {
					try {
						monto = naftaSuper.calcularMontoTotalAPagar(litros.getText());
						miRegistro.registrarVenta(new Venta(litros.getText(), monto));
						JOptionPane.showMessageDialog(null, "La venta se registro con éxito ", "CONFIRMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (DatoInvalidoException | AppSQLException | ClassNotFound e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		panel.add(btnConfirmar);

		JButton btnCerrar = new JButton("Volver");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnCerrar);

		rdbtnComun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnComun.isSelected()) {
					rdbtnSuper.setSelected(false);
				}
			}
		});
		rdbtnSuper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnSuper.isSelected()) {
					rdbtnComun.setSelected(false);
				}
			}
		});
	}

}
