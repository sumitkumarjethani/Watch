package view.swing;

import controller.Command;
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
    private final Map<String,Command> commands;
    private final JPanel toolbar;
    
    public MainFrame(){
        setTitle("Analog Watch");
        setSize(500,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayout(new BorderLayout());
        labels = new HashMap<>();
        commands = new HashMap<>();
        toolbar = new JPanel();
        initLabels();
        initToolbar();
        this.add(toolbar,BorderLayout.SOUTH);
    }
    
    public void execute(){
        this.setVisible(true);
    }
    
    public void addCommand(String name, Command command){
        commands.put(name, command);
    }
    
    public void addWatchDisplay(SwingWatchDisplay watchDisplay){
        this.add(watchDisplay,BorderLayout.CENTER);
    }
    
    public void addWatchDialog(SwingWatchDialog swingWatchDialog){
        this.toolbar.add(swingWatchDialog);
    }

    private void initLabels() {
        labels.put("Exit", "Exit");
        labels.put("Change", "Change");
    }

    private void initToolbar() {
        this.toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.toolbar.add(button("Exit"));
        this.toolbar.add(button("Change"));
    }

    private JButton button(String label) {
        JButton button = new JButton(labels.get(label));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get(label).execute();
            }
        });
        return button;
    }
    
}
