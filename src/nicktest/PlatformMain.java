package nicktest;
import java.awt.Dimension;

import com.golden.gamedev.GameLoader;


public class PlatformMain {
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new PlatformTest(), new Dimension(600, 600), false);
        game.start();
    }
}
