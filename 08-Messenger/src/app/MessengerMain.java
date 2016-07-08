package app;

import controller.AccessController;
import database.GestionBD;
import view.AccessView;

/**
 * 
 * @author JuanAlberto
 *
 */
public class MessengerMain {

	public static void main(String[] args) {
		GestionBD.getGestionBD();
		AccessView accessView=new AccessView();
		AccessController accesoController=new AccessController(accessView);
		accessView.setVisible(true);	
		}

}
