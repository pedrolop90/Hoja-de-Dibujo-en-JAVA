/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class prueba extends JFrame{
    
    private engrane engrane;
    
    public prueba(){
        setSize(300,325);
        setLayout(null);
        engrane=new engrane();
        engrane.setSize(300,300);
        add(engrane);
        engrane.start();
    }
    
    public static void main(String[] args) {
        prueba p=new prueba();
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

class engrane extends JPanel{
    
    private javax.swing.Timer t;
    private int variable=0;
    private int alto=300;
    private int ancho=300;
    private int tiempo=250;
    private Graphics2D g2;
    private int img=0;
    
    
    public engrane(){
        setLayout(null);
        t = new javax.swing.Timer(tiempo,new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      if(img==0){
                          img=1;
                      }else if(img==1){
                          img=0;
                      }
                      update();  
                  }
              }); 
        
    }
     public void paintComponent(Graphics g) {
         super.paintComponents(g);
          g2 = (Graphics2D) g;
          g2.drawLine(ancho/2, 0, ancho/2, alto);
          g2.drawLine(0, alto/2, alto, ancho/2);
         fondo(g);
         figura(g);
     }

     public void figura(Graphics g) {
         ImageIcon imagen = null;
         ImageIcon imagen2=null;
         switch(img){
             case 0: imagen=new ImageIcon("imagenes/engrane.png");
                     imagen2=new ImageIcon("imagenes/engrane2.png");
                  break;
             case 1: imagen=new ImageIcon("imagenes/engrane2.png");
                     imagen2=new ImageIcon("imagenes/engrane.png");
                 break;
             
         }
         
         g2.drawImage(imagen.getImage(),100,100, imagen.getIconWidth(), imagen.getIconHeight(), null);
         g2.drawImage(imagen2.getImage(),35,30, imagen2.getIconWidth(), imagen2.getIconHeight(), null);
         g2.drawImage(imagen2.getImage(),167,32, imagen2.getIconWidth(), imagen2.getIconHeight(), null);
         g2.drawImage(imagen2.getImage(),35,69+imagen.getIconHeight(), imagen2.getIconWidth(), imagen2.getIconHeight(), null);
         g2.drawImage(imagen2.getImage(),167,69+imagen.getIconHeight(), imagen2.getIconWidth(), imagen2.getIconHeight(), null);
     }

     public void fondo(Graphics g) {
         g2.setColor(Color.red);
         g2.fillRect(0, 0, ancho, alto);
     }
     
     
    public void update(){
        this.repaint();
    }
    public void start() {
        t.start();
    }
    public void reiniciar(){
        t.restart();
    }

}
