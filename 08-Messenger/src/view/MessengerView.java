package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
/**
 * Clase vista de la ventana de chat
 * @author JuanAlberto
 *
 */
public class MessengerView extends JFrame {
	private JButton btnEnviar = new JButton("Enviar");
	private JTextField txtCuerpoMensaje = new JTextField();
	private Container c = getContentPane();
	private JPanel panel = new JPanel(); //Declaramos el panel que contendrÃ¡ los contorles 
	private JScrollPane scrollPane = new JScrollPane(); //declaramos el panel con scrolls
	private JComboBox cmbUsers = new JComboBox();
	private JLabel lblChange = new JLabel("Change User: ");

/**
 * Constructor de la clase
 */
	public MessengerView(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Mensajería");
		this.setBounds(100, 100, 440, 482);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		c.setLayout(null);
		
		
		scrollPane.setBounds(10, 11, 300, 380);//al panel con scrolls se le da un tamaÃ±p y posiciÃ³n
		scrollPane.setViewportView(panel); //agregamos el panel normal al panel con scrolls
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel.setPreferredSize(new Dimension(250, 800)); //le damos una dimensiÃ³n al panel 
		panel.setLayout(null);//se declara el absolutelayout 
	
		
		btnEnviar.setBounds(320, 403, 70, 20);
		txtCuerpoMensaje.setBounds(10,402,300,23);
		
		cmbUsers.setBounds(320, 35, 104, 20);
		cmbUsers.addItem("Users");
		
		lblChange.setBounds(320, 11, 104, 20);
		
		c.add(scrollPane);
		c.add(cmbUsers);
		c.add(btnEnviar);
		c.add(lblChange);
		c.add(txtCuerpoMensaje);		
	}

/**
 * Método que get 
 * @return botón enviar
 */
	public JButton getBtnEnviar() {
		return btnEnviar;
	}

/**
 * Método setter
 * @param btnEnviar botón
 */
	public void setBtnEnviar(JButton btnEnviar) {
		this.btnEnviar = btnEnviar;
	}

/**
 * Método getter 
 * @return JTextField
 */
	public JTextField getTxtCuerpoMensaje() {
		return txtCuerpoMensaje;
	}

/**
 * Método setter
 * @param txtCuerpoMensaje JTextField
 */
	public void setTxtCuerpoMensaje(JTextField txtCuerpoMensaje) {
		this.txtCuerpoMensaje = txtCuerpoMensaje;
	}

	/**
	 * Método getter 
	 * @return JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Método getter 
	 * @return JComboBox
	 */
	public JComboBox getCmbUsers() {
		return cmbUsers;
	}

/**
 * Método setter
 * @param cmbUsers JComboBox
 */
	public void setCmbUsers(JComboBox cmbUsers) {
		this.cmbUsers = cmbUsers;
	}
	/**
	 * Método que asigna el onCick
	 * @param al Action Listener
	 */
	public void onClickEnviar(ActionListener al){
		btnEnviar.addActionListener(al);
	}

/**
 * Método que asigna el onChange
 * @param il Item Listener
 */
	public void onItmen(ItemListener il) {
	cmbUsers.addItemListener(il);
		
	}

/**
 * Método getter
 * @return JScrollPane
 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

/**
 * Muestra alerta
 * @param string mensaje a mostrar
 */
	public void mostrarAlerta(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}

	
	
	
	
}
