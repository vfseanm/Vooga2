package editor.file;

import java.io.File;

import editor.Level;

public abstract class LevelLoader {
    
    public abstract Level readLevel(File file);
}
