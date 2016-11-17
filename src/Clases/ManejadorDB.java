/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
//librerias para manejo de base de datos y herramientas extras
import java.io.File;
import java.util.Properties;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Inventor
 */
public class ManejadorDB {
    private Connection cnn = null;
    //metodo que conecta a la base de datos especificada
    public void conectarbd(){
            try {
                //Parametros que indica que se va a usar la base de datos de access    
                    Properties props = new Properties();
                    props.put ("charSet", "iso-8859-1");
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    // Obtiene la informacion del archico de la base de datos a usarse 
                    File f = new File("BaseManejoCanciones.mdb");
                    String myDB ="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+f.getAbsolutePath()+"";
                    // Conecta hacia la base de datos con los parametros antes indicados
                    cnn = DriverManager.getConnection(myDB,props);
            }
            catch(ClassNotFoundException e) {
                    System.out.println("Error de driver");
            }
            catch(SQLException e2) {
                    JOptionPane.showMessageDialog(null, e2);
            }
    }

    //metodo que desconecta de la base de datos
    public void desconectar() {
            try {
                    cnn.close();
            }
            catch(SQLException e) {
                    System.out.println("Error al desconectar");
            }
    }
    
    //metodo que realiza una consulta especifica hacia la base de datos
    public ResultSet getRS(String sql) {
            ResultSet res = null;
            try{
                    Statement st = cnn.createStatement();
                    res = st.executeQuery(sql);
            }
            catch(SQLException e) {
            }
            return res;
    }
    
    //metodo que verifica y realiza una transaccion hacia la base de datos
    public boolean ejecutar(String sql){
            try{
                    this.conectarbd();
                    Statement st = cnn.createStatement();
                    st.executeUpdate(sql);
                    st.close();
                    this.desconectar();
                    return true;
            }
            catch(SQLException e) {
                    return false;
            }
    }
}
