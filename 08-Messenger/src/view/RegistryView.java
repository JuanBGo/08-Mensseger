package view;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import controller.AccessController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * 
 * @author JuanAlberto
 *
 */
public class RegistryView extends JFrame{
	private JLabel lblUsuario = new JLabel("Nombre de usuario:");
	private JLabel lblPassword = new JLabel("Contrase\u00F1a:");

	private JButton btnRegresar = new JButton("Regresar");
	private JButton btnRegistrarse = new JButton("Registrarse");
	
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	
	private Container c = getContentPane();
	private JLabel lblPassword2 = new JLabel("Repetir contrase\u00F1a:");
	private JPasswordField txtPassword2 = new JPasswordField();

	
	/**
	 * Constructor de la clase AccesoView
	 */
	public RegistryView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Registro de usuarios");
		this.setBounds(100, 100, 280, 279);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		c.setLayout(null);
		
		btnRegresar.setBounds(71, 188, 110, 30);
		btnRegistrarse.setBounds(71, 147, 110, 30);
		
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBounds(10, 29, 128, 14);
		
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(10, 57, 100, 14);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(132, 26, 132, 20);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(132, 54, 132, 20);
		
		c.add(btnRegresar);
		c.add(btnRegistrarse);
		c.add(lblUsuario);
		c.add(lblPassword);
		c.add(txtUsuario);
		c.add(txtPassword);
		lblPassword2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword2.setBounds(10, 85, 128, 14);
		
		getContentPane().add(lblPassword2);
		txtPassword2.setBounds(132, 82, 132, 20);
		
		getContentPane().add(txtPassword2);
	}

	/**
	 * @return the btnRegresar
	 */
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	/**
	 * @return the btnRegistrarse
	 */
	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

	/**
	 * @return the txtUsuario
	 */
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	/**
	 * @return the txtPassword
	 */
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	/**
	 * @return the txtPassword2
	 */
	public JPasswordField getTxtPassword2() {
		return txtPassword2;
	}

	/**
	 * Método para asignar el evento click a los botones
	 * @param al evento click
	 */
	public void onClickBotones(ActionListener al){
		btnRegresar.addActionListener(al);
		btnRegistrarse.addActionListener(al);
	}
	
	/**
	 * Método para mostrar la ventana de acceso
	 */
	public void mostrarVentanaAcceso(){
		AccessView accesoView = new AccessView();
		AccessController accesoController = new AccessController(accesoView);
		this.setVisible(false);
		accesoView.setVisible(true);
	}
	
	/**
	 * Método para mostrar mensaje en pantalla
	 * @param mensaje generado
	 */
	public void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	/**
	 * Método para mostrar alerta en pantalla
	 * @param alerta generada
	 */
	public void mostrarAlerta(String alerta){
		JOptionPane.showMessageDialog(this, alerta, "Alerta", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Método para mostrar error en pantalla
	 * @param error generado
	 */
//	public void mostrarError(String error){
//		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
//	}
	
	/**
	 * Método para limpiar ventana
	 */
	public void limpiarVentana(){
		txtUsuario.setText(null);
		txtPassword.setText(null);
		txtPassword2.setText(null);
	}
}
