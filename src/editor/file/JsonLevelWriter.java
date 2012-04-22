package editor.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import editor.Level;

public class JsonLevelWriter extends LevelWriter {
    
    public void writeLevel(String filename, Level level)
    {
  
        FileWriter fileOut;
        try
        {
            fileOut = new FileWriter(filename);
            BufferedWriter out2 = new BufferedWriter(fileOut);
            out2.write(level.toJson());
            out2.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
