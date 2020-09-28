package View;


import Logica.PantallaControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class PantallaIni extends JFrame{
    private JTextField mensaje, ip , nombre, puerto;
    private JButton boton1;
    private JLabel label1,labelPuerto,labelIp,labelNombre;
    private JTextArea area1;
    private JScrollPane scroll;
    
    public PantallaIni(PantallaControl ctrl){
        
        setLayout(null);
        setTitle("Whatsapp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0,0,1267,800);
        setLocationRelativeTo(null);
        setResizable(false);

        iniComponentes(ctrl);

        setVisible(true);
        
        
    }
    /*clase PantallaIni
    crea los elementos existentes dentro del JFrame
    entradas: ctrl
    salidas: JTextField, JButton, JLabel, JTextArea, JScrollPane
    */
    private void iniComponentes(PantallaControl ctrl){
        
        
        label1 = new JLabel("Ingrese un Mensaje");
        label1.setBounds(400,710,112,15);
        add(label1);
        
        labelNombre = new JLabel("Ingrese su Nombre");
        labelNombre.setBounds(10,0,200,15);
        add(labelNombre);
        
        labelIp = new JLabel("Ip");
        labelIp.setBounds(400,0,40,15);
        add(labelIp);
        
        labelPuerto = new JLabel("Puerto");
        labelPuerto.setBounds(520,0,40,15);
        add(labelPuerto);
        
        mensaje = new JTextField();
        mensaje.setBounds(520,710,600,20);
        add(mensaje);
        
        puerto = new JTextField();
        puerto.setBounds(520,20,100,20);
        add(puerto);
        
        ip = new JTextField();
        ip.setBounds(400,20,100,20);
        add(ip);
        
        nombre = new JTextField();
        nombre.setBounds(10,20,380,20);
        add(nombre);
        
        boton1 = new JButton("Enviar");
        boton1.setBounds(1150,710,80,20);
        boton1.addActionListener(ctrl);
        add(boton1);
        
        area1 = new JTextArea();
        area1.setEditable(false);
        scroll = new JScrollPane(area1);
        scroll.setBounds(400,50,830,650);
        add(scroll);
    }
    
    public String getMensaje(){
        return mensaje.getText();
    
    }
    public void printMensaje(String msg){
        area1.append(msg);
        
    }
    public String getNombre(){
        return nombre.getText();
    }
    public String getIp(){
        return ip.getText();
    }
    public String getPuerto(){
        return puerto.getText();
    }
}