package uTorrentSorter;

import java.security.InvalidParameterException;
import javax.swing.JOptionPane;


/******************************************************************************
 *Written By: Taylor M. Hicks                                                 *
 *Purpose:Models a Torrent from the parameter passed by uTorrent v 3.11.3             *
 *Date: May 7, 2012                                                                       *
 *******************************************************************************/
// "%F" "%D" "%N" "%P" "%L" "%T" "%M" "%I" "%S" "%K"
public class Torrent {

    String[] torrent;
    //make it easy to program should be hardcoded by compiler or jvm
    public final static int name = 0;//Name of downloaded file (for single file torrents)
    public final static int directory = 1;//Directory where files are saved
    public final static int title = 2;//Title of Torrent
    public final static int previousState = 3;//Previous state of Torrent
    public final static int label =4;//Label
    public final static int tracker = 5;//Tracker
    public final static int status = 6;//Status message string (same as status column)
    public final static int infoHash = 7;//hex encoded info-hash
    public final static int state = 8;//State of Torrent
    public final static int kind = 9;//kind of Torrent (single|multi)

    public Torrent()
    {
        String[] testTorrent = {
        "Test Torrent",
        "/Test Path",
        "Test Title",
        "6",
        "Test Label",
        "www.Test_Tracker.com/tracker",
        "Test Status",
        Integer.toHexString(420),
        "11",
        "Test Kind"
         };
        updateTorrent(testTorrent);
    }     
    public Torrent(String[] parameters)
    {
        this(); 
        updateTorrent(parameters);
    }    
    public String get(int property)
    {
        if ( property >= 0 && property <= 9) return torrent[property];
        else throw new InvalidParameterException("Use one of the static states");
    }
    public String getName()
    {
         return this.torrent[name];
     }
    public String getDirectory()
    {
         return this.torrent[directory];
     }
    public String getTitle()
    {
         return this.torrent[title];
     }
    public int getPreviousState()
    {
         return (Integer.parseInt(this.torrent[previousState]));
     }
    public String getPreviousStateString()
    {
        return this.parseState(this.torrent[previousState]);   
    }
    public int getState()
    {
        return Integer.parseInt(this.torrent[state]);
    }
    public String getStateString()
    {
         return this.parseState(this.torrent[state]);
    }
    public String getLabel()
    {
         return this.torrent[label];
     }
    public String getTracker()
    {
         return this.torrent[tracker];
     }
    public String getStatus()
    {
         return this.torrent[status];
     }
    private long getInfoHash() // private till funtionality added
    {
     return Long.parseLong(torrent[infoHash],16);   
    }
    public String getInfoHashString()
    {
         return this.torrent[infoHash];
     }  
    public String getKind()
    {
         return this.torrent[kind];
     }
    public String[] returnTorrentArray()
    {
         return this.torrent;
     }
    // utility functions left public due to their usefullness
    public void testTorrent()
    {
        String formattedString = "";
        for (String property : torrent) formattedString += String.format("%s%n", property);
        formattedString += String.format("%nTesting special get functions%n");
        formattedString += String.format("%s%n",getPreviousState());
        formattedString += String.format("%s%n",getPreviousStateString());
        formattedString += String.format("%s%n",getState());
        formattedString += String.format("%s%n",getStateString());
        formattedString += String.format("%d%n",getInfoHash());
        
        JOptionPane.showMessageDialog(null, formattedString);
     }
    public String parseState(String state)
    {
     /*
     Error - 1
     Checked - 2
     Paused - 3
     Super seeding - 4
     Seeding - 5
     Downloading - 6
     Super seed [F] - 7
     Seeding [F] - 8
     Downloading [F] - 9
     Queued seed - 10
     Finished - 11
     Queued - 12
     Stopped - 13
     */
        String[] states =  { null,
                             "Error",
                             "Checked",
                             "Paused",
                             "Super Seeding",
                             "Seeding",
                             "Downloading",
                             "Super Seed [F]",
                             "Seeding [F]",
                             "Downloading [F]",
                             "Queued Seed",
                             "Finished",
                             "Queued",
                             "Stopped"};        
        try {return states[Integer.parseInt(state)];}
        catch (ArrayIndexOutOfBoundsException exception)
        {
        return "Somthings Wrong with the State";
        }   
     }
    public String parseState(int state)
    {
         /*
     * Where State is one of:
     * Error - 1
     * Checked - 2
     * Paused - 3
     * Super seeding - 4
     * Seeding - 5
     * Downloading - 6
     * Super seed [F] - 7
     * Seeding [F] - 8
     * Downloading [F] - 9
     * Queued seed - 10
     * Finished - 11
     * Queued - 12
     * Stopped - 13
     */  
     String[] states =  { null,
                             "Error",
                             "Checked",
                             "Paused",
                             "Super Seeding",
                             "Seeding",
                             "Downloading",
                             "Super Seed [F]",
                             "Seeding [F]",
                             "Downloading [F]",
                             "Queued Seed",
                             "Finished",
                             "Queued",
                             "Stopped"};        
     try{ return states[state];     }
     catch (ArrayIndexOutOfBoundsException exception)
    {
        return "Somthings Wrong with the State";
    }   

    }
    public final boolean validateTorrentArray(String [] parameters)
    {
         if(
                 parameters.length == 10&&
                 (((Integer.parseInt(parameters[state])) > 0) && (Integer.parseInt(parameters[state]) < 14))
                 //could add more checks
            ) return true;
         else return false;
     }
    public final void updateTorrent(String[] parameters)
    {
         if (this.validateTorrentArray(parameters)) this.torrent = parameters;
         else throw new InvalidParameterException("The arguments failed validation");
    }
}