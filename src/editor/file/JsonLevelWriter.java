package editor.file;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import editor.Level;
/**
 * Writer that allows you to write a level using json
 * @author Becky
 *
 */
public class JsonLevelWriter extends LevelWriter {
    /**
     * writes the specifed level in json to the specified file name
     */
    public void writeLevel(String filename, Level level)
    {
  
        FileWriter fileOut;
        try
        {
            fileOut = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fileOut);
            out.write("json");
            out.write(level.toJson());
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
