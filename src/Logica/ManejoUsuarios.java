/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Clases.*;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Inventor
 */
public class ManejoUsuarios {
    ArrayList<Usuario> v;
    public ArrayList<Usuario> getUsuarios(){
        ResultSet rs = null;
        ManejadorDB db = new ManejadorDB();
        db.conectarbd();
        ArrayList <Usuario> res = new ArrayList <Usuario>();
        rs = db.getRS("Select * from Usuarios order by Codigo_Usuario");
        try{
            while(rs.next()){
                Usuario tmp = new Usuario();
                tmp.setCodigo(rs.getString("Codigo_Usuario"));
                tmp.setNombre(rs.getString("Nombre_Usuario"));
                tmp.setClave(rs.getString("Clave_Usuario"));
                tmp.setEmail(rs.getString("Email_Usuario"));
                res.add(tmp);
            }
        }catch(Exception ex){ex.printStackTrace();}
        db.desconectar();
        return res;
    }
    
    public Usuario getUsuario(String Codigo,String Clave){
        ResultSet rs = null;
        Usuario tmp = new Usuario();
        ManejadorDB db = new ManejadorDB();
        db.conectarbd();
        rs = db.getRS("Select * from Usuarios where Codigo_Usuario='"+Codigo+"' and Clave_Usuario='"+Clave+"';");
        try{
            while(rs.next()){
                tmp.setCodigo(rs.getString("Codigo_Usuario"));
                tmp.setNombre(rs.getString("Nombre_Usuario"));
                tmp.setClave(rs.getString("Clave_Usuario"));
                tmp.setEmail(rs.getString("Email_Usuario"));
            }
        }catch(Exception ex){ex.printStackTrace();}
        db.desconectar();
        return tmp;
    }

    public ManejoUsuarios(){
        //ManejadorDB d = new ManejadorDB();
        v = new ArrayList<Usuario>();
        v = getUsuarios();          
    }

    public int largo(){
            return v.size();
    }
    
    public Usuario getUsuario(int i) {
            return v.get(i);
    }
    
    public boolean guardarUsuarios(Usuario unJugador){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("Insert into Usuarios (Codigo_Usuario,Nombre_Usuario,Clave_Usuario,Email_Usuario)values ('"+unJugador.getCodigo()+"','"+unJugador.getNombre()+"','"+unJugador.getClave()+"','"+unJugador.getEmail()+"');");
    }
    public boolean eliminarUsuario(String Codigo){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE FROM Usuarios where (Codigo_Usuario='"+ Codigo+ "');");
    }
    public boolean eliminarUsuarios(){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE * FROM Usuarios;");
    }
    public boolean updateUsuario(Usuario unJugador){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("UPDATE Usuarios SET Nombre_Usuario='"+unJugador.getNombre()+"',Clave_Usuario='"+unJugador.getClave()+"',Email_Usuario='"+unJugador.getEmail()+"' where (Codigo_Usuario ='"+unJugador.getCodigo()+"');");
    }
}
