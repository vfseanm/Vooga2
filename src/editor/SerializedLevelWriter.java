package editor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializedLevelWriter extends LevelWriter {
    
    public void writeLevel(String filename, Level level)
    {
        FileOutputStream fos = null;
    
        ObjectOutputStream out = null;
        try
        {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(level);
            out.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
