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
public class Usuario {
    private String codigo;
    private String nombre;
    private String clave;
    private String email;
    
    public Usuario(){
        codigo = "";
        nombre = "";
        clave = "";
        email = "";
    }

    public void setCodigo(String c){
        codigo = c;
    }

    public void setNombre(String n){
        nombre = n;
    }

    public void setClave(String c){
        clave = c;
    }

    public void setEmail(String e){
        email = e;
    }
    
    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public String getClave(){
        return clave;
    }
    public String getEmail(){
        return email;
    }
}
