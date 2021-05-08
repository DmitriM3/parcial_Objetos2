package ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.RegistroDeVentas;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5448405755932365960L;

	private JPanel contentPane;
	private RegistroDeVentas miRegistro;

	public VentanaPrincipal(RegistroDeVentas registro) {
		this.miRegistro = registro;
		setTitle("Ventana Principal");
		setBounds(400, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 37, 414, 42);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Titulo = new JLabel("Estaciones de servicio YPZW");
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(10, 11, 394, 20);
		panel.add(Titulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 107, 414, 42);
		contentPane.add(panel_1);

		JButton btnCargaCombustible = new JButton("Carga de Combustible");
		btnCargaCombustible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargaDeCombustible nuevaCarga = new CargaDeCombustible(obtenerRegistro());
				nuevaCarga.setVisible(true);
			}
		});
		btnCargaCombustible.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(btnCargaCombustible);

		JButton btnConsulta = new JButton("Consulta de Ventas");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaDeVentas consulta = new ConsultaDeVentas(obtenerRegistro());
				consulta.setVisible(true);
			}
		});
		btnConsulta.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(btnConsulta);
	}

	public RegistroDeVentas obtenerRegistro() {
		return this.miRegistro;
	}

	public void iniciarVentana() {
		this.setVisible(true);
	}

}
