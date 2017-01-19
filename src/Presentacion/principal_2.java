package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class principal_2 extends JFrame{
     
       private paint paint;
       private JToolBar menuPrincipal;
       private JToolBar menuFormas;
       private JPanel panelHerramientas;
       
    public principal_2(){
        setSize(1200,700);
        setLayout(null);
        setResizable(false);
        paint=new paint(this.getWidth()-600,this.getHeight());
        paint.setBounds(0,0,this.getWidth()-600,this.getHeight());
        panelHerramientas=new JPanel();
        panelHerramientas.setBounds(this.getWidth()-600,0,200,this.getHeight());
        panelHerramientas.setLayout(null);
        add(panelHerramientas);
        barraPrincipal();
        add(paint);
        
        
        
    }
           public void barraPrincipal(){
            menuPrincipal=new JToolBar();
            menuPrincipal.setOrientation(JToolBar.VERTICAL);
            menuPrincipal.setBounds(0, 0, 50, 200);
            menuFormas=new JToolBar();
            menuFormas.setOrientation(JToolBar.VERTICAL);
            menuFormas.setBounds(50, 0, 70, 440);
            agregarMenuFormas("imagenes/rectangulo.png",1);
            agregarMenuFormas("imagenes/ovalo.png",2);
            agregarMenuFormas("imagenes/triangulo.png",3);
            agregarMenuFormas("imagenes/lapiz.png",4);
            agregarMenuFormas("imagenes/flecha.png",5);
            menuFormas.addSeparator();
            menuFormas.addSeparator();
            JComboBox aux=new JComboBox();
            ImageIcon a=new ImageIcon();
            aux.addItem(new ImageIcon("imagenes/grosor1.png"));
            aux.addItem(new ImageIcon("imagenes/grosor2.png"));
            aux.addItem(new ImageIcon("imagenes/grosor3.png"));
            aux.addItem(new ImageIcon("imagenes/grosor4.png"));
            aux.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch(aux.getSelectedIndex()){
                        case 0:paint.setGrosorFigura(1);
                            break;
                         case 1:paint.setGrosorFigura(2);
                            break;
                         case 2:paint.setGrosorFigura(3);
                            break;
                         case 3:paint.setGrosorFigura(4);
                            break;
                    }
                    paint.paint(paint.getGraphics());
                }
            });
            menuFormas.add(aux);
            menuFormas.addSeparator();
            menuFormas.addSeparator();
            agregarMenuColores(menuFormas,"imagenes/botonRojo.png",Color.RED,true);
            agregarMenuColores(menuFormas,"imagenes/botonNegro.png",Color.black,true);
            agregarMenuColores(menuFormas,"imagenes/botonBlanco.jpg",Color.white,true);
            agregarMenuColores(menuFormas,"imagenes/botonAmarillo.png",Color.yellow,true);
            agregarMenuColores(menuFormas,"imagenes/botonAzul.png",Color.blue,true);
            agregarMenuColores(menuPrincipal,"imagenes/botonRojo.png",Color.RED,false);
            agregarMenuColores(menuPrincipal,"imagenes/botonNegro.png",Color.black,false);
            agregarMenuColores(menuPrincipal,"imagenes/botonBlanco.jpg",Color.white,false);
            agregarMenuColores(menuPrincipal,"imagenes/botonAmarillo.png",Color.yellow,false);
            agregarMenuColores(menuPrincipal,"imagenes/botonAzul.png",Color.blue,false);
            panelHerramientas.add(menuPrincipal);
            panelHerramientas.add(menuFormas);
            
        }
           
           
        public void agregarMenuFormas(String tipo,int fi){
            JButton item=new JButton(new ImageIcon(tipo));
            item.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    paint.setPintarFiguras(true);
                    paint.setPrimerosPuntos(true);
                    paint.setTipoFigura(fi);
                    if(fi==4){
                       paint.setDibujoLibre(true);
                       paint.x2=-1;
                       paint.x=-1;
                    }else{
                        paint.setDibujoLibre(false);
                    }
                }
            });
             menuFormas.add(item);
        }
            
        public void agregarMenuColores(JToolBar barra,String tipo,Color color,boolean x){
            JButton item=new JButton(new ImageIcon(tipo));
            item.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                    if(!x){
                        paint.setLimpiarPantalla(true);
                        paint.setColorFondo(color);
                    }else{
                        paint.setColorFigura(color);
                    }
                     paint.paint(paint.getGraphics());
                }
            });
             barra.add(item);
        }
    
    public static void main(String[] args) {
        principal_2 p=new principal_2();
        p.setVisible(true);
        p.setDefaultCloseOperation(3);
    }
    private class paint extends Canvas{
        
        private int x=-1,y=-1;
        private int x2=-1,y2=-1;
        private int anchoPintar;
        private int altoPintar;
        private MouseListener accionMousePuntos;
        private KeyListener accionTecladoLimpiar;
        private MouseMotionListener accionMouseMovimiento; 
        private boolean limpiarPantalla=true;
        private Color colorFondo=Color.WHITE;
        private Color colorFigura=Color.black;
        private boolean pintarFiguras=false;
        private int tipoFigura=0;
        private boolean primerosPuntos=false;
        private boolean segundosPuntos=false;
        private boolean dibujoLibre;
        private int grosorFigura;
        
        public paint(int anchoP,int altoP){
            anchoPintar=anchoP;
            altoPintar=altoP;
            acciones();
            addKeyListener(accionTecladoLimpiar);
            addMouseListener(accionMousePuntos);
            addMouseMotionListener(accionMouseMovimiento);
        }
        
        public void paint(Graphics g){
            Graphics2D g2=(Graphics2D) g;
            if(limpiarPantalla){
                g2.setColor(Color.BLACK);
                g2.drawRect(0, 0, anchoPintar, altoPintar);
                g2.setColor(colorFondo);
                g2.fillRect(1, 1, anchoPintar-1, altoPintar-1);
                limpiarPantalla=false;
            } 
                if(!dibujoLibre){
                    g2.setColor(colorFondo);
                    g2.fillRect(x, y, x2+x, y2+y);
                }
                 g2.setColor(colorFigura);
                 g2.setStroke(new BasicStroke(grosorFigura));
                switch(tipoFigura){
                    case 1: g2.drawRect(x, y, x2, y2);
                            g2.fillRect(x, y, x2, y2);
                        break;
                    case 2: g2.drawOval(x, y, x2, y2);
                            g2.fillOval(x, y, x2, y2);
                        break;
                    case 3: 
                        g2.drawLine(x, y, x2 + x, y);
                        g2.drawLine(x, y, (x2 / 2) + x, y2 + y);
                        g2.drawLine(x2 + x, y, (x2 / 2) + x, y2 + y);
                        break;
                    case 4:
                        if(x2!=-1&&x!=-1){
                            g2.drawLine(x, y, x2, y2);
                        }
                        break; 
                    case 5:
                        int mitadY=(y2+y)/4;
                        int mitadX=(x2+x)/4;
                        //primeroArriba
                        g2.drawLine(x, (y2+y)+(mitadY), (x2+x)/2, (y2+y)+(mitadY));
                        //primeroAbajo
                        g2.drawLine(x,y+(mitadY),(x2+x)/2,y+(mitadY));
                        //primeraVertical
                        g2.drawLine(x, (y2+y)+(mitadY), x,(y2+y)-(mitadY));
                        //dosLados
                        g2.drawLine((x2+x)/2,(y2+y)-(mitadY), (x2+x)/2,(y2+y));
                        g2.drawLine((x2+x)/2,(y2+y)-(mitadY), (x2+x)/2, y);
                        break;
                }
        }
        
        public void acciones(){
            accionTecladoLimpiar=new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e) {
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyChar()=='n'){
                        limpiarPantalla=true;
                        paint(getGraphics());
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
                
            };
            
            accionMousePuntos=new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                      if(primerosPuntos&&x<=anchoPintar&&!dibujoLibre){
                        x=e.getX();
                        y=e.getY();
                        segundosPuntos=true;
                        primerosPuntos=false;
                      }else if(segundosPuntos&&x<=anchoPintar&&!dibujoLibre){
                          x2=e.getX()-x;
                          y2=e.getY()-y;
                          pintarFiguras=!pintarFiguras;
                          segundosPuntos=false;
                          tipoFigura=0;
                      }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    if (dibujoLibre) {
                        x = e.getX();
                        y = e.getY();
                        paint(getGraphics());
                    }
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                        if (dibujoLibre) {
                        x = -1;
                        x2 = -1;
                     }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                @Override
                public void mouseExited(MouseEvent e) {
                }
            };
            
            accionMouseMovimiento=new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dibujoLibre) {
                        x2 = e.getX();
                        y2 = e.getY();
                        paint(getGraphics());
                        x = x2;
                        y = y2;
                    }
                }
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (!dibujoLibre) {
                        if (segundosPuntos) {
                            if (x <= anchoPintar) {
                                x2 = e.getX() - x;
                            } else {
                                x2 = anchoPintar;
                            }
                            y2 = e.getY() - y;
                            System.out.println(e.getY());
                             paint(getGraphics());
                        }
                    }
                }
            };
            
        }

        public int getGrosorFigura() {
            return grosorFigura;
        }

        public void setGrosorFigura(int grosorFigura) {
            this.grosorFigura = grosorFigura;
        }

        
        
        public boolean isPrimerosPuntos() {
            return primerosPuntos;
        }

        public void setPrimerosPuntos(boolean primerosPuntos) {
            this.primerosPuntos = primerosPuntos;
        }

        public boolean isSegundosPuntos() {
            return segundosPuntos;
        }

        public void setSegundosPuntos(boolean segundosPuntos) {
            this.segundosPuntos = segundosPuntos;
        }

        public boolean isDibujoLibre() {
            return dibujoLibre;
        }

        public void setDibujoLibre(boolean dibujoLibre) {
            this.dibujoLibre = dibujoLibre;
        }
        
        public int getX2() {
            return x2;
        }

        public void setX2(int x2) {
            this.x2 = x2;
        }

        public int getY2() {
            return y2;
        }

        public void setY2(int y2) {
            this.y2 = y2;
        }

        public int getAnchoPintar() {
            return anchoPintar;
        }

        public void setAnchoPintar(int anchoPintar) {
            this.anchoPintar = anchoPintar;
        }

        public int getAltoPintar() {
            return altoPintar;
        }

        public void setAltoPintar(int altoPintar) {
            this.altoPintar = altoPintar;
        }

        public MouseListener getAccionMousePuntos() {
            return accionMousePuntos;
        }

        public void setAccionMousePuntos(MouseListener accionMousePuntos) {
            this.accionMousePuntos = accionMousePuntos;
        }

        public KeyListener getAccionTecladoLimpiar() {
            return accionTecladoLimpiar;
        }

        public void setAccionTecladoLimpiar(KeyListener accionTecladoLimpiar) {
            this.accionTecladoLimpiar = accionTecladoLimpiar;
        }

        public MouseMotionListener getAccionMouseMovimiento() {
            return accionMouseMovimiento;
        }

        public void setAccionMouseMovimiento(MouseMotionListener accionMouseMovimiento) {
            this.accionMouseMovimiento = accionMouseMovimiento;
        }

        public boolean isLimpiarPantalla() {
            return limpiarPantalla;
        }

        public void setLimpiarPantalla(boolean limpiarPantalla) {
            this.limpiarPantalla = limpiarPantalla;
        }

        public Color getColorFondo() {
            return colorFondo;
        }

        public void setColorFondo(Color colorFondo) {
            this.colorFondo = colorFondo;
        }

        public Color getColorFigura() {
            return colorFigura;
        }

        public void setColorFigura(Color colorFigura) {
            this.colorFigura = colorFigura;
        }

        public boolean isPintarFiguras() {
            return pintarFiguras;
        }

        public void setPintarFiguras(boolean pintarFiguras) {
            this.pintarFiguras = pintarFiguras;
        }

        public int getTipoFigura() {
            return tipoFigura;
        }

        public void setTipoFigura(int tipoFigura) {
            this.tipoFigura = tipoFigura;
        }
        
        
    }
    
}
