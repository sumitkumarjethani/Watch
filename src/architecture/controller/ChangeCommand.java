package architecture.controller;

import architecture.model.Watch;
import architecture.presenter.WatchPresenter;

public class ChangeCommand implements Command{
    
    private final WatchPresenter watchPresenter;
    private final Watch watch;
    
    public ChangeCommand(WatchPresenter watchPresenter){
        this.watchPresenter = watchPresenter;
        this.watch = this.watchPresenter.getWatch();
    }
    
    @Override
    public void execute() {
        this.watch.setHours(this.watchPresenter.changeHoursToAngle());
        this.watch.setMinutes(this.watchPresenter.changeMinutesToAngle().getX());
        this.watch.setHours(this.watchPresenter.changeMinutesToAngle().getY());
        this.watch.setSeconds(this.watchPresenter.changeSecondsToAngle());
    }
}
