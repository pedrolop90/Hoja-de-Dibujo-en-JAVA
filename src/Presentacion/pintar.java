

package Presentacion;
import java.awt.*;
import java.awt.event.*;

public class pintar extends Canvas implements KeyListener,MouseListener,MouseMotionListener{
    
    private int x=-1,y=-1,x2=-1,y2=-1;
    private boolean entrar=true;
    
    public pintar(){
        setSize(500,500);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.black);
        if(x2!=-1&&x!=-1)  g2.drawLine(x, y, x2, y2);
        if(entrar){
            g2.setColor(Color.WHITE);
            g2.fillRect(-10, -10, 800,600);
            entrar=false;
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        paint(this.getGraphics());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       x=-1;
       x2=-1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2=e.getX();
        y2=e.getY();
        paint(this.getGraphics());
        x=x2;
        y=y2;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='n'){
            entrar=true;
            paint(this.getGraphics());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}