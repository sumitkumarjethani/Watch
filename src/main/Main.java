package main;

import architecture.controller.ChangeCommand;
import architecture.controller.ExitCommand;
import architecture.model.Watch;
import architecture.presenter.WatchPresenter;
import architecture.view.WatchDialog;
import architecture.view.WatchDisplay;
import swing.SwingWatchDialog;
import swing.SwingWatchDisplay;

public class Main {
    public static void main(String[] args){
        Watch watch = new Watch();
        
        MainFrame mainFrame = new MainFrame();
        WatchDisplay watchDisplay = new SwingWatchDisplay();
        WatchDialog watchDialog = new SwingWatchDialog();
        mainFrame.addWatchDisplay((SwingWatchDisplay)watchDisplay);
        mainFrame.addWatchDialog((SwingWatchDialog)watchDialog);
        
        WatchPresenter watchPresenter = new WatchPresenter(watchDisplay, watchDialog, watch);
        
        mainFrame.addCommand("Exit", new ExitCommand());
        mainFrame.addCommand("Change", new ChangeCommand(watchPresenter));
        mainFrame.execute();
    }
}
