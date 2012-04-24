package Tester;
import java.awt.Dimension;

import com.golden.gamedev.GameLoader;


public class FighterTesterMain {
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new FighterTester(), new Dimension(700, 700), false);
        game.start();
    }
}
