
package Logica;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class SocketClient {
    public static void sendPaquete(PaqueteEnvio paquete){
        try{
            
            Socket destino = new Socket(paquete.getIp(), Integer.parseInt(paquete.getPuerto()));
            ObjectOutputStream stream = new ObjectOutputStream(destino.getOutputStream());
            stream.writeObject(paquete);
                
            stream.close();
            destino.close();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
}
