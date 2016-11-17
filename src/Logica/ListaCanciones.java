/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import Clases.*;
//import Persistencia.Broker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ListaCanciones {    
    ArrayList<Cancion> v;
    public ListaCanciones(){
        ManejoCanciones d = new ManejoCanciones();
        v = new ArrayList<Cancion>();
        v = d.getCanciones();          
    }
    
   
    
    //metodos para cancion a lista de canciones generales
    public Cancion getCancion(int i) {
            return v.get(i);
    }
    public int largo(){
            return v.size();
    }
    public boolean guardarCancion(Cancion s){
        ManejoCanciones d = new ManejoCanciones();
        if (d.guardarCanciones(s)) {
            v.add(s);
            return true;
        }
        else return false;
    }

    public boolean eliminarCancion(int s){
        ManejoCanciones b = new ManejoCanciones();
        if (b.eliminarCancion(s)) {
            for(int i = 0;i<v.size();i++){
                if (v.get(i).getId() == s)
                    v.remove(i);
            }
            return true;
        }else return false;
    }
    public boolean eliminarCanciones(){
        ManejoCanciones b = new ManejoCanciones();
        v.removeAll(v);
        return b.eliminarCanciones();
    }
    public boolean updateId(int c,int idN){
        ManejoCanciones b = new ManejoCanciones();
        return b.updateId(c,idN);
    }
    
}
