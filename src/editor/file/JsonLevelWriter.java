package editor.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import editor.Level;

public class JsonLevelWriter extends LevelWriter {
    
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
