package editor.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.Level;
/**
 * Writes a level to a file using serialization
 * @author Becky
 *
 */
public class SerializedLevelWriter extends LevelWriter {
    
    /**
     * writes the level to the specified filename
     * @param filename filename to be written to
     * @param Level level to be written
     */
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
