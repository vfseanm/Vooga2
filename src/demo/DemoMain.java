package demo;

import java.awt.Dimension;
import java.util.ResourceBundle;
import com.golden.gamedev.GameLoader;
import demo.DemoGame;


public class DemoMain
{
    public static void main (String[] args)
    {
        ResourceBundle mySidescrollerResources = ResourceBundle
                .getBundle("sidescrolling.SidescrollerResourceBundle");
        int gameWidth = Integer.parseInt(mySidescrollerResources.getString("gameWidth"));
        int gameHeight = Integer.parseInt(mySidescrollerResources.getString("gameHeight"));
        GameLoader loader = new GameLoader();
        loader.setup(new DemoGame(), new Dimension(gameWidth, gameHeight), false);
        loader.start();
    }
}
