package Main;

import Controller.ExitCommand;
import Model.Watch;
import View.MainFrame;
import View.WatchDisplay;

public class Main {
    public static void main(String[] args){
        Watch watch = new Watch();
        WatchDisplay watchDisplay = new WatchDisplay(watch);
        watch.addObserver(watchDisplay);
        MainFrame mainFrame = new MainFrame();
        mainFrame.addWatchDisplay(watchDisplay);
        mainFrame.addCommand("Exit", new ExitCommand(watch));
        mainFrame.execute();
    }
}
