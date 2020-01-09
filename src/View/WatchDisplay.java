package View;

import Model.Watch;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WatchDisplay extends JPanel implements Observer{
    private final Image background;
    private Point[] points;
    private final Watch watch;
    
    public WatchDisplay(Watch watch){
        this.watch = watch;
        this.background = loadBackground();
        this.points = new Point[0];
    }
    
    @Override
    public void update() {
        this.points = this.getPoints();
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(background ,0, 0, this.getWidth(),this.getHeight(),null);
        for (Point point : this.points) {
            g2(g).drawLine(centerx(), centery(), point.x + centerx(), centery() - point.y);
        }
    }

    private Point[] getPoints() {
        Point[] actualPoints = new Point[3];
        actualPoints[0] = pointAt(200,watch.getSecond());
        actualPoints[1] = pointAt(150,watch.getMinute());
        actualPoints[2] = pointAt(100,watch.getHour());
        return actualPoints;
    }
    
    private Point pointAt(int length, double angle) {
        return new Point((int)(length * cos(angle)),(int)(length * sin(angle)));
    }
    
    private int centerx(){
        return this.getWidth() / 2;
    }
    
    private int centery(){
        return this.getHeight() / 2;
    }
    
    private Image loadBackground() {
        try {
            return ImageIO.read(new File("background.png"));
        } catch (IOException ex) {
            Logger.getLogger(WatchDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Graphics2D g2(Graphics g) {
        return (Graphics2D) g;
    }
}
