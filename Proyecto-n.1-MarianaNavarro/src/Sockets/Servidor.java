
package Sockets;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class PantallaIniServ extends JFrame implements Runnable{
    public PantallaIniServ(){
    
    setTitle("Whatsapp");
    setBounds(0,0,1267,800);
    setLocationRelativeTo(null);
    setResizable(false);
    
    InteraccionServ inteServidor = new InteraccionServ ();
    add(inteServidor);
    
    setVisible(true);
    
    Thread hilo = new Thread(this);
    hilo.start();
    
    }
    private JTextArea area1;

    @Override
    public void run() {
        
        try {
            ServerSocket servidor = new ServerSocket(9000);
            String nombre, ip, mensaje;
            PaqueteEnvio paqueterecibido;
            
            while(true){
                Socket socketServ = servidor.accept();
                ObjectInputStream paqueteDatos = new ObjectInputStream(socketServ.getInputStream());
                paqueterecibido = (PaqueteEnvio)paqueteDatos.readObject();
                nombre =  paqueterecibido.getNombre();
                ip = paqueterecibido.getIp();
                mensaje = paqueterecibido.getMensaje();
                
                area1.append(nombre + ":     " +mensaje+ "\n"  );
                Socket eviarDestino = new Socket(ip, 9050);
                ObjectOutputStream Reenvio = new ObjectOutputStream(eviarDestino.getOutputStream());
                Reenvio.writeObject(paqueterecibido);
                
                eviarDestino.close();
                socketServ.close();
                
                /* DataInputStream tex_entrada = new DataInputStream(socketServ.getInputStream());
                String mensajeTxt = tex_entrada.readUTF();
                ;
                socketServ.close();*/
            }
            
        } catch (IOException | ClassNotFoundException  ex) {
            System.out.println(ex.getMessage());
            
            
        } 
        
    }
    class InteraccionServ  extends JPanel{
    
    private JTextField field1;
    private JButton boton1;
    private JLabel label1;
    
    private JScrollPane scroll;
    
    public InteraccionServ (){
        setLayout(null);
        
        label1 = new JLabel("Ingrese un Mensaje");
        label1.setBounds(400,710,112,15);
        add(label1);
        
        field1 = new JTextField();
        field1.setBounds(520,710,600,20);
        add(field1);
        
        boton1 = new JButton("Enviar");
        boton1.setBounds(1150,710,80,20);
        add(boton1);
        
        
        area1 = new JTextArea();
        area1.setEditable(false);
        scroll = new JScrollPane(area1);
        scroll.setBounds(400,50,830,650);
        add(scroll);
        
        
                
    } 
    
}
}

public class Servidor {
    public static void main(String[]args){
        PantallaIniServ clienteServ = new PantallaIniServ();
        clienteServ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


