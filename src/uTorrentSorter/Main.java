package uTorrentSorter;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

 static Torrent torrent;
 static Config config;
 

public static void main(String[] arguments)
{  
   config = new Config(new File("Directory.ini"));
   switch (arguments.length)
   {
       case 0: //No command line arguments run configuration editor
           ConfigManager panel = new ConfigManager(config);
           JFrame frame = new JFrame("Configuration");
           frame.add(panel);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(300,500);
           frame.setVisible(true);
           break;
        case 10://uTorrents complete parameter list act on torrent 
           torrent = new Torrent(arguments);
           switch(torrent.getState())
           {
               case 1:
                   break;
               case 2:
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 5:
                   //torrent is now seeding
                   sortTorrent();
                   break;
               case 6:
                   break;
               case 7:
                   break;
               case 8:
                   break;
               case 9:
                   break;
               case 10:
                   break;
               case 11:
                   break;
               case 12:
                   break;
               case 13:                    
                   break;
               default:                   
           }
           break;
        default:
            //Invalid command line arguments 
               JOptionPane.showMessageDialog(null,String.format("Invalid Run Arguments"));
            break;
   }
}
private static void sortTorrent()
{
        String label = torrent.getLabel();
        if(label.equalsIgnoreCase("tv") || 
                (label.equalsIgnoreCase("television")))
            moveTorrent(config.getTvDirectory());
        else if(label.equalsIgnoreCase("movies") ||
                label.equalsIgnoreCase("movie"))
            moveTorrent(config.getMoviesDirectory());
        else if((label.equalsIgnoreCase("game")) ||
                (label.equalsIgnoreCase("games")))
            moveTorrent(config.getGamesDirectory());
        else if((label.equalsIgnoreCase("document")) || 
                label.equalsIgnoreCase("documents"))
             moveTorrent(config.getDocumentDirectory());
        else if((label.equalsIgnoreCase("video")) ||
                label.equalsIgnoreCase("videos"))
             moveTorrent(config.getVideoDirectory());
        else if ((label.equalsIgnoreCase("music")) )
             moveTorrent(config.getMusicDirectory());
        else moveTorrent(config.getUnlabeledDirectory());
    
}
private static void moveTorrent(String sortedDirectory)
{
    try
    {
        String source = torrent.getDirectory();
        String destination = config.getRootDirectory() + sortedDirectory;
        
        if((torrent.getKind()).equalsIgnoreCase("multi"))
        {
            destination += torrent.getTitle() + "\\";
            FileUtils.copyDirectory(source, destination);
        }
        else
        {
            source += "\\" + torrent.getName();
            destination += torrent.getName();
            FileUtils.copyFile(source, destination);
        }
        
    }
    catch(Exception e)
    {
        popup(e.toString());
    }
}
private static void torrentIsNowState(Torrent torrent)
{
      JOptionPane.showMessageDialog(null, String.format("%s is now %s",torrent.getTitle(), torrent.getStateString()),torrent.getTitle(), JOptionPane.PLAIN_MESSAGE);
}
private static void displayCommandLineArgs(String[] arguments)
{
    String message = new String();
    int counter = 0;
    
    message += String.format("Elements in String[] arguments = %d%n",arguments.length);
    for (String argument: arguments)
    {
        if (counter < 20) message += String.format("arguments[%d] = %s%n",counter,argument);
        else break;
        counter++;
    }
   JOptionPane.showMessageDialog(null, message, "Argument Breakdown", JOptionPane.INFORMATION_MESSAGE);
}
private static void popup(String s)
{
    JOptionPane.showMessageDialog(null,String.format("%s",s), s ,JOptionPane.PLAIN_MESSAGE);
}
}


