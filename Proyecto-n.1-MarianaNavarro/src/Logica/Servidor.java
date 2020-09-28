
package Logica;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable{
    int puerto;
    
    Servidor(int puerto){
        this.puerto = puerto;
        
    }
    /*
    Server Ini
    se crea un thread para el ServerSocket
    entrada: puerto
    salidas: -
    */
    public static void ServerIni(int puerto){
        Servidor server = new Servidor(puerto);
        Thread hilo = new Thread(server);
        hilo.start();
    }
    
    
    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(puerto);  //se crea el ServerSocket y se crea un loop para aceptar la coneccion con el servidor
            while(true){
                Socket socketServ = servidor.accept();
                
                ObjectInputStream streamImp = new ObjectInputStream(socketServ.getInputStream());
                PaqueteEnvio info = (PaqueteEnvio)streamImp.readObject();
                PantallaControl.getInstance().infoRecibido(info);
          
                socketServ.close();
                
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
}
