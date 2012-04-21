package editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import sprite.AnimatedGameSprite;

public class JsonLevelLoader extends LevelLoader {
    public Level readLevel(File file) 
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(file);
            String wholeFile = scanner.useDelimiter("\\A").next();
            Level level = Level.fromJson(wholeFile);
            return level;
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }
}
