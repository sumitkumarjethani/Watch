package View;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingWatchDisplay extends JPanel implements WatchDisplay{
    private final Image background;
    private Point[] points;
    
    public SwingWatchDisplay(){
        this.background = loadBackground();
        this.points = new Point[0];
    }
    
    @Override
    public void display(Point[] point) {
        this.points = point;
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(background ,0, 0, this.getWidth(),this.getHeight(),null);
        int width = 1;
        for (Point point : this.points) {
            g2(g).setStroke(new BasicStroke(width));
            g2(g).drawLine(centerx(), centery(), point.x + centerx(), centery() - point.y);
            width++;
        }
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
}