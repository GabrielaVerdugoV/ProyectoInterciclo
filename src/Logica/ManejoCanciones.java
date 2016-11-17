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
public class ManejoCanciones {
    public ArrayList<Cancion> getCanciones(){
        ResultSet rs = null;
        ManejadorDB db = new ManejadorDB();
        db.conectarbd();
        ArrayList <Cancion> res = new ArrayList <Cancion>();
        rs = db.getRS("Select * from Cancion order by idcancion");
        try{
            while(rs.next()){
                Cancion tmp = new Cancion();
                tmp.setNombre(rs.getString("RutaCancion"));
                tmp.setId(rs.getInt("IdCancion"));
                res.add(tmp);
            }
        }catch(Exception ex){ex.printStackTrace();}
        db.desconectar();
        return res;
    }

    public ArrayList<CancionxUsuario> getCancionesFavoritas(String Usuario){
        ResultSet rs = null;
        Integer aux=0;
        ManejadorDB db = new ManejadorDB();
        db.conectarbd();
        ArrayList <CancionxUsuario> res = new ArrayList <CancionxUsuario>();
        rs = db.getRS("Select cu.*,u.*,c.* from CancionesxUsuario as cu,Usuarios as u,Cancion as c where cu.codigo_usuario=u.codigo_usuario and cu.idcancion=c.idcancion order by cu.Codigo_Usuario,cu.IdCancion");
        try{
            while(rs.next()){
                CancionxUsuario tmp = new CancionxUsuario();
                tmp.setCodigo(rs.getString("Codigo_Usuario"));
                tmp.setNombre(rs.getString("Nombre_Usuario"));
                tmp.setRuta(rs.getString("Clave_Usuario"));
                aux=Integer.getInteger(rs.getString("Id"));
                tmp.setId(aux);
                res.add(tmp);
            }
        }catch(Exception ex){ex.printStackTrace();}
        db.desconectar();
        return res;
    }

    ArrayList<Cancion> v;
    public ManejoCanciones(){
//        ManejadorDB d = new ManejadorDB();
        v = new ArrayList<Cancion>();
        v = this.getCanciones();          
    }

    public Cancion getCancion(int i) {
            return v.get(i);
    }
    
    public int largo(){
            return v.size();
    }
    
    public boolean guardarCanciones(Cancion unJugador){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("Insert into Cancion (RutaCancion,IdCancion)values ('"+unJugador.getNombre()+"',"+unJugador.getId()+");");
    }
    public boolean eliminarCancion(int id){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE FROM Cancion where (IdCancion="+ id+ ");");
    }
    public boolean eliminarCanciones(){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("DELETE * FROM Cancion;");
    }
    public boolean updateId(int idV,int idN){
        ManejadorDB db = new ManejadorDB();
        return db.ejecutar("UPDATE Cancion SET IdCancion ="+idN+" where (IdCancion ="+idV+");");
    }
    
    
//    public boolean updateId1(int idV,int idN){
//        ManejadorDB db = new ManejadorDB();
//        return db.ejecutar("UPDATE Cancion SET IdCancion ="+idN+" where (IdCancion ="+idV+");");
//    }

}
