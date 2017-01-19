

package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class principal extends JFrame{
    
    private paint paint;
    
    public principal(){
        setSize(800,700);
        setLayout(null);
        setResizable(false);
        paint=new paint();
        paint.setBounds(0,0,800,700);
        paint.start();
        add(paint);
    }
    
    public static void main(String[] args) {
        principal p=new principal();
        p.setVisible(true);
        p.setDefaultCloseOperation(3);
    }
}
class paint extends JPanel{
        
        private Graphics2D g2;
        private javax.swing.Timer t; 
        private int anchoPintar=800;
        private int altoPintar=400;
        private Color colorFondo=Color.WHITE;
        private Color colorFigura=Color.BLACK;
        private JToolBar barraPrincipal;
        private boolean entrarDibujar=false;
        private int x;
        private int y;
        private boolean primerosPuntos=false;
        private boolean segundosPuntos=false;
        private int ancho;
        private int alto;
        private MouseListener accionPrimerPunto;
        private MouseListener accionSegundoPunto;
        private MouseMotionListener accionMover;
        private int figura=0;
        private JToolBar barraFigura;
        private MouseListener accionMoverFigura;
        private MouseMotionListener accionMoverDos;
        
        public paint(){
        barraHerramientas();
         barraHerramientasFigura();
        acciones();
        t=new  javax.swing.Timer(50,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponents(g);
            g2=(Graphics2D)g;
            fondo(g);
            pintar(g);
        }
        
        public void acciones(){
            accionPrimerPunto=new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                          if (e.getY()>=300) {
                            x = e.getX();
                            y = e.getY();
                            entrarDibujar = true;
                            primerosPuntos = false;
                            addMouseMotionListener(accionMover);
                            removeMouseListener(accionPrimerPunto);
                            addMouseListener(accionSegundoPunto);
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                };
             accionSegundoPunto=new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                          if (e.getY()>=300) {
                            ancho = e.getX() - x;
                            alto = e.getY() - y;
                            removeMouseMotionListener(accionMover);
                            removeMouseListener(accionSegundoPunto);
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        addMouseListener(accionMoverFigura);
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                };
            accionMover = new MouseMotionAdapter() {
                    public void mouseMoved(MouseEvent evento) {
                          if(evento.getY()>=300){
                          alto=evento.getY()-y; 
                          }else{
                          alto=300-y; 
                          }
                          ancho=evento.getX()-x;
                          
                    }
                };
            accionMoverFigura=new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                };
            accionMoverDos= new MouseMotionAdapter() {
                    public void mouseMoved(MouseEvent evento) {
                          if(evento.getY()>=300){
                          y=evento.getY();
                          }else{
                          y=300;
                          }
                          x=evento.getX();
                    }
                };
        }
        public void pintar(Graphics g){
            g.setColor(colorFigura);
            if(primerosPuntos){
                addMouseListener(accionPrimerPunto);
            }
            if(entrarDibujar) {
                
                g.setColor(colorFigura);
              if(figura==1) g2.fillRect(x,y, ancho, alto);
              if(figura==2) g2.fillOval(x,y, ancho, alto);
              if(figura==3){
                  g2.drawLine(x, y, ancho+x,y);
                  g2.drawLine(x, y, (ancho/2)+x, alto+y);
                  g2.drawLine(ancho+x, y, (ancho/2)+x,alto+y);
                  
              }
              if(figura==4){
                 t.stop();
                  
                 pintar pintar=new pintar();
                 add(pintar);
              }
            }
        }
        
        public void fondo(Graphics g){
            g.setColor(colorFondo);
            g.fillRect(0, 300, anchoPintar, altoPintar);
        }
        
        public void barraHerramientas(){
            barraPrincipal=new JToolBar();
            barraPrincipal.setOrientation(JToolBar.HORIZONTAL);
            barraPrincipal.setLocation(0,0);
            botonColores(barraPrincipal,"imagenes/botonRojo.png",Color.RED,true);
            botonColores(barraPrincipal,"imagenes/botonNegro.png",Color.black,true);
            botonColores(barraPrincipal,"imagenes/botonBlanco.jpg",Color.white,true);
            botonColores(barraPrincipal,"imagenes/botonAmarillo.png",Color.yellow,true);
            botonColores(barraPrincipal,"imagenes/botonAzul.png",Color.blue,true);
            barraPrincipal.addSeparator(new Dimension(50,barraPrincipal.getHeight()));
            botonBarra("imagenes/rectangulo.png",1);
            botonBarra("imagenes/ovalo.png",2);
            botonBarra("imagenes/triangulo.png",3);
            botonBarra("imagenes/lapiz.png",4);
            add(barraPrincipal);
        }
        
        public void barraHerramientasFigura(){
            barraFigura=new JToolBar();
            barraFigura.setOrientation(JToolBar.HORIZONTAL);
            botonColores(barraFigura,"imagenes/botonRojo.png",Color.RED,false);
            botonColores(barraFigura,"imagenes/botonNegro.png",Color.black,false);
            botonColores(barraFigura,"imagenes/botonBlanco.jpg",Color.white,false);
            botonColores(barraFigura,"imagenes/botonAmarillo.png",Color.yellow,false);
            botonColores(barraFigura,"imagenes/botonAzul.png",Color.blue,false);
            add(barraFigura);
        }
        
        public void botonColores(JToolBar barra,String ruta,Color c,boolean x){
            JButton boton=new JButton(new ImageIcon(ruta));
            boton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(x){
                       colorFondo=c;
                   }else{
                       colorFigura=c;
                   }
                }
            });
             barra.add(boton);
        }
        
        public void botonBarra(String ruta,int fi){
            JButton boton=new JButton(new ImageIcon(ruta));
            boton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                      primerosPuntos=true;
                      figura=fi;
                }
            });
             barraPrincipal.add(boton);
        }
        
        public void start(){
            t.start();
        }
        
        public void update(){
            this.repaint();
        }
        
    }