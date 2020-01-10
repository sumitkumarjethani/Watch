package View;

import Presenter.WatchPresenter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private final Map<String,String> labels;
    private WatchPresenter watchPresenter;
    
    public MainFrame(){
        setTitle("Analog Watch");
        setSize(450,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayout(new BorderLayout());
        labels = new HashMap<>();
        initLabels();
        this.add(toolbar(),BorderLayout.SOUTH);
    }
    
    public void execute(){
        this.setVisible(true);
    }
    
    public void addWatchDisplay(WatchDisplay watchDisplay){
        this.add(watchDisplay,BorderLayout.CENTER);
    }
    
    public void setWatchPresenter(WatchPresenter watchPresenter){
        this.watchPresenter = watchPresenter;
    }

    private void initLabels() {
        labels.put("Exit", "Exit");
    }

    private JPanel toolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolbar.add(button("Exit"));
        return toolbar;
    }

    private JButton button(String label) {
        JButton button = new JButton(labels.get(label));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                watchPresenter.exit();
            }
        });
        return button;
    }
    
}
