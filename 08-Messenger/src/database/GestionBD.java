package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JOptionPane;

import model.MessageModel;
import model.UserModel;


/**
 * clase singleton 
 * @author JuanAlberto
 *
 */
public final class GestionBD implements DatabaseOperations{
	
	private static final GestionBD gestionBD = new GestionBD(); 
	private static SingleDBConnection con;

	/**
	 * Constructor de la clase GestionBD
	 */
	private GestionBD(){
		
	}
	
	/**
	 * Método para recuperar estancia de la clase GestionBD
	 * @return GestionBD instancia de la clase
	 */
	public static GestionBD getGestionBD(){
		return gestionBD;
	}
	/**
	 * Método para registrar usuario
	 */
	@Override
	public void registrarUsuario(UserModel usuarioModel) throws SQLException {
		String sql="INSERT INTO users (nickname,password) VALUES"
				+ "('"+usuarioModel.getNombreUsuario()+"',"
				+ "'"+usuarioModel.getPasswordUsuario()+"')";
		
		  try {
	            con = SingleDBConnection.getInstance("messenger", "root", "4427189");
	            if(con.open()){
	                con.updateData(sql);
	                usuarioModel.limpiarModelo();
	            }else{
	            	JOptionPane.showMessageDialog(null, " ERROR!: Not connection to dabtabase found!", "Data base error",
	    					JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (ClassNotFoundException ex) {
	            JOptionPane.showMessageDialog(null, String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex)  , "Data base error",
    					JOptionPane.ERROR_MESSAGE);
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex)  , "Data base error",
    					JOptionPane.ERROR_MESSAGE);
	        }
		}

	/**
	 * Método para validar el acceso al usuario
	 */
	@Override
	public boolean verificarUsuario(UserModel usuarioModel) throws SQLException {
		String sql = "SELECT * FROM users WHERE "
				+ "nickname = '"+usuarioModel.getNombreUsuario()+"' AND "
				+ "password = '"+usuarioModel.getPasswordUsuario()+"'";
		
		ResultSet rs=null;
		
		try {
			con = SingleDBConnection.getInstance("messenger", "root", "4427189");
                if(con.open()){
                rs=con.readData(sql);
                
                if(rs.next()){
        			usuarioModel.setIdUsuario(rs.getInt("us_id"));
        			return true;
        		}else{
        			usuarioModel.limpiarModelo();
        			return false;
        		}
                }else{
	            	JOptionPane.showMessageDialog(null, " ERROR!: Not connection to dabtabase found!", "Data base error",
	    					JOptionPane.ERROR_MESSAGE);
                return false;
                }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex)  , "Data base error",
					JOptionPane.ERROR_MESSAGE);
        	return false;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex)  , "Data base error",
					JOptionPane.ERROR_MESSAGE);
        	return false;
        }
	}

	/**
	 * Método para consultar los mensajes en el hilo
	 */
	@Override
	public List<MessageModel> consultarMensajes(int opt, String nUsuarioEmisor, String nUsuarioReceptor) throws SQLException {
		
		
		String sql=null;
		if(opt==1){
			sql="SELECT * FROM messages WHERE "
			+ "(msg_us_emisor= '"+nUsuarioEmisor+"' AND msg_us_receptor= '"+nUsuarioReceptor+"')"
			+ " OR (msg_us_emisor='"+nUsuarioReceptor+"' AND msg_us_receptor= '"+nUsuarioEmisor+"')";
		}else if(opt==2){
			sql="SELECT * FROM messages WHERE "
					+ "(msg_us_emisor='"+nUsuarioEmisor+"' AND msg_us_receptor='"+nUsuarioReceptor+"')"
					+ " OR (msg_us_emisor= '"+nUsuarioReceptor+"' AND msg_us_receptor= '"+nUsuarioEmisor+"') "
					+ " ORDER BY msg_id DESC LIMIT 1 " ;
		}
		List<MessageModel> lista=new ArrayList<MessageModel>();
		ResultSet rs=null;
		
		
		
		
		
		
		try {
			con = SingleDBConnection.getInstance("messenger", "root", "4427189");
                if(con.open()){
                rs=con.readData(sql);
                int c=0;
                while (rs.next()) {
    				lista.add(new MessageModel());
    				lista.get(c).setIdMensaje(rs.getInt("msg_id"));
    				lista.get(c).setUsEm(rs.getString("msg_us_emisor"));
    				lista.get(c).setUsRec(rs.getString("msg_us_receptor"));
    				lista.get(c).setMensaje(rs.getString("msg_body"));
    				lista.get(c).setFecha(rs.getString("msg_date"));
    				lista.get(c).setHora(rs.getString("msg_time"));
    				c++;
    			}
                
                
                }else{
                	
                	JOptionPane.showMessageDialog(null, " ERROR!: Not connection to dabtabase found!", "Data base error",
	    					JOptionPane.ERROR_MESSAGE);
            
                }
        } catch (ClassNotFoundException ex) {
	        System.out.println(ex);	
            throw new SQLException( String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex));        	

        } catch (SQLException ex) {
        	System.out.println(ex);
        	 throw new SQLException( String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex));
        	
        }
		
		return lista;
	}
	/**
	 * método para consultar usuarios
	 */
	@Override
	public List<UserModel> consultarUsers(int id) throws SQLException {
		String sql = "SELECT * FROM users WHERE NOT us_id = "+id;
		List<UserModel> lista=new ArrayList<UserModel>();
		ResultSet rs=null;
		
		try {
			con = SingleDBConnection.getInstance("messenger", "root", "4427189");
                if(con.open()){
                rs=con.readData(sql);
                int c=0;
                while (rs.next()) {
    				lista.add(new UserModel());
    				lista.get(c).setIdUsuario(rs.getInt("us_id"));
    				lista.get(c).setNombreUsuario(rs.getString("nickname"));
    				c++;
    			}
                
                
                }else{
	            	JOptionPane.showMessageDialog(null, " ERROR!: Not connection to dabtabase found!", "Data base error",
	    					JOptionPane.ERROR_MESSAGE);
            
                }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex)  , "Data base error",
					JOptionPane.ERROR_MESSAGE);
        	

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex)  , "Data base error",
					JOptionPane.ERROR_MESSAGE);
        	
        }
		return lista;
	}


	/**
	 * Método para agregar mensajes a la conversación
	 */
	@Override
	public void agregarMensaje(MessageModel mensajeModel) throws SQLException {
		
		String sql="INSERT INTO messages VALUES(null,"
				+ "'"+mensajeModel.getUsEm()+"',"
				+ "'"+mensajeModel.getUsRec()+"',"
				+ "'"+mensajeModel.getMensaje()+"',"
				+ "'"+mensajeModel.getFecha()+"',"
				+ "'"+mensajeModel.getHora()+"')";
		
		 try {
	            con = SingleDBConnection.getInstance("messenger", "root", "4427189");
	            if(con.open()){
	                con.updateData(sql);
	                
	            }else{
	            	JOptionPane.showMessageDialog(null, " ERROR!: Not connection to dabtabase found!", "Data base error",
	    					JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (ClassNotFoundException ex) {
	            JOptionPane.showMessageDialog(null, String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex)  , "Data base error",
 					JOptionPane.ERROR_MESSAGE);
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex)  , "Data base error",
 					JOptionPane.ERROR_MESSAGE);
	        }
		  
	}

/**
 * metodo para crear un nuevo modelo de usuario receptor
 */
	@Override
	public UserModel createReceptor(String userReceptor) throws SQLException {
		String sql = "SELECT * FROM users WHERE nickname = '"+userReceptor+"'";
		
ResultSet rs=null;
UserModel usReceptor = new UserModel();
		try {
			con = SingleDBConnection.getInstance("messenger", "root", "4427189");
                if(con.open()){
                rs=con.readData(sql);
                
                if(rs.next()){

                	
        			usReceptor.setIdUsuario(rs.getInt("us_id"));
        			usReceptor.setNombreUsuario(rs.getString("nickname"));

        		}
                }else{
	            	JOptionPane.showMessageDialog(null, " ERROR!: Not connection to dabtabase found!", "Data base error",
	    					JOptionPane.ERROR_MESSAGE);
                }
        } catch (ClassNotFoundException ex) {
        	System.out.println(ex);
        	throw new SQLException(String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex));
//            JOptionPane.showMessageDialog(null, String.format("ERROR!: Not found Mysql Connector Driver!: %s", ex)  , "Data base error",
//					JOptionPane.ERROR_MESSAGE);
        	

        } catch (SQLException ex) {
        	throw new SQLException(String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex));
//            JOptionPane.showMessageDialog(null, String.format("ERROR!: SQL operation fail!: %s: --- %s ---", sql,ex)  , "Data base error",
//					JOptionPane.ERROR_MESSAGE);
        	
        }
		
		
		
		return usReceptor;
	}

}