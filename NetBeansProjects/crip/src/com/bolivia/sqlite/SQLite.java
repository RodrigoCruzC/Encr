package com.bolivia.sqlite;
import com.bolivia.crypto.Encriptacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class SQLite extends Encriptacion{

    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db= "jdbc:mysql:/localhost/encrip";   

    /**
 * Constructor de clase que se conecta a la base de datos SQLite
 */
    public SQLite()
    {
      try{
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:" + this.db );
         System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
      }catch(Exception e){
         System.out.println(e);
      }

    }

   /** METODO PARA INSERTAR UN REGISTRO EN LA BASE DE DATOS
 * @param table Nombre de la tabla
 * @param fields String con los nombres de los campos donde insertar Ej.: campo1,campo2campo_n
 * @param values String con los datos de los campos a insertar Ej.: valor1, valor2, valor_n
 * @return Boolean
 */
    public boolean insert(String table, String fields, String values)
    {
        boolean res=false;        
        String q=" INSERT INTO encrip " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";        
        try {
            PreparedStatement pstm = connection.prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(Exception e){
            System.err.println( e.getMessage() );
        }
      return res;
    }

    /** METODO PARA REALIZAR UNA CONSULTA A LA BASE DE DATOS
 * @param ninguno
 * @return String tabla 
 */
    public String printTable()
    {
       String res=" Nombre | Apellido \n ";
       try {
         statement = connection.createStatement();
         resultSet = statement.executeQuery(" SELECT encrip FROM Nombres ; ");
         while (resultSet.next())
         {
           res+= decrypt( resultSet.getString("Nombre") ) + " | " +  decrypt(resultSet.getString("Apellido")) + " \n ";
         }
        }
        catch (SQLException ex) {
           System.out.println(ex);
        }
       return res;
    }

}