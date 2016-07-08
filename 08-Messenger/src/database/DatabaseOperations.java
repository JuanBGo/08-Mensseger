package database;

import java.sql.SQLException;
import java.util.List;

import model.MessageModel;
import model.UserModel;
/**
 * 
 * @author JuanAlberto
 *
 */
public interface DatabaseOperations {
	/**
	 * M�todo para registrar un usuario en la BD 
	 * @param usuarioModel modelo del usuario
	 * @throws SQLException Excepci�n de transacci�n SQL
	 */
	public void registrarUsuario(UserModel usuarioModel) throws SQLException;
	
	/**
	 * M�todo para verificar el acceso del usuario
	 * @param usuarioModel modelo del usuario
	 * @return boolean estado del acceso
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public boolean verificarUsuario(UserModel usuarioModel) throws SQLException;
	
	/**
	 * M�todo para consultar los mensajes del hilo
	 * @return Lista con los modelos de los mensajes
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public List<MessageModel> consultarMensajes(int opt, String IdUSuarioEmisor, String idUsuarioReceptor) throws SQLException;
	/**
	 * 
	 * M�todo para consultar los usuarios excluyendo al id
	 * @param id identificador de usuario que ser� exclu�do
	 * @return Lista con los modelos de los mensajes
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public List<UserModel> consultarUsers(int id) throws SQLException;
	
	/**
	 * M�todo para agregar un nuevo mensaje al pull
	 * @param mensajeModel modelo del mensaje
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public void agregarMensaje(MessageModel mensajeModel) throws SQLException;
	
	/**
	 * M�todo crear a un modelo del usuario recetor
	 * @param userReceptor nick name del usuario
	 * @return modelo de usuario 
	 * @throws SQLException excepci�n sql
	 */
	public UserModel createReceptor(String userReceptor) throws SQLException;
}



