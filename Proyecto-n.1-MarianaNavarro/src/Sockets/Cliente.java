package Sockets;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.net.*;


import java.io.DataOutputStream;
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
class Interaccion  extends JPanel{
    
    private JTextField field1;
    private JButton boton1;
    private JLabel label1;
    private JTextArea area1;
    private JScrollPane scroll;
    
    String texto = "";
    
    public Interaccion (){
        setLayout(null);
        
        label1 = new JLabel("Ingrese un Mensaje");
        label1.setBounds(400,710,112,15);
        add(label1);
        
        field1 = new JTextField();
        field1.setBounds(520,710,600,20);
        add(field1);
        
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
        
        
        
                
    }
    private class EnviarTextoCli implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                Socket socketClient = new Socket("127.0.0.1",9000);
                
                DataOutputStream text_Env = new DataOutputStream(socketClient.getOutputStream());
                text_Env.writeUTF(field1.getText());
                text_Env.close();
                texto+= field1.getText()+"\n";
                area1.setText(texto);
                field1.setText("");
                
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

