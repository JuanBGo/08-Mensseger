package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.GestionBD;
import model.UserModel;
import view.RegistryView;


/**
 * 
 * @author JuanAlberto
 *
 */
public class RegistryController implements ActionListener{
	RegistryView registroView;
	UserModel usuarioModel;
	GestionBD gestionBD;
	
	/**
	 * Constructor de la clase RegistroController
	 * @param registroView vista
	 */
	public RegistryController(RegistryView registroView) {
		this.registroView=registroView;
		this.gestionBD=GestionBD.getGestionBD();
		
		usuarioModel = new UserModel();
		registroView.onClickBotones(this);
	}

	/**
	 * M�todo que controla la acci�n de los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==registroView.getBtnRegistrarse()){
			if(registroView.getTxtUsuario().getText().isEmpty()){
				registroView.mostrarAlerta("Ingrese su nombre de usuario");
				return;
			}else if(registroView.getTxtPassword().getText().isEmpty()){
				registroView.mostrarAlerta("Ingrese su contrase�a");
				return;
			}else if(registroView.getTxtPassword2().getText().isEmpty()){
				registroView.mostrarAlerta("Ingrese de nuevo su contrase�a");
				return;
			}else if(!registroView.getTxtPassword().getText().equals(registroView.getTxtPassword2().getText())){
				registroView.mostrarAlerta("Las contrase�as con coinciden");
				return;
			}
			
			usuarioModel.setNombreUsuario(registroView.getTxtUsuario().getText());
			usuarioModel.setPasswordUsuario(registroView.getTxtPassword().getText());
			
			try {
				gestionBD.registrarUsuario(usuarioModel);
				registroView.mostrarMensaje("Usuario registrado correctamente");
				registroView.limpiarVentana();
			} catch (Exception ex) {
//				registroView.mostrarError(ex.getMessage());
			}
		
		}else if(e.getSource()==registroView.getBtnRegresar()){
			registroView.mostrarVentanaAcceso();
		}
	}
}
