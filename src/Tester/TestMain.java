package Tester;
import java.awt.Dimension;

import com.golden.gamedev.GameLoader;


public class TestMain {
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Test(), new Dimension(600, 600), false);
        game.start();
    }
}
