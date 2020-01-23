package architecture.presenter;

import architecture.Point;
import architecture.observer.Observer;
import architecture.model.Watch;
import architecture.view.WatchDialog;
import architecture.view.WatchDisplay;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class WatchPresenter implements Observer{
    private static final double PI2 = Math.PI * 2;
    private static final double DegreesPerSecond = PI2 / 60;
    private static final double DegreesPerMinute = DegreesPerSecond / 60;
    private static final double DegreesPerHour = DegreesPerMinute / 12;    
    private final WatchDisplay watchDisplay;
    private final WatchDialog watchDialog;
    private final Watch watch;
    
    public WatchPresenter(WatchDisplay watchDisplay, WatchDialog watchDialog, Watch watch){
        this.watchDisplay = watchDisplay;
        this.watchDialog = watchDialog;
        this.watch = watch;
        this.watch.addObserver(this);
        this.watchDisplay.addListener(new WatchDisplay.Listener() {
            @Override
            public void newHourPosition(Point point) {
                double hourAngle = changeCartesianToPolar(point);
                hourAngle = normalize(hourAngle);
                watch.setHours(hourAngle);
            }

            private double changeCartesianToPolar(Point point) {
                double alfa = Math.tan(point.getX()/point.getY());
                return alfa;
                //return Math.toRadians(alfa);
            }
        });
    }
    
    @Override
    public void update() {
        this.watchDisplay.display(getPoints());
    }
    
    public WatchDisplay getWatchDisplay(){
        return this.watchDisplay;
    }
    
    public WatchDialog getWatchDialog(){
        return this.watchDialog;
    }
    
    public Watch getWatch(){
        return this.watch;
    }
    
    public double changeHoursToAngle() {
        double hours = Math.PI / 2;
        hours = this.normalize(hours - (this.watchDialog.getHours() * 5* DegreesPerSecond));
        return hours;
    }
    

    public Point changeMinutesToAngle() {
        double minutes = Math.PI / 2;
        double hours = this.watch.getHours();
        minutes = this.normalize(minutes - (this.watchDialog.getMinutes() * DegreesPerSecond));
        hours = normalize(hours - (this.watchDialog.getMinutes() * 5 *DegreesPerMinute));
        return new Point(minutes,hours);
    }

    public double changeSecondsToAngle() {
        double seconds = Math.PI / 2;
        seconds = normalize(seconds - (this.watchDialog.getSeconds() * DegreesPerSecond));
        return seconds;
    }
    
    private double normalize(double angle) {
        return (angle + PI2) % PI2;
    }
    
    
    private Point[] getPoints() {
        Point[] actualPoints = new Point[3];
        actualPoints[0] = pointAt(200,watch.getSeconds());
        actualPoints[1] = pointAt(150,watch.getMinutes());
        actualPoints[2] = pointAt(100,watch.getHours());
        return actualPoints;
    }
    
    private Point pointAt(int length, double angle) {
        return new Point((length * cos(angle)),(length * sin(angle)));
    }
}
