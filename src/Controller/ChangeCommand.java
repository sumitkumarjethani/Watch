package Controller;

import Model.Watch;
import View.SwingWatchDialog;

public class ChangeCommand implements Command{
    
    private final SwingWatchDialog swingWatchDialog;
    private final Watch watch;

    public ChangeCommand(SwingWatchDialog swingWatchDialog, Watch watch){
        this.swingWatchDialog = swingWatchDialog;
        this.watch = watch;
    }
    
    @Override
    public void execute() {
        this.watch.setHour(this.swingWatchDialog.getHours());
        this.watch.setMinute(this.swingWatchDialog.getMinutes());
        this.watch.setSecond(this.swingWatchDialog.getSeconds());
    }
    
}
