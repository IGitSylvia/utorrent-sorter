package uTorrentSorter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



/******************************************************************************
 *Written By: Taylor M. Hicks                                                 *
 *Purpose: Store configuration settings for programs                                                                 *
 *Date: 5/9/2012                                                                       *
 *******************************************************************************/
public class Config{

    private File configFile;

    //Save Directories - All directories should contain an absolute pathname
    /* require a uTorrent label to sort to this directory */
    private String tvDirectory;
    private String moviesDirectory;
    private String gamesDirectory;
    
    /* label & auto-detect will sort*/
    private String musicDirectory;
    private String documentDirectory; 
    private String videoDirectory;
    private String filesDirectory;//failsafe will catch all unknown filetypes
    
    private String rootDirectory;
    
    public Config()
    {
        tvDirectory = new String();
        moviesDirectory = new String();
        gamesDirectory = new String();        
        musicDirectory = new String();
        documentDirectory = new String();
        videoDirectory = new String();
        filesDirectory = new String();
        rootDirectory = new String();
        configFile = null;
    }    
    public Config(File configFile)
    {
        this();
        this.configFile = configFile;
        if (configFile.exists())
        try
        {
            this.readFromFile();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Somethings Wrong with the config File please make a new one");
            this.voidAll();
            this.configFile = configFile;
            runConfigManager();
        }
        else runConfigManager();
        
    }
    
    public final void runConfigManager()
    {
            if(this.configFile != null)
            {
                ConfigManager panel = new ConfigManager(this);
                JFrame frame = new JFrame("Configuration");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(250,500);
                frame.add(panel);           
                frame.setVisible(true); 
            }
            else System.out.print("Can't run config manager configFile = null");
    }
    public final Boolean writeToFile() throws Exception
    {
        boolean success = false;
        try
        {
            FileWriter writer = new FileWriter(configFile);
            writer.write(String.format("!%s!!%s!!%s!!%s!!%s!!%s!!%s!!%s!",
                    rootDirectory,
                    tvDirectory,
                    moviesDirectory,
                    gamesDirectory,
                    
                    musicDirectory,
                    documentDirectory,
                    videoDirectory,
                    filesDirectory));
            writer.close();
            success = true;
        }
        catch(Exception e)
        {
            if (e instanceof IOException) throw new Exception(String.format("Error writing to %s",configFile.getName()),e);
            else throw e;
        }
        return success;
    }
    public final boolean readFromFile() throws Exception    
    {
        boolean success = false;
        try
        {
           Scanner reader= new Scanner(configFile).useDelimiter("!*!");
           
           rootDirectory = reader.next();
           tvDirectory = reader.next();
           moviesDirectory = reader.next();
           gamesDirectory = reader.next();
           
           musicDirectory = reader.next();
           documentDirectory = reader.next();
           videoDirectory = reader.next();
           filesDirectory = reader.next();
           reader.close();
           success = true;
        }
        catch(Exception e)
        {
            if (e instanceof IOException) throw new Exception(String.format("Error reading %s",configFile.getName()),e);
        }
        return success;
    }
    private void voidAll()
    {
        tvDirectory = new String();
        moviesDirectory = new String();
        gamesDirectory = new String();        
        musicDirectory = new String();
        documentDirectory = new String();
        videoDirectory = new String();
        filesDirectory = new String();
        configFile = null;        
    }

    public String getRootDirectory()
    {
        return rootDirectory;
    }
    public void setRootDirectory(String rootDirectory)
    {
        this.rootDirectory = rootDirectory;
    }    
    public String getMoviesDirectory()
    {
        return moviesDirectory;
    }
    public void setMoviesDirectory(String moviesDirectory)
    {
        this.moviesDirectory = moviesDirectory;
    }
    public String getFilesDirectory()
    {
        return filesDirectory;
    }
    public void setFilesDirectory(String filesDirectory)
    {
        this.filesDirectory = filesDirectory;
    }
    public File getConfigFile()
    {
        return configFile;
    }
    public void setConfigFile(File configFile)
    {
        this.configFile = configFile;
    }
    public String getDocumentDirectory()
    {
        return documentDirectory;
    }
    public void setDocumentDirectory(String documentDirectory)
    {
        this.documentDirectory = documentDirectory;
    }
    public String getGamesDirectory()
    {
        return gamesDirectory;
    }
    public void setGamesDirectory(String gamesDirectory)
    {
        this.gamesDirectory = gamesDirectory;
    }
    public String getMusicDirectory()
    {
        return musicDirectory;
    }
    public void setMusicDirectory(String musicDirectory)
    {
        this.musicDirectory = musicDirectory;
    }
    public String getTvDirectory()
    {
        return tvDirectory;
    }
    public void setTvDirectory(String tvDirectory)
    {
        this.tvDirectory = tvDirectory;
    }
    public String getVideoDirectory()
    {
        return videoDirectory;
    }
    public void setVideoDirectory(String videoDirectory)
    {
        this.videoDirectory = videoDirectory;
    }   
}
