/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clas� que conecta la base de datos, usando singleton
 * @author JuanAlberto
 */
//Desing Pattern Singleton
/*
 Tiene 3 reglas:
 * 1- debe tener una variable privada y estatica del mismo nombre que la clase (tener istancia de clase dentro de la clase)
 * 2.- tener un constructor privado dentro de la misma clase
 * 3.- tener un método queretorne la instancia de la clase
 */

public class SingleDBConnection {
    private static SingleDBConnection instance;

    private static String database, user, pass;
    private Connection connection;
    private Statement statement;

    /**
 *	constructor de la clase  
 * @throws ClassNotFoundException
 */
    private SingleDBConnection() throws ClassNotFoundException{
        SingleDBConnection.database = "";
        SingleDBConnection.user = "";
        SingleDBConnection.pass = "";

        Class.forName("com.mysql.jdbc.Driver");

    }
/**
 * m�todo que retorna la instancia de la base de datos
 * @param database nombre de la base de datos
 * @param user usuario de la base de datos
 * @param pass contrase�a de la base de datos
 * @return instancia de la base de datos
 * @throws ClassNotFoundException
 */
    public static SingleDBConnection getInstance(String database, String user, String pass) throws ClassNotFoundException{
        if(instance == null){
        instance = new SingleDBConnection();
        }
                SingleDBConnection.database = database;
                SingleDBConnection.user = user;
                SingleDBConnection.pass = pass;

                return instance;
        }
/**
 * m�todo que abre conexi�n con la base 
 * @return respuesta 
 */
     public boolean open(){
        String url = "jdbc:mysql://127.0.0.1:3306/"+ SingleDBConnection.database;
        try {
            this.connection = DriverManager.getConnection(url, user, pass);
            this.statement = this.connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
         return true;
     }
/**
 * M�todo que lee de la base de datos
 * @param query consulta sql
 * @return resultados de la consulta
 * @throws SQLException
 */
     public ResultSet readData(String query) throws SQLException{
          return this.statement.executeQuery(query);
          }
/**
 * M�todo que hace cambios sobre la base de datos
 * @param sql script sql
 * @throws SQLException
 */
     public void updateData(String sql) throws SQLException{
         this.statement.executeUpdate(sql);
     }
/**
 * M�todo que cierra conexi�n
 */
    @Override
    protected void finalize() throws Throwable {
       this.connection.close();
       this.statement.close();
        super.finalize();
    }



}
