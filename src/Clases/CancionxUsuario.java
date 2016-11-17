/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Inventor
 */
public class CancionxUsuario {
    private String codigo;
    private int id;
    private String rutacancion;
    private String nombreusuario;
    
    public CancionxUsuario(){
        codigo = "";
        rutacancion="";
        nombreusuario="";
        id = 0;
    }
    
    public void setCodigo(String c){
        codigo = c;
    }

    public void setRuta(String r){
        rutacancion = r;
    }

    public void setNombre(String n){
        nombreusuario = n;
    }

    public void setId(int iddd){
        id = iddd;
    }
    
    public int getId(){
        return id;
    }
    
    public String getCodigo(){
        return codigo;
    }

    public String getRuta(){
        return rutacancion;
    }

    public String getNombre(){
        return nombreusuario;
    }
}
