package editor;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;
import editor.EditorView;

public class Main
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new EditorView(), new Dimension(1300, 750), false);
        loader.start();
    }
}