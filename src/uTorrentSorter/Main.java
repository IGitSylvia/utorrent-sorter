package uTorrentSorter;

import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

 static Torrent torrent;
 static Config config;
 

public static void main(String[] arguments)
{  
   config = new Config(new File("conf.txt"));
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
               case 2:
               case 3:
               case 4:
               case 5:
                   //torrent is now seeding
                   sortTorrent();
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 11:
               case 12:
               case 13:                    
                   break;
               default:                   
           }
           torrent.testTorrent();
           break;
       default:  //Invalid command line arguments 
           JOptionPane.showMessageDialog(null,String.format(
                   "Valid Arguments Are:%n"
                   + "\"%F\" \"%D\" \"%N\" \"%P\" \"%L\" \"%T\" \"%M\" \"%I\" \"%S\" \"%K\""
                   + "and%n"
                   + "none"));
           break;
           
   }
}
private static void sortTorrent()
{
    String label = torrent.getLabel();
    String rootDirectory = config.getRootDirectory();
    String sourceDirectory = torrent.getDirectory();
    try
    {
        if(label.equalsIgnoreCase("tv") || (label.equalsIgnoreCase("television")))FileUtils.copyDirectory(sourceDirectory, rootDirectory + config.getTvDirectory());
        else if(label.equalsIgnoreCase("movies")|| label.equalsIgnoreCase("movie"))FileUtils.copyDirectory(sourceDirectory,rootDirectory + config.getMoviesDirectory());
        else if((label.equalsIgnoreCase("game"))||(label.equalsIgnoreCase("games")))FileUtils.copyDirectory(sourceDirectory,rootDirectory + config.getGamesDirectory());
        else if((label.equalsIgnoreCase("document"))||label.equalsIgnoreCase("documents"))FileUtils.copyDirectory(sourceDirectory,rootDirectory + config.getDocumentDirectory());
        else if((label.equalsIgnoreCase("video"))||label.equalsIgnoreCase("videos"))FileUtils.copyDirectory(sourceDirectory,rootDirectory + config.getVideoDirectory());
        else FileUtils.copyDirectory(sourceDirectory,rootDirectory + config.getFilesDirectory());
    }
    catch (IOException e)
    {

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

}


