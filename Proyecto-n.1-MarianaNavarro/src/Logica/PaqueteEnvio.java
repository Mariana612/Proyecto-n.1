
package Logica;

import java.io.Serializable;

class PaqueteEnvio implements Serializable{ 
    
    private String nombre, ip, mensaje, puerto; //Objetos Serializables que se envian por el socket
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getIp(){
        return ip;
    }
    public void setIp(String ip){
        this.ip= ip;
    }
    public String getMensaje(){
        return mensaje;
    }
    public void setMensaje(String mensaje){
        this.mensaje= mensaje;
    }
    public String getPuerto(){
        return puerto;
    }
    public void setPuerto(String puerto){
        this.puerto = puerto;
    }
}

