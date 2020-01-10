package Main;

import Model.Watch;
import Presenter.WatchPresenter;
import View.MainFrame;
import View.WatchDisplay;

public class Main {
    public static void main(String[] args){
        //Model
        Watch watch = new Watch();
        
        /*
            Entiendo WatchDisplay como la vista que se actualiza cada segundo
            Y MainnFrame es parte de la vista pero no se actualiza sino que solo
            llama al metodo correspondiente del Presenter cuando se produce algun 
            evento del boton.(Intento de MVP y MVC juntos)
        */
        MainFrame mainFrame = new MainFrame();
        WatchDisplay watchDisplay = new WatchDisplay();
        mainFrame.addWatchDisplay(watchDisplay);
        
        //Presenter
        WatchPresenter watchPresenter = new WatchPresenter(watchDisplay, watch);
        mainFrame.setWatchPresenter(watchPresenter);
        
        //....
        mainFrame.execute();
    }
}
