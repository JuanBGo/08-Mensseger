package view;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import controller.MenssegerController;
import controller.RegistryController;
import controller.AccessController;

import model.UserModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * 
 * @author JuanAlberto
 *
 */
public class AccessView extends JFrame{
	private JLabel lblUsuario = new JLabel("Nombre de usuario:");
	private JLabel lblPassword = new JLabel("Contrase\u00F1a:");

	private JButton btnAcceder = new JButton("Acceder");
	private JButton btnRegistrarse = new JButton("Crear cuenta");
	
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	
	private Container c = getContentPane();

	
	/**
	 * Constructor de la clase AccesoView
	 */
	public AccessView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Acceso");
		this.setBounds(100, 100, 270, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		c.setLayout(null);
		
		btnAcceder.setBounds(77, 121, 120, 30);
		btnRegistrarse.setBounds(77, 162, 120, 30);
		
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(0, 31, 116, 14);
		
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 59, 106, 14);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 28, 132, 20);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(122, 56, 132, 20);
		
		c.add(btnAcceder);
		c.add(btnRegistrarse);
		c.add(lblUsuario);
		c.add(lblPassword);
		c.add(txtUsuario);
		c.add(txtPassword);
	}

	/**
	 * @return the btnAcceder
	 */
	public JButton getBtnAcceder() {
		return btnAcceder;
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
	 * Método para asignar el evento click a los botones
	 * @param al evento click
	 */
	public void onClickBotones(ActionListener al){
		btnAcceder.addActionListener(al);
		btnRegistrarse.addActionListener(al);
	}
	
	/**
	 * Método para mostrar la ventana de registro de usuarios
	 */
	public void mostrarVentanaRegistro(){
		RegistryView registroView = new RegistryView();
		RegistryController registroController = new RegistryController(registroView);
		this.setVisible(false);
		registroView.setVisible(true);
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
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Método para limpiar la ventana
	 */
	public void limpiarVentana(){
		txtUsuario.setText(null);
		txtPassword.setText(null);
	}
	
	/**
	 * Método para mostrar la ventana de mensajes
	 * @param usuarioModel modelo
	 */
	public void mostrarMessenger(UserModel usuarioModel){
		MessengerView MensajesView = new MessengerView();
		MenssegerController MensajesController = new MenssegerController(MensajesView,usuarioModel);
		
		this.setVisible(false);
		MensajesView.setVisible(true);
	}
}
