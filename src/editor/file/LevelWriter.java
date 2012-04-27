package editor.file;

import editor.Level;
/**
 * Abstract level writer that writes a level to a file
 * @author Becky
 *
 */
public abstract class LevelWriter {
    
    /**
     * Writes the specified level to the specified filename
     * @param fileName
     * @param level
     */
    public abstract void writeLevel(String fileName, Level level);
    
    

}
