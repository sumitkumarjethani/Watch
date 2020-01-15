package Main;

import Controller.ChangeCommand;
import Controller.ExitCommand;
import Model.Watch;
import Presenter.WatchPresenter;
import View.MainFrame;
import View.SwingWatchDialog;
import View.SwingWatchDisplay;
import View.WatchDialog;

public class Main {
    public static void main(String[] args){
        //Model
        Watch watch = new Watch();
        
        //View
        MainFrame mainFrame = new MainFrame();
        SwingWatchDisplay watchDisplay = new SwingWatchDisplay();
        SwingWatchDialog swingWatchDialog = new SwingWatchDialog();
        mainFrame.addWatchDisplay(watchDisplay);
        mainFrame.addWatchDialog(swingWatchDialog);
        
        //Presenter
        WatchPresenter watchPresenter = new WatchPresenter(watchDisplay, watch);
        
        //Controller
        mainFrame.addCommand("Exit", new ExitCommand());
        mainFrame.addCommand("Change", new ChangeCommand(swingWatchDialog,watch));
        mainFrame.execute();
    }
}
