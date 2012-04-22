package editor.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import editor.Level;

public class SerializedLevelLoader extends LevelLoader {
    
    public Level readLevel(File file)
    {
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
