package Model;

import View.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Watch {
    private double second = Math.PI/2;
    private double minute = Math.PI/2;
    private double hour = Math.PI/2;
    private final Timer timer;
    private final List<Observer> observers;
    
    public Watch(){
        observers = new ArrayList<>();
        timer = new Timer();
        timer.schedule(task(), 0, 1000);
    }

    public double getSecond() {
        return second;
    }

    public double getMinute() {
        return minute;
    }

    public double getHour() {
        return hour;
    }
    
    public void stopTimer(){
        this.timer.cancel();
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
                second = normalize(second - DegreesPerSecond);
                minute = normalize(minute - DegreesPerMinute);
                hour = normalize(hour - DegreesPerHour);
                notifyObservers();
            }

            private double normalize(double angle) {
                return (angle + PI2) % PI2;
            }

        };
    }
}
