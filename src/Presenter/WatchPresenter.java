package Presenter;

import View.*;
import Model.Watch;
import java.awt.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class WatchPresenter implements Observer{

    private final SwingWatchDisplay watchDisplay;
    private final Watch watch;
    
    public WatchPresenter(SwingWatchDisplay watchDisplay, Watch watch){
        this.watchDisplay = watchDisplay;
        this.watch = watch;
        this.watch.addObserver(this);
    }
    
    @Override
    public void update() {
        this.watchDisplay.display(getPoints());
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
}
