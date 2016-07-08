package controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.GestionBD;
import model.MessageModel;
import model.UserModel;
import view.MessengerView;

/**
 * 
 * @author JuanAlberto
 *
 *clase controlador de Mensseger
 */

public class MenssegerController implements ActionListener, ItemListener, Runnable{
	
	private MessengerView messengerView;
	private UserModel userEmM;
	private UserModel userRecM;
	private GestionBD gBD;
	private List<MessageModel> listMessages;
	private List<UserModel> listUsers;
	private Thread h1;
	private int c = 0;
	int y = 0 ;
	int h = 0;
	private int ultimoMensajitopapu;
	
	
	/**
	 * Método costructor de la clase
	 * @param messengerView vista del messenger
	 * @param userEmM modelo del usuario emisor
	 */
	public MenssegerController(MessengerView messengerView, UserModel userEmM) {
		this.messengerView = messengerView;
		this.userEmM = userEmM;
		this.gBD = GestionBD.getGestionBD();
		
		this.messengerView.onClickEnviar(this);
		this.messengerView.onItmen(this);
		this.h1 = new Thread(this);
		loadCmb(userEmM.getIdUsuario());
	}
/**
 * Método que carga los valores al combobox
 * @param id valor del id que será excluído 
 */
	private void loadCmb(int id) {
		
		try {
			listUsers = gBD.consultarUsers(id);
			for (int i = 0; i < listUsers.size(); i++) {
				messengerView.getCmbUsers().addItem(listUsers.get(i).getNombreUsuario());
			}
		} catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(),"Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
			
	}

	/**
	 *Método actionperformed para esccuhar al botón
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(messengerView.getCmbUsers().getSelectedIndex() == 0){
			messengerView.mostrarAlerta("Seleccione Usuario");
			return;
		}
		if (messengerView.getTxtCuerpoMensaje().getText().isEmpty()){
			messengerView.mostrarAlerta("Ingresar mensaje");
			return;
		}
		MessageModel mensajeModel = new MessageModel();
		
		String[] meses = new String[]{
				"Enero",
				"Febrero",
				"Marzo",
				"Abril",
				"Mayo",
				"Junio",
				"Julio",
				"Agosto",
				"Septiembre",
				"Octubre",
				"Noviembre",
				"Diciembre"}; 
		
		Calendar now = Calendar.getInstance();
		
		int dia = now.get(Calendar.DAY_OF_MONTH);
		String mes = meses[now.get(Calendar.MONTH)];
		int año = now.get(Calendar.YEAR);
		String hora,minutos,segundos,ampm;
		
		ampm = now.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

		if(ampm.equals("PM")){
		 int h = now.get(Calendar.HOUR_OF_DAY)-12;

		 hora = h>9?""+h:"0"+h;

		}else{
			hora = now.get(Calendar.HOUR_OF_DAY)>9?""+now.get(Calendar.HOUR_OF_DAY):"0"+now.get(Calendar.HOUR_OF_DAY);            
		}
		
		minutos = now.get(Calendar.MINUTE)>9?""+now.get(Calendar.MINUTE):"0"+now.get(Calendar.MINUTE);
		segundos = now.get(Calendar.SECOND)>9?""+now.get(Calendar.SECOND):"0"+now.get(Calendar.SECOND);
	
		
		mensajeModel.setUsEm(userEmM.getNombreUsuario());
		mensajeModel.setUsRec(userRecM.getNombreUsuario());
		mensajeModel.setMensaje(messengerView.getTxtCuerpoMensaje().getText());
		mensajeModel.setFecha(dia+" de "+mes+" de "+año);
		mensajeModel.setHora(hora+":"+minutos+":"+segundos+" "+ampm);
		
		try {
			gBD.agregarMensaje(mensajeModel);
			messengerView.getTxtCuerpoMensaje().setText("");
		} catch (SQLException e) {
			e.getMessage();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if(e.getSource()==messengerView.getCmbUsers()){
				try {
					c = 0;
					messengerView.getPanel().removeAll();
					messengerView.getScrollPane().getVerticalScrollBar().setValue(messengerView.getScrollPane().getVerticalScrollBar().getValue()+1);
					userRecM = gBD.createReceptor(messengerView.getCmbUsers().getSelectedItem().toString());
					//System.out.println(userEmM.getNombreUsuario()+"  -  "+userRecM.getNombreUsuario());
					if(!h1.isAlive()){
					h1.start();
					
					}
					} catch (SQLException e1) {
		            JOptionPane.showMessageDialog(null, e1  , "Data base error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
	}

	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		
		while(ct == h1){
			if(c == 0){
				try {
					listMessages = gBD.consultarMensajes(1, userEmM.getNombreUsuario(),userRecM.getNombreUsuario());
					if(!listMessages.isEmpty()){
						printMessage(1);						
					}
				} catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e  , "Data base error",
								JOptionPane.ERROR_MESSAGE);
				}
				c++;
			}else{
				try {
					listMessages = gBD.consultarMensajes(2, userEmM.getNombreUsuario(),userRecM.getNombreUsuario());
					if(!listMessages.isEmpty()){
						if(ultimoMensajitopapu != listMessages.get(0).getIdMensaje()){
						printMessage(2);						
						}
					}
				} catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e  , "Data base error",
								JOptionPane.ERROR_MESSAGE);
				}	
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Error al cargar mensajes"+e);
				}
			}
		}
		
	}
/**
 * Método que pinta los mensajes que se encuentran de la conversación
 * @param opc variable que difiere entre cuando se cargarán todos los mensajes o sólo el ultimo
 */

private void printMessage(int opc) {
	Font fuente=new Font("Dialog", Font.BOLD, 12);
		if (opc==1) {
			messengerView.getPanel().removeAll();
			
			y=10;
			h=450;
			messengerView.getPanel().setPreferredSize(new Dimension(250, h));
			
			for (int i = 0; i < listMessages.size(); i++) {
					
					
					JTextArea lbl = new JTextArea(listMessages.get(i).getFecha()+" a las "
							+listMessages.get(i).getHora()+"\n"
							+listMessages.get(i).getUsEm()+": \n"+
							listMessages.get(i).getMensaje());
					lbl.setBounds(20, y, 250, 70);
					lbl.setWrapStyleWord(true);
				    lbl.setLineWrap(true);
				    lbl.setOpaque(false);
				    lbl.setEditable(false);
				    lbl.setFocusable(false);
				    lbl.setFont(fuente);
					messengerView.getPanel().setPreferredSize(new Dimension(250, h));
					messengerView.getPanel().add(lbl);
					y += 80;
					h += 84;
					ultimoMensajitopapu=listMessages.get(i).getIdMensaje();
			}
		}else{
			JTextArea lbl = new JTextArea(listMessages.get(0).getFecha()+" a las "
					+listMessages.get(0).getHora()+"\n"
					+listMessages.get(0).getUsEm()+": \n"+
					listMessages.get(0).getMensaje());
			lbl.setBounds(20, y, 250, 70);
			lbl.setWrapStyleWord(true);
		    lbl.setLineWrap(true);
		    lbl.setOpaque(false);
		    lbl.setEditable(false);
		    lbl.setFocusable(false);
		    lbl.setFont(fuente);
			messengerView.getPanel().setPreferredSize(new Dimension(250, h));
			messengerView.getPanel().add(lbl);
			y += 80;
			h += 84;
			ultimoMensajitopapu=listMessages.get(0).getIdMensaje();
		}
		messengerView.getScrollPane().getVerticalScrollBar().setValue(messengerView.getScrollPane().getVerticalScrollBar().getValue()+1);
	}	
}


