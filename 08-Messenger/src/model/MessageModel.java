package model;

/**
 * 
 * @author JuanAlberto
 *
 */
public class MessageModel {
	
	private int idMensaje;
	private String usuarioRemitente;
	private String usuarioReceptor;
	private String mensaje;
	private String fecha;
	private String hora;
	
	/**
	 * @return the idMensaje
	 */
	public int getIdMensaje() {
		return idMensaje;
	}
	/**
	 * @param idMensaje the idMensaje to set
	 */
	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getUsRec() {
		return usuarioReceptor;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setUsRec(String nombreUsuario) {
		this.usuarioReceptor = nombreUsuario;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getUsEm() {
		return usuarioRemitente;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setUsEm(String nombreUsuario) {
		this.usuarioRemitente = nombreUsuario;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
}
