package architecture.model;

import architecture.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Watch {
    private double seconds = Math.PI/2;
    private double minutes = Math.PI/2;
    private double hours = Math.PI/2;
    private final Timer timer;
    private final List<Observer> observers;
    
    public Watch(){
        observers = new ArrayList<>();
        timer = new Timer();
        timer.schedule(task(), 0, 1000);
    }

    public double getSeconds() {
        return this.seconds;
    }

    public double getMinutes() {
        return this.minutes;
    }

    public double getHours() {
        return this.hours;
    }
    
    public void setSeconds(double seconds){
        this.seconds = seconds;
        this.notifyObservers();
    }
    
    public void setMinutes(double minutes){
        this.minutes = minutes;
        this.notifyObservers();
    }
    
    public void setHours(double hours){
        this.hours = hours;
        this.notifyObservers();
    }
    
    public void addObserver(Observer o){
        observers.add(o);
    }
    
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    
    private TimerTask task() {
        return new TimerTask() {
        private static final double PI2 = Math.PI * 2;
        private static final double DegreesPerSecond = PI2 / 60;
        private static final double DegreesPerMinute = DegreesPerSecond / 60;
        private static final double DegreesPerHour = DegreesPerMinute / 12;    
            @Override
            public void run() {
                seconds = normalize(seconds - DegreesPerSecond);
                minutes = normalize(minutes - DegreesPerMinute);
                hours = normalize(hours - DegreesPerHour);
                notifyObservers();
            }
            
            private double normalize(double angle) {
                return (angle + PI2) % PI2;
            }
        };
    }
}
