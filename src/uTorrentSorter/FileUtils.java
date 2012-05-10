/******************************************************************************
 *Written By: Taylor M. Hicks                                                 *
 *COSC 1427
 *Purpose:                                                                    *
 *Date:                                                                       *
 *******************************************************************************/
package uTorrentSorter;
import java.io.*;

public class FileUtils {
public static void copyDirectory(String sourceDirectory, String destinationDirectory) throws IOException
{	
        File srcFolder = new File(sourceDirectory);
        File destFolder = new File(destinationDirectory); 
    	//make sure source exists
    	if(!srcFolder.exists())
        {           
           throw new IOException(String.format("Copy failed: %s does not exist", sourceDirectory));
        }
        else
        {
            try
            {
                recursiveCopyDirectory(srcFolder, destFolder);
            }
            catch(IOException e)
            {
                throw new IOException("IOException Thrown From recursiveCopy", e);
            }            
        }
}
private static void recursiveCopyDirectory(File src, File dest) throws IOException
{
         	if(src.isDirectory())
                {
   		//if directory does not exists, create it
    		if(!dest.exists())
                {
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) 
                {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   recursiveCopyDirectory(srcFile,destFile);
    		}
                }
                else
                {
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0)
                {
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
                }
                
 
}
    
}
