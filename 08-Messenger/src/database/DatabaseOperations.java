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
	 * Método para registrar un usuario en la BD 
	 * @param usuarioModel modelo del usuario
	 * @throws SQLException Excepción de transacción SQL
	 */
	public void registrarUsuario(UserModel usuarioModel) throws SQLException;
	
	/**
	 * Método para verificar el acceso del usuario
	 * @param usuarioModel modelo del usuario
	 * @return boolean estado del acceso
	 * @throws SQLException Excepción de la transacción SQL
	 */
	public boolean verificarUsuario(UserModel usuarioModel) throws SQLException;
	
	/**
	 * Método para consultar los mensajes del hilo
	 * @return Lista con los modelos de los mensajes
	 * @throws SQLException Excepción de la transacción SQL
	 */
	public List<MessageModel> consultarMensajes(int opt, String IdUSuarioEmisor, String idUsuarioReceptor) throws SQLException;
	/**
	 * 
	 * Método para consultar los usuarios excluyendo al id
	 * @param id identificador de usuario que será excluído
	 * @return Lista con los modelos de los mensajes
	 * @throws SQLException Excepción de la transacción SQL
	 */
	public List<UserModel> consultarUsers(int id) throws SQLException;
	
	/**
	 * Método para agregar un nuevo mensaje al pull
	 * @param mensajeModel modelo del mensaje
	 * @throws SQLException Excepción de la transacción SQL
	 */
	public void agregarMensaje(MessageModel mensajeModel) throws SQLException;
	
	/**
	 * Método crear a un modelo del usuario recetor
	 * @param userReceptor nick name del usuario
	 * @return modelo de usuario 
	 * @throws SQLException excepción sql
	 */
	public UserModel createReceptor(String userReceptor) throws SQLException;
}



