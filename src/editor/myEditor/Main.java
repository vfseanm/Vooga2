package editor.myEditor;

import java.awt.Dimension;


import com.golden.gamedev.GameLoader;

public class Main 
{
    public static void main (String[] args) {
        GameLoader loader = new GameLoader();
        loader.setup(new CustomizedEditor(), new Dimension(1300, 750), false);
        loader.start();
    }
}