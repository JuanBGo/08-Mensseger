package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.GestionBD;
import model.UserModel;
import view.AccessView;


/**
 * 
 * @author JuanAlberto
 *
 */
public class AccessController implements ActionListener{
	private AccessView accesoView;
	private GestionBD gestionBD;
	private UserModel usuarioModel;
	
	/**
	 * Constructor de la clase AccesoController
	 * @param accesoView vista
	 */
	public AccessController(AccessView accesoView) {
		this.accesoView=accesoView;
		this.gestionBD=GestionBD.getGestionBD();
		
		usuarioModel = new UserModel();
		
		accesoView.onClickBotones(this);
	}

	/**
	 * Método que controla la acción de los botones
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==accesoView.getBtnAcceder()){
			if(accesoView.getTxtUsuario().getText().isEmpty()){
				accesoView.mostrarAlerta("Ingrese su nombre de usuario");
				return;
			}else if(accesoView.getTxtPassword().getText().isEmpty()){
				accesoView.mostrarAlerta("Ingrese su contraseña");
				return;
			}
			
			usuarioModel.setNombreUsuario(accesoView.getTxtUsuario().getText());
			usuarioModel.setPasswordUsuario(accesoView.getTxtPassword().getText());
			
			try {
				if(gestionBD.verificarUsuario(usuarioModel)){
						accesoView.mostrarMessenger(usuarioModel);
				}else{
					accesoView.mostrarAlerta("Usuario o contraseña incorrectos");
				}
			} catch (SQLException e) {
				accesoView.mostrarError(e.getMessage());
			}
		}else if(ae.getSource()==accesoView.getBtnRegistrarse()){
			accesoView.mostrarVentanaRegistro();
		}
	}
}
