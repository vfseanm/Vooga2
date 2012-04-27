package editor.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import editor.Level;
/**
 * Loads a level that is stored either using json or serialization
 * @author Becky
 *
 */
public class LevelLoader {
    /**
     * reads the level in from the file and loads appropriately
     * @param file to be read in
     * @return Level to be loaded
     */
    public Level readLevel(File file)
    {
        Scanner scanner = null;
        try
        {
            FileReader input = new FileReader(file);
            BufferedReader b = new BufferedReader(input);
            if(b.readLine().startsWith("json"))
            {
                scanner = new Scanner(file);
                String wholeFile = scanner.useDelimiter("\\A").next();
                Level level = Level.fromJson(wholeFile.substring(4));
                return level;
            }
            
            
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            Level myLevel = (Level) in.readObject();
            in.close();
            return myLevel;
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
