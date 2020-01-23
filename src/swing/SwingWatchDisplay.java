package swing;

import architecture.Point;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import architecture.view.WatchDisplay;

public class SwingWatchDisplay extends JPanel implements WatchDisplay{
    private final Image background;
    private final List<WatchDisplay.Listener> listeners;
    private Point[] points;
    private boolean hoursGrabbed = false;
    
    public SwingWatchDisplay(){
        this.background = loadBackground();
        this.listeners = new ArrayList<>();
        this.points = new Point[0];
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }
    
    @Override
    public void display(Point[] points) {
        this.points = points;
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(background ,0, 0, this.getWidth(),this.getHeight(),null);
        int width = 7;
        for (Point point : this.points) {
            g2(g).setStroke(new BasicStroke(width));
            g2(g).drawLine(centerx(), centery(), (int)point.getX() + centerx(), centery() - (int)point.getY());
            width++;
        }
    }
    
    @Override
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }
    
    private int centerx(){
        return this.getWidth() / 2;
    }
    
    private int centery(){
        return this.getHeight() / 2;
    }
    
    private Graphics2D g2(Graphics g) {
        return (Graphics2D) g;
    }
    
    private Image loadBackground() {
        try {
            return ImageIO.read(new File("background.png"));
        } catch (IOException ex) {
            Logger.getLogger(SwingWatchDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if(isTouchingHours(me.getX(), me.getY())){
                    hoursGrabbed = true;
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                hoursGrabbed = false;
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        };
    }
    
    private boolean isTouchingHours(int x, int y){
        if(x < (this.points[2].getX()+centerx()+5) && x > (this.points[2].getX()+centerx()-7) &&
           y > (centery() - this.points[2].getY()-5) && y < (centery() - this.points[2].getY()+5)){
            return true;
        }
        return false;
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if(!hoursGrabbed) return;
                for (Listener listener : listeners) {
                    listener.newHourPosition(new Point((me.getX()-centerx()),(me.getY()-centery())));
                }
                
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        };
    }
}