package Sockets;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.net.*;


import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
class PantallaIni extends JFrame{
    public PantallaIni(){
    setTitle("Whatsapp");
    setBounds(0,0,1267,800);
    setLocationRelativeTo(null);
    setResizable(false);
    
    Interaccion inteCliente = new Interaccion ();
    add(inteCliente);
    
    setVisible(true);
    }
}
class Interaccion  extends JPanel implements Runnable{  // como se necestia un hilo para escuchar se utiliza el metodo run
    
    private JTextField mensaje, ip , nombre;
    private JButton boton1;
    private JLabel label1;
    private JTextArea area1;
    private JScrollPane scroll;
    
    String texto = "";
    String iptxt = "";
    
    
    public Interaccion (){
        setLayout(null);
        
        label1 = new JLabel("Ingrese un Mensaje");
        label1.setBounds(400,710,112,15);
        add(label1);
        
        mensaje = new JTextField();
        mensaje.setBounds(520,710,600,20);
        add(mensaje);
        
        ip = new JTextField();
        ip.setBounds(400,20,100,20);
        add(ip);
        
        nombre = new JTextField();
        nombre.setBounds(10,20,380,20);
        add(nombre);
        
        boton1 = new JButton("Enviar");
        boton1.setBounds(1150,710,80,20);
        EnviarTextoCli evento = new EnviarTextoCli();
        boton1.addActionListener(evento);
        add(boton1);
        
        area1 = new JTextArea();
        area1.setEditable(false);
        scroll = new JScrollPane(area1);
        scroll.setBounds(400,50,830,650);
        add(scroll);
        
        Thread string  = new Thread(this);
        string.start();
        
                
    }

    @Override
    public void run() {
        try{
            ServerSocket servidorCliente = new ServerSocket(9090);
            Socket cliente;
            PaqueteEnvio paqueteRecibido;
            while(true){
            cliente = servidorCliente.accept();
            ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
            paqueteRecibido =(PaqueteEnvio)flujoentrada.readObject();
            area1.append(paqueteRecibido.getNombre() + ":       " + paqueteRecibido.getMensaje());
            
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private class EnviarTextoCli implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                
                Socket socketClient = new Socket("127.0.0.1",9000);
                
                PaqueteEnvio datos = new PaqueteEnvio(); //Aquí se crea un paquete con la infomracion del usuario
                
                datos.setNombre(nombre.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(mensaje.getText());
                ObjectOutputStream PaqueteDatos = new ObjectOutputStream (socketClient.getOutputStream());
                PaqueteDatos.writeObject(datos);
                socketClient.close();
                
                
                /*
                DataOutputStream text_Env = new DataOutputStream(socketClient.getOutputStream());
                text_Env.writeUTF(field1.getText());
                text_Env.close();
                texto+= field1.getText()+"\n";
                area1.setText(texto);
                field1.setText(""); */
                
            }catch(UnknownHostException e1){
                e1.printStackTrace();
            }catch (IOException e1){
                System.out.println(e1.getMessage());
            }
            
        }
    }
    
}
public class Cliente {
    public static void main(String[]args){
        PantallaIni cliente1 = new PantallaIni();
        cliente1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class PaqueteEnvio implements Serializable{
    private String nombre, ip, mensaje;
    
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
}

