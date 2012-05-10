package uTorrentSorter;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/******************************************************************************
 *Written By: Taylor M. Hicks                                                 *
 *Purpose: Config Manager Panel                  *                          
 *Date:5/9/2012                                                               *
 *******************************************************************************/
public class ConfigManager extends JPanel {

    static JLabel rootLabel = new JLabel("Root Directory:");
    static JLabel tvLabel = new JLabel("TV Directory:");
    static JLabel moviesLabel = new JLabel("Movie Directory:");
    static JLabel gamesLabel = new JLabel("Game Directory:");
    static JLabel musicLabel = new JLabel("Music Directory:");
    static JLabel documentLabel = new JLabel("Document Directory:"); 
    static JLabel videoLabel = new JLabel("Video Directory:");
    static JLabel filesLabel = new JLabel("Unlabeld Directory");
    
    static JButton saveButton = new JButton("Save");
    static JButton cancelButton = new JButton("Cancel");

    Config config;
    
    JTextField rootTextField;
    JTextField tvTextField;
    JTextField moviesTextField;
    JTextField gamesTextField;
    JTextField musicTextField;
    JTextField documentTextField; 
    JTextField videoTextField;
    JTextField filesTextField;    
    
    public ConfigManager()
    {
        config = new Config();
        config.setConfigFile(new File("conf.txt"));
        initializeJTextFields(config);
        constructPanel();
    }
    public ConfigManager(Config c)
    {
        initializeJTextFields(c);
        constructPanel();
    }
    private void initializeJTextFields(Config c)
    {
        config = c;
        rootTextField = new JTextField(config.getRootDirectory());
        tvTextField = new JTextField(config.getTvDirectory());
        moviesTextField = new JTextField(config.getMoviesDirectory());
        gamesTextField = new JTextField(config.getGamesDirectory());
        musicTextField = new JTextField(config.getMusicDirectory());
        documentTextField = new JTextField(config.getDocumentDirectory());
        videoTextField = new JTextField(config.getVideoDirectory());
        filesTextField = new JTextField(config.getFilesDirectory());
    }
    private void constructPanel()
    {
        setLayout(new GridLayout(9,2));
        
        Listener listener = new Listener();
        rootTextField.addActionListener(listener);
        tvTextField.addActionListener(listener);
        moviesTextField.addActionListener(listener);
        gamesTextField.addActionListener(listener);
        musicTextField.addActionListener(listener);
        documentTextField.addActionListener(listener);
        videoTextField.addActionListener(listener);
        filesTextField.addActionListener(listener);
        saveButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
                
        add(rootLabel); add(rootTextField);
        add(tvLabel); add(tvTextField);        
        add(moviesLabel); add(moviesTextField);        
        add(gamesLabel); add(gamesTextField);
        add(musicLabel); add(musicTextField);
        add(documentLabel); add(documentTextField);
        add(videoLabel); add(videoTextField);
        add(filesLabel); add(filesTextField);
        add(saveButton); add(cancelButton);
        
    }
    private class Listener implements ActionListener
    { 
    @Override public void actionPerformed(ActionEvent event)
    {
            if (event.getSource().equals(cancelButton)) System.exit(0);
            
            config.setTvDirectory(tvTextField.getText());
            config.setDocumentDirectory(documentTextField.getText());
            config.setFilesDirectory(filesTextField.getText());
            config.setGamesDirectory(gamesTextField.getText());
            config.setMoviesDirectory(moviesTextField.getText());
            config.setMusicDirectory(musicTextField.getText());
            config.setVideoDirectory(videoTextField.getText());
            config.setRootDirectory(rootTextField.getText());
            
            if (event.getSource().equals(saveButton))
            {
                              
                try
                {
                config.writeToFile();
                System.exit(0);
                }
                catch(Exception exception)
                {
                    if(exception instanceof IOException) JOptionPane.showMessageDialog(saveButton.getTopLevelAncestor(),String.format("Error writing to file check your input and try agin.%n%s",exception.getMessage()),"Error", JOptionPane.ERROR_MESSAGE);
                    else
                    {
                        JOptionPane.showMessageDialog(saveButton.getTopLevelAncestor(),String.format("Error: %S%nForced To Close.",exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE));
                    }
                }
             }       
        }
    }
}
