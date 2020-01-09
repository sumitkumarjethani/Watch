package Controller;

import Model.Watch;

public class ExitCommand implements Command{

    private final Watch watch;
    
    public ExitCommand(Watch watch){
        this.watch = watch;
    }
    
    @Override
    public void execute() {
        this.watch.stopTimer();
        System.exit(0);
    }
    
}
