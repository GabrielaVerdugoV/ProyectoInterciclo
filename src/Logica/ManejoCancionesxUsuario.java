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
public class ManejoCancionesxUsuario {
     ArrayList<CancionxUsuario> v1;
     public String UsuarioFavoritos;
     public ArrayList<CancionxUsuario> getCancionesUsuario(){
        CancionxUsuario tmp = new CancionxUsuario();
        ResultSet rs = null;
        Integer aux=0;
        String codigo="";
        String ruta="";
        String nombre="";
        ManejadorDB db = new ManejadorDB();
        db.conectarbd();
        ArrayList <CancionxUsuario> res = new ArrayList <CancionxUsuario>();
        rs = db.getRS("Select cu.codigo_usuario,cu.idcancion,u.nombre_usuario,c.rutacancion from CancionesxUsuario as cu,Usuarios as u,Cancion as c where cu.codigo_usuario=u.codigo_usuario and cu.idcancion=c.idcancion order by cu.Codigo_Usuario,cu.IdCancion");
        try{
            while(rs.next()){
//                tmp.setCodigo(rs.getString("Codigo_Usuario"));
//                tmp.setNombre(rs.getString("Nombre_Usuario"));
//                tmp.setClave(rs.getString("Clave_Usuario"));
//                tmp.setEmail(rs.getString("Email_Usuario"));
//                res.add(tmp);

                
                codigo=rs.getString("codigo_usuario");
                nombre=rs.getString("nombre_usuario");
                ruta=rs.getString("rutausuario");
                aux=Integer.getInteger(rs.getString("idcancion").toString());
                tmp.setCodigo(codigo);
                tmp.setNombre(nombre);
                tmp.setRuta(ruta);
                tmp.setId(aux);
                res.add(tmp);
            }
        }catch(Exception ex){ex.printStackTrace();}
        db.desconectar();
        return res;
    }
     
    public ManejoCancionesxUsuario(){
        ManejadorDB d = new ManejadorDB();
        v1 = new ArrayList<CancionxUsuario>();
        v1 = this.getCancionesUsuario();          
    }
  
    public void setUsuario(String c){
        UsuarioFavoritos = c;
    }
    
    public String getUsuario(){
        return UsuarioFavoritos;
    }
    
    public ArrayList<CancionxUsuario> ListaCanciones(){
        ManejoCancionesxUsuario d = new ManejoCancionesxUsuario();
        v1 = new ArrayList<CancionxUsuario>();
        v1 = d.getCancionesUsuario();          
        return v1;
    }
     
    public boolean guardarCancionesUsuario(CancionxUsuario unJugador){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("Insert into CancionesxUsuario (Codigo_Usuario,IdCancion)values ('"+unJugador.getCodigo()+"','"+unJugador.getId()+"');");
    }
    public boolean eliminarCancionesUsuario(String Codigo){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE FROM CancionesxUsuario where (Codigo_Usuario='"+ Codigo+ "');");
    }
    
        //camcopmes favprotas
    public CancionxUsuario getCancion(int i) {
            return v1.get(i);
    }
    
    public int largo(){
            return v1.size();
    }
    
    public boolean guardarCanciones(CancionxUsuario unJugador){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("Insert into CancionesxUsuario (Codigo_Usuario,IdCancion)values ('"+unJugador.getCodigo()+"',"+unJugador.getId()+");");
    }
    public boolean eliminarCancion(int id,String Usuario){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE FROM CancionesxUsuario where (IdCancion="+ id+ " and Codigo_Usuario='"+Usuario+"');");
    }
    public boolean eliminarCanciones(String Usuario){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE * FROM CancionesxUsuario where Codigo_Usuario='"+Usuario+"';");
    }

}
