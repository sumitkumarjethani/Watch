package architecture.view;

import architecture.Point;


public interface WatchDisplay {
    
    public void display(Point[] point);
    public void addListener(WatchDisplay.Listener listener);
    
    public interface Listener {
        public void newHourPosition(Point point);
    }
    
}
