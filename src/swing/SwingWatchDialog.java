package swing;

import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JPanel;
import javax.swing.JTextField;
import architecture.view.WatchDialog;

public class SwingWatchDialog extends JPanel implements WatchDialog{

    private final JTextField seconds;
    private final JTextField minutes;
    private final JTextField hours;
    
    public SwingWatchDialog(){
        this.seconds = new JTextField(3);
        this.minutes = new JTextField(3);
        this.hours = new JTextField(3);
        this.add(toolbar());
    }
    
    @Override
    public double getSeconds() {
        if(this.seconds.getText().matches("^[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$")){
            double sec = Double.parseDouble(this.seconds.getText());
            if(verifySecMin(sec)) return sec;
        }
        return 0.0;
    }

    @Override
    public double getMinutes() {
        if(this.minutes.getText().matches("^[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$")){
            double min = Double.parseDouble(this.minutes.getText());
            if(verifySecMin(min)) return min;
        }
        return 0.0;
    }

    @Override
    public double getHours() {
        if(this.hours.getText().matches("^[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$")){
            double hour = Double.parseDouble(this.hours.getText());
            if(verifyHour(hour)) return hour;
        }
        return 0.0;
    }

    private JPanel toolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(new Label("Hour"));
        toolbar.add(this.hours);
        toolbar.add(new Label("Minutes"));
        toolbar.add(this.minutes);
        toolbar.add(new Label("Seconds"));
        toolbar.add(this.seconds);
        return toolbar;
    }

    private boolean verifySecMin(double x) {
        if(x > 59.0) return false;
        return true;
    }

    private boolean verifyHour(double hour) {
        if(hour > 12.0) return false;
        return true;
    }
    
}
