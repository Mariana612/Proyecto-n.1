
package Logica;

import View.PantallaIni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaControl implements ActionListener{
    
    PantallaIni vista;
    static PantallaControl instancia = null;
    
    public static PantallaControl getInstance(){
        if(instancia == null){
            instancia = new PantallaControl();
        }
        return instancia;
    }
    
    private PantallaControl(){
        vista = new PantallaIni(this);
        Servidor.ServerIni(9001);
    }
    
    public void infoRecibido(PaqueteEnvio info){
        vista.printMensaje(info.getNombre()+":    " + info.getMensaje()+"\n" );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.printMensaje(vista.getNombre()+":    " + vista.getMensaje()+"\n" );

                
        PaqueteEnvio datos = new PaqueteEnvio(); //Aquí se crea un paquete con la infomracion del usuario

        datos.setNombre(vista.getNombre());
        datos.setIp(vista.getIp());
        datos.setMensaje(vista.getMensaje());
        datos.setPuerto(vista.getPuerto());
        
        SocketClient.sendPaquete(datos);
 
    }
    
}
