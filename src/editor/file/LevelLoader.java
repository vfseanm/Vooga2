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

public class LevelLoader {
    
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            Level myLevel = (Level) in.readObject();
            // System.out.println(myLevel.getAllSprites().get(0).toString());
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
